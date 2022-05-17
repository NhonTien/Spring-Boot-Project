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
import com.training.dao.IWardDao;
import com.training.entity.Ward;
import com.training.model.ResponseDataModel;
import com.training.service.IWardService;

@Service
@Transactional
public class WardServiceImpl implements IWardService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IWardDao wardDao;

	@Override
	public Ward findAllById(String id) {
		return wardDao.findAllById(id);
	}

	@Override
	public List<Ward> getAll() {
		//return brandDao.findAll(Sort.by(Sort.Direction.DESC, "brandId"));
		return wardDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}

	@Override
	public ResponseDataModel findWardByIdApi(String id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Ward ward = null;
		try {
			ward = wardDao.findAllById(id);
			if (ward != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding ward by ID";
			LOGGER.error("Error when finding ward by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, ward);
	}
}
