package com.training.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.common.constant.Constants;
import com.training.dao.IFeeShipDao;
import com.training.dao.jpaspec.FeeShipJpaSpecification;
import com.training.entity.FeeShipEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.IFeeShipService;

@Service
@Transactional
public class FeeShipServiceImpl implements IFeeShipService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IFeeShipDao feeShipDao;

	@Override
	public FeeShipEntity findByFeeShipId(Long feeShipId) {
		return feeShipDao.findByFeeShipId(feeShipId);
	}
	
	@Override
	public FeeShipEntity findByWardId(String id) {
		return feeShipDao.findByWard_Id(id);
	}
	
	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "feeShipId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<FeeShipEntity> feeShipEntitiesPage = feeShipDao.findAll(pageable);
			responseMap.put("feeShipsList", feeShipEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, feeShipEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all fee ship: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel addApi(FeeShipEntity feeShipEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByWardId(feeShipEntity.getWard().getId()) != null) {
				responseMsg = "Phí giao hàng đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				feeShipDao.saveAndFlush(feeShipEntity);
				responseMsg = "Thêm thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when adding fee ship";
			LOGGER.error("Error when adding fee ship: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(FeeShipEntity feeShipEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {

			FeeShipEntity duplicatedFeeShip = feeShipDao.findByWard_IdAndFeeShipIdNot(feeShipEntity.getWard().getId(),
					feeShipEntity.getFeeShipId());

			// Check if brand name existed
			if (duplicatedFeeShip != null) {
				responseMsg = "Phí giao hàng đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				feeShipDao.saveAndFlush(feeShipEntity);
				responseMsg = "Cập nhật thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when updating fee ship";
			LOGGER.error("Errorr when updating fee ship: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findFeeShipByIdApi(Long feeShipId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		FeeShipEntity feeShipEntity = null;
		try {
			feeShipEntity = feeShipDao.findByFeeShipId(feeShipId);
			if (feeShipEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding fee ship by ID";
			LOGGER.error("Error when finding fee ship by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, feeShipEntity);
	}
	
	@Override
	public ResponseDataModel findFeeShipByWardIdApi(String id) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		FeeShipEntity feeShipEntity = null;
		try {
			feeShipEntity = feeShipDao.findByWard_Id(id);
			if (feeShipEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding fee ship by ID";
			LOGGER.error("Error when finding fee ship by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, feeShipEntity);
	}

	@Override
	public ResponseDataModel deleteApi(Long feeShipId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		FeeShipEntity feeShipEntity = feeShipDao.findByFeeShipId(feeShipId);
		try {
			if (feeShipEntity != null) {
				feeShipDao.deleteById(feeShipId);
				feeShipDao.flush();

				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when deleting fee ship";
			LOGGER.error("Error when delete fee ship: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "feeShipId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<FeeShipEntity> feeShipEntitiesPage = feeShipDao.findAll(FeeShipJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("feeShipList", feeShipEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, feeShipEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all fee ship: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
