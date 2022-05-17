package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.BannerEntity;
import com.training.model.ResponseDataModel;

public interface IBannerService {

	BannerEntity findByBannerId(Long bannerId);

	BannerEntity findByBannerName(String bannerName);

	List<BannerEntity> getAll();

	ResponseDataModel findAllWithPagerApi(int pageNumber);

	ResponseDataModel addApi(BannerEntity bannerEntity);

	ResponseDataModel updateApi(BannerEntity bannerEntity);

	ResponseDataModel findBannerByIdApi(Long bannerId);

	ResponseDataModel deleteApi(Long bannerId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

}
