package com.training.service;

import java.util.Map;

import com.training.entity.FeeShipEntity;
import com.training.model.ResponseDataModel;

public interface IFeeShipService {

	FeeShipEntity findByFeeShipId(Long feeShipId);
	
	FeeShipEntity findByWardId(String id);

	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel addApi(FeeShipEntity feeShipEntity);

	ResponseDataModel updateApi(FeeShipEntity feeShipEntity);

	ResponseDataModel findFeeShipByIdApi(Long feeShipId);
	
	ResponseDataModel findFeeShipByWardIdApi(String id);

	ResponseDataModel deleteApi(Long feeShipId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

}
