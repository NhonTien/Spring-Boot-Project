package com.training.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.training.common.constant.Constants;
import com.training.dao.IDistrictDao;
import com.training.dao.IProvinceDao;
import com.training.entity.District;
import com.training.entity.Province;
import com.training.model.ResponseDataModel;
import com.training.service.IProvinceService;

@Service
@Transactional
public class ProvinceServiceImpl implements IProvinceService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IProvinceDao provinceDao;
	
	@Autowired
	IDistrictDao districtDao;

	@Override
	public Province findByAllId(String id) {
		return provinceDao.findAllById(id);
	}

	@Override
	public List<Province> getAll() {
		//return brandDao.findAll(Sort.by(Sort.Direction.DESC, "brandId"));
		return provinceDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public ResponseDataModel findProvinceByIdApi(String id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Province province = null;
		try {
			province = provinceDao.findAllById(id);
			if (province != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding province by ID";
			LOGGER.error("Error when finding province by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, province);
	}

	@Override
	public ResponseDataModel findAllByIdApi(String id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<District> districtEntities = null;
		try {
			districtEntities = districtDao.findAllByProvince_Id(id);
			if (districtEntities != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding province by ID";
			LOGGER.error("Error when finding province by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, districtEntities);
	}
}
