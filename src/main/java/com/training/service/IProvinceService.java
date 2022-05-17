package com.training.service;

import java.util.List;

import com.training.entity.Province;
import com.training.model.ResponseDataModel;

public interface IProvinceService {

	Province findByAllId(String id);

	List<Province> getAll();

	ResponseDataModel findProvinceByIdApi(String id);

	ResponseDataModel findAllByIdApi(String id);

}
