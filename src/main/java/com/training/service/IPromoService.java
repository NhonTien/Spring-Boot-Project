package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.PromoEntity;
import com.training.model.ResponseDataModel;

public interface IPromoService {

	PromoEntity findByPromoId(Long promoId);

	List<PromoEntity> getAll();

	PromoEntity findByPromoName(String promoName);
	
	PromoEntity findByPromoCode(String promoCode);

	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel addApi(PromoEntity promoEntity);

	ResponseDataModel updateApi(PromoEntity promoEntity);

	ResponseDataModel findPromoByIdApi(Long promoId);
	
	ResponseDataModel findPromoByPromoCodeApi(String promoCode);

	ResponseDataModel deleteApi(Long promoId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

}
