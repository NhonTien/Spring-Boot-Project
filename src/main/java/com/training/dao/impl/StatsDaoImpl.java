package com.training.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.training.dao.IStatsDao;
import com.training.entity.BrandEntity;
import com.training.entity.CategoryEntity;
import com.training.entity.CommentEntity;
import com.training.entity.Order;
import com.training.entity.OrderDetail;
import com.training.entity.ProductEntity;

@Repository
@Transactional
public class StatsDaoImpl implements IStatsDao {

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> brandStarts() {

		Session session = this.sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		Root productRoot = criteriaQuery.from(ProductEntity.class);
		Root brandRoot = criteriaQuery.from(BrandEntity.class);

		criteriaQuery.where(criteriaBuilder.equal(productRoot.get("brandEntity"), brandRoot.get("brandId")));
		criteriaQuery.multiselect(brandRoot.get("brandId"), brandRoot.get("brandName"), criteriaBuilder.count(productRoot.get("productId")));
		criteriaQuery.groupBy(brandRoot.get("brandId"));

		Query query = session.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> categoryStarts() {

		Session session = this.sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		Root productRoot = criteriaQuery.from(ProductEntity.class);
		Root categoryRoot = criteriaQuery.from(CategoryEntity.class);

		criteriaQuery.where(criteriaBuilder.equal(productRoot.get("categoryEntity"), categoryRoot.get("categoryId")));
		criteriaQuery.multiselect(categoryRoot.get("categoryId"), categoryRoot.get("categoryName"), criteriaBuilder.count(productRoot.get("productId")));
		criteriaQuery.groupBy(categoryRoot.get("categoryId"));

		Query query = session.createQuery(criteriaQuery);

		return query.getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> commentStarts() {

		Session session = this.sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		Root commentRoot = criteriaQuery.from(CommentEntity.class);
		Root productRoot = criteriaQuery.from(ProductEntity.class);

		criteriaQuery.where(criteriaBuilder.equal(commentRoot.get("productEntity"), productRoot.get("productId")));
		criteriaQuery.multiselect(productRoot.get("productId"), productRoot.get("productName"), criteriaBuilder.count(commentRoot.get("commentId")));
		criteriaQuery.groupBy(productRoot.get("productId"));

		Query query = session.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> productStarts(String keyword, Date fromDate, Date toDate) {

		Session session = this.sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		Root productRoot = criteriaQuery.from(ProductEntity.class);
		Root orderRoot = criteriaQuery.from(Order.class);
		Root orderDetailRoot = criteriaQuery.from(OrderDetail.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(orderDetailRoot.get("productEntity"), productRoot.get("productId")));
		predicates.add(criteriaBuilder.equal(orderDetailRoot.get("order"), orderRoot.get("id")));

		criteriaQuery.multiselect(productRoot.get("productId"), productRoot.get("productName"),
				criteriaBuilder.sum(criteriaBuilder.prod(orderDetailRoot.get("price"), orderDetailRoot.get("quantity"))));

		if (keyword != null && !keyword.isEmpty()) {
			predicates.add(criteriaBuilder.like(productRoot.get("productName"), String.format("%%%s%%", keyword)));
		}

		if (fromDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("orderDate"), fromDate));
		}

		if (toDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("orderDate"), toDate));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		criteriaQuery.groupBy(productRoot.get("productId"));

		Query query = session.createQuery(criteriaQuery);

		return query.getResultList();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> productMonthStarts(String keyword, Date fromDate, Date toDate) {

		Session session = this.sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		Root productRoot = criteriaQuery.from(ProductEntity.class);
		Root orderRoot = criteriaQuery.from(Order.class);
		Root orderDetailRoot = criteriaQuery.from(OrderDetail.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(orderDetailRoot.get("productEntity"), productRoot.get("productId")));
		predicates.add(criteriaBuilder.equal(orderDetailRoot.get("order"), orderRoot.get("id")));

		criteriaQuery.multiselect(criteriaBuilder.function("MONTH", Integer.class, orderRoot.get("orderDate")),
				criteriaBuilder.function("YEAR", Integer.class, orderRoot.get("orderDate")),
				criteriaBuilder.sum(criteriaBuilder.prod(orderDetailRoot.get("price"), orderDetailRoot.get("quantity"))));
		
		if (keyword != null && !keyword.isEmpty()) {
			predicates.add(criteriaBuilder.like(productRoot.get("productName"), String.format("%%%s%%", keyword)));
		}

		if (fromDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("orderDate"), fromDate));
		}

		if (toDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(orderRoot.get("orderDate"), toDate));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		criteriaQuery.groupBy(criteriaBuilder.function("MONTH", Integer.class, orderRoot.get("orderDate")),
				criteriaBuilder.function("YEAR", Integer.class, orderRoot.get("orderDate")));

		Query query = session.createQuery(criteriaQuery);

		return query.getResultList();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object[]> hotProducts(Long categoryId, int number) {

		Session session = this.sessionFactory.getObject().getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

		Root productRoot = criteriaQuery.from(ProductEntity.class);
		Root orderDetailRoot = criteriaQuery.from(OrderDetail.class);

		criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(orderDetailRoot.get("productEntity"), productRoot.get("productId")));
		
		criteriaQuery.multiselect(productRoot.get("productId"), productRoot.get("categoryEntity").get("categorySlug"), productRoot.get("productSlug"), productRoot.get("image"), productRoot.get("productName"), productRoot.get("price"), productRoot.get("discount"), productRoot.get("categoryEntity").get("categoryId"),
				criteriaBuilder.sum(orderDetailRoot.get("quantity")));

		criteriaQuery.groupBy(productRoot.get("productId"));
		//criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(orderDetailRoot.get("quantity"))));
		criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(orderDetailRoot.get("quantity"))));
		criteriaQuery.having(criteriaBuilder.equal(productRoot.get("categoryEntity").get("categoryId"), categoryId));
		Query query = session.createQuery(criteriaQuery);
		query.setMaxResults(number);

		return query.getResultList();
	}
}
