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
import com.training.dao.IWardDao;
import com.training.entity.District;
import com.training.entity.Ward;
import com.training.model.ResponseDataModel;
import com.training.service.IDistrictService;

@Service
@Transactional
public class DistrictServiceImpl implements IDistrictService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IDistrictDao districtDao;
	
	@Autowired
	IWardDao wardDao;

	@Override
	public District findAllById(String id) {
		return districtDao.findAllById(id);
	}

	@Override
	public List<District> getAll() {
		//return brandDao.findAll(Sort.by(Sort.Direction.DESC, "brandId"));
		return districtDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public ResponseDataModel findDistrictByIdApi(String id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		District district = null;
		try {
			district = districtDao.findAllById(id);
			if (district != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding district by ID";
			LOGGER.error("Error when finding district by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, district);
	}

	@Override
	public ResponseDataModel findAllByIdApi(String id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<Ward> districtEntities = null;
		try {
			districtEntities = wardDao.findAllByDistrict_Id(id);
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
