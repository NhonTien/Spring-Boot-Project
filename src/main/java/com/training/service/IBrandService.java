package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.BrandEntity;
import com.training.model.ResponseDataModel;

public interface IBrandService {

	List<BrandEntity> getAll();

	BrandEntity findByBrandId(Long brandId);

	BrandEntity findByBrandName(String brandName);
	
	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel addApi(BrandEntity brandEntity);

	ResponseDataModel updateApi(BrandEntity brandEntity);

	ResponseDataModel findBrandByIbApi(Long brandId);

	ResponseDataModel deleteApi(Long brandId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);
}
