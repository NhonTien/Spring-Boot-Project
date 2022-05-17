package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.ProductEntity;
import com.training.model.ResponseDataModel;

public interface IProductService {

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

	ProductEntity findByProductId(Long productId);

	List<ProductEntity> getAll();

	ProductEntity findByProductName(String productName);

	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel addApi(ProductEntity productEntity);

	ResponseDataModel updateApi(ProductEntity productEntity);

	ResponseDataModel findBrandByIdApi(Long productId);

	ResponseDataModel deleteApi(Long productId);

	ResponseDataModel searchWithConditionsWeb(Map<String, Object> searchConditionsWeb, int pageNumber);
	
	List<ProductEntity> findAllByCategoryId(Long categoryId);

	ResponseDataModel findAllProductByCategoryIdApi(Long categoryId);

	ResponseDataModel findAllWithPagerApi(Long categoryId, int pageNumber);

	ProductEntity findByProductSlug(String productSlug);

}
