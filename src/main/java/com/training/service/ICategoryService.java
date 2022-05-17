package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.CategoryEntity;
import com.training.model.ResponseDataModel;

public interface ICategoryService {

	CategoryEntity findByCategoryId(Long categoryId);

	List<CategoryEntity> getAll();

	CategoryEntity findByCategoryName(String categoryName);

	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel addApi(CategoryEntity categoryEntity);

	ResponseDataModel updateApi(CategoryEntity categoryEntity);

	ResponseDataModel findCategoryByIdApi(Long categoryId);

	ResponseDataModel deleteApi(Long categoryId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

	List<CategoryEntity> getAllCategory();

	CategoryEntity findByCategorySlug(String categorySlug);

}
