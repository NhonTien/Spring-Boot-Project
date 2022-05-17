package com.training.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.ProductEntity;

@Repository
public interface IProductDao extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

	ProductEntity findByProductId(Long productId);

	ProductEntity findByProductName(String productName);

	ProductEntity findByProductNameAndProductIdNot(String productName, Long productId);
	
	ProductEntity findByProductSlug(String productSlug);
	
	List<ProductEntity> findByCategoryEntity_CategoryId(Long categoryId);

	List<ProductEntity> findByCategoryEntity_CategoryId(Long categoryId, Sort sortInfo);

	Page<ProductEntity> findByCategoryEntity_CategoryId(Long categoryId, Pageable pageable);

	ProductEntity findByProductSlugAndProductIdNot(String productSlug, Long productId);

}
