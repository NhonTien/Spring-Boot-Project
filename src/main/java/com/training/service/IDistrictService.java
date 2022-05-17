package com.training.service;

import java.util.List;

import com.training.entity.District;
import com.training.model.ResponseDataModel;

public interface IDistrictService {

	District findAllById(String id);

	List<District> getAll();

	ResponseDataModel findDistrictByIdApi(String id);

	ResponseDataModel findAllByIdApi(String id);

}
