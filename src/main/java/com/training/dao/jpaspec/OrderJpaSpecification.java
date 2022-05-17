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

import com.training.entity.District;
import com.training.entity.Order;
import com.training.entity.Province;
import com.training.entity.UserEntity;
import com.training.entity.Ward;

public class OrderJpaSpecification {

	/**
	 * Get search criteria for query to search products
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>
	 */
	public static Specification<Order> getSearchCriteria(Map<String, Object> searchConditionsMap) {

		return new Specification<Order>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Order> orderRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (searchConditionsMap != null) {

					String name = (String) searchConditionsMap.get("name");
					String address = (String) searchConditionsMap.get("address");
					String priceFrom = (String) searchConditionsMap.get("priceFrom");
					Join<Order, UserEntity> userRoot = orderRoot.join("userEntity");
					Join<Order, Province> provinceRoot = orderRoot.join("province");
					Join<Order, District> districtRoot = orderRoot.join("district");
					Join<Order, Ward> wardRoot = orderRoot.join("ward");

					// Keyword Predicate
					if (StringUtils.isNotEmpty(name)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(userRoot.get("firstname"), "%" + name + "%"),
								criteriaBuilder.like(userRoot.get("lastname"), "%" + name + "%")
						));
					}
					
					if (StringUtils.isNotEmpty(address)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(provinceRoot.get("name"), "%" + address + "%"),
								criteriaBuilder.like(districtRoot.get("name"), "%" + address + "%"),
								criteriaBuilder.like(wardRoot.get("name"), "%" + address + "%")
						));
					}
					
					// Price From Predicate
					if (StringUtils.isNotEmpty(priceFrom)) {
						predicates.add(criteriaBuilder.greaterThanOrEqualTo(orderRoot.get("total"), Double.parseDouble(priceFrom)));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

