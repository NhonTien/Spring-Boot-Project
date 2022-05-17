package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.CategoryEntity;

@Repository
public interface ICategoryDao extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity> {

	CategoryEntity findByCategoryId(Long categoryId);

	CategoryEntity findByCategoryName(String categoryName);

	CategoryEntity findByCategoryNameAndCategoryIdNot(String categoryName, Long categoryId);

	CategoryEntity findByCategorySlug(String categorySlug);
}
