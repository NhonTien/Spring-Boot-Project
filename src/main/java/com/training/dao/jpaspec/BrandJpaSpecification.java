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

import com.training.entity.BrandEntity;

public class BrandJpaSpecification {

	/**
	 * Get search criteria for query to search products
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>
	 */
	public static Specification<BrandEntity> getSearchCriteria(Map<String, Object> searchConditionsMap) {

		return new Specification<BrandEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<BrandEntity> brandRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (searchConditionsMap != null) {

					String keyword = (String) searchConditionsMap.get("keyword");

					// Keyword Predicate
					if (StringUtils.isNotEmpty(keyword)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(brandRoot.get("brandName"), "%" + keyword + "%"),
								criteriaBuilder.like(brandRoot.get("description"), "%" + keyword + "%")
						));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
