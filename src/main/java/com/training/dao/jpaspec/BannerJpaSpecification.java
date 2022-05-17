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

import com.training.entity.BannerEntity;

public class BannerJpaSpecification {

	/**
	 * Get search criteria for query to search products
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>
	 */
	public static Specification<BannerEntity> getSearchCriteria(Map<String, Object> searchConditionsMap) {

		return new Specification<BannerEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<BannerEntity> bannerRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (searchConditionsMap != null) {

					String keyword = (String) searchConditionsMap.get("keyword");

					// Keyword Predicate
					if (StringUtils.isNotEmpty(keyword)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(bannerRoot.get("bannerName"), "%" + keyword + "%"),
								criteriaBuilder.like(bannerRoot.get("description"), "%" + keyword + "%")
						));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

