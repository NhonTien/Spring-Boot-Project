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
import com.training.entity.FeeShipEntity;
import com.training.entity.Province;
import com.training.entity.Ward;

public class FeeShipJpaSpecification {

	/**
	 * Get search criteria for query to search products
	 * 
	 * @param searchConditionsMap
	 * @return Specification<ProductEntity>
	 */
	public static Specification<FeeShipEntity> getSearchCriteria(Map<String, Object> searchConditionsMap) {

		return new Specification<FeeShipEntity>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<FeeShipEntity> feeShipRoot, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if (searchConditionsMap != null) {

					String keyword = (String) searchConditionsMap.get("keyword");
					Join<FeeShipEntity, Province> provinceRoot = feeShipRoot.join("province");
					Join<FeeShipEntity, District> districtRoot = feeShipRoot.join("district");
					Join<FeeShipEntity, Ward> wardRoot = feeShipRoot.join("ward");

					// Keyword Predicate
					if (StringUtils.isNotEmpty(keyword)) {
						predicates.add(criteriaBuilder.or(
								criteriaBuilder.like(provinceRoot.get("name"), "%" + keyword + "%"),
								criteriaBuilder.like(districtRoot.get("name"), "%" + keyword + "%"),
								criteriaBuilder.like(wardRoot.get("name"), "%" + keyword + "%")
						));
					}
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}

