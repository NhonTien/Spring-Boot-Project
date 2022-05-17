package com.training.dao.jpaspec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.training.entity.UserEntity;

public class UserJpaSpecification {

	/**
	 * Get search criteria for query to search products
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>
	 */
	public static Specification<UserEntity> getSearchCriteria(Map<String, Object> searchConditionsMap) {

		return new Specification<UserEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<UserEntity> userRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (searchConditionsMap != null) {

					String keyword = (String) searchConditionsMap.get("keyword");
					String role = (String) searchConditionsMap.get("role");

					// Keyword Predicate
					if (StringUtils.isNotEmpty(keyword)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(userRoot.get("lastname"), "%" + keyword + "%"),
								criteriaBuilder.like(userRoot.get("firstname"), "%" + keyword + "%"),
								criteriaBuilder.like(userRoot.get("username"), "%" + keyword + "%"),
								criteriaBuilder.like(userRoot.get("phoneNumber"), "%" + keyword + "%")
						));
					}
					
					if (StringUtils.isNotEmpty(role)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(userRoot.get("role"), "%" + role + "%")
						));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

