package com.training.service;

import java.util.List;

import com.training.entity.Ward;
import com.training.model.ResponseDataModel;

public interface IWardService {

	Ward findAllById(String id);

	List<Ward> getAll();

	ResponseDataModel findWardByIdApi(String id);

}
