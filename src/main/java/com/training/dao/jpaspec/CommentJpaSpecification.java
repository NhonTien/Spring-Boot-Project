package com.training.dao.jpaspec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.training.entity.CommentEntity;
import com.training.entity.ProductEntity;
import com.training.entity.UserEntity;

public class CommentJpaSpecification {

	/**
	 * Get search criteria for query to search products
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>
	 */
	public static Specification<CommentEntity> getSearchCriteria(Map<String, Object> searchConditionsMap) {

		return new Specification<CommentEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CommentEntity> commentRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (searchConditionsMap != null) {

					String keyword = (String) searchConditionsMap.get("keyword");
					Join<CommentEntity, ProductEntity> productRoot = commentRoot.join("productEntity");
					Join<CommentEntity, UserEntity> userRoot = commentRoot.join("userEntity");

					// Keyword Predicate
					if (StringUtils.isNotEmpty(keyword)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(commentRoot.get("content"), "%" + keyword + "%"),
								criteriaBuilder.like(productRoot.get("productName"), "%" + keyword + "%"),
								criteriaBuilder.like(userRoot.get("lastname"), "%" + keyword + "%"),
								criteriaBuilder.like(userRoot.get("firstname"), "%" + keyword + "%"),
								criteriaBuilder.like(userRoot.get("username"), "%" + keyword + "%")
						));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

