package com.training.service.impl;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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
import com.training.dao.IPromoDao;
import com.training.dao.jpaspec.PromoJpaSpecification;
import com.training.entity.PromoEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.IPromoService;

@Service
@Transactional
public class PromoServiceImpl implements IPromoService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	IPromoDao promoDao;

	@Override
	public PromoEntity findByPromoId(Long promoId) {
		return promoDao.findByPromoId(promoId);
	}

	@Override
	public List<PromoEntity> getAll() {
		return promoDao.findAll(Sort.by(Sort.Direction.DESC, "promoId"));
	}

	@Override
	public PromoEntity findByPromoName(String promoName) {
		return promoDao.findByPromoName(promoName);
	}
	
	@Override
	public PromoEntity findByPromoCode(String promoCode) {
		return promoDao.findByPromoCode(promoCode);
	}
	
	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "promoId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<PromoEntity> promoEntitiesPage = promoDao.findAll(pageable);
			responseMap.put("promosList", promoEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, promoEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all promo: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel addApi(PromoEntity promoEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByPromoCode(promoEntity.getPromoCode()) != null) {
				responseMsg = "Mã khuyến mãi đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				promoDao.saveAndFlush(promoEntity);
				responseMsg = "Thêm thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when adding promo";
			LOGGER.error("Error when adding promo: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(PromoEntity promoEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {

			PromoEntity duplicatedPromo = promoDao.findByPromoCodeAndPromoIdNot(promoEntity.getPromoCode(),
					promoEntity.getPromoId());

			// Check if brand name existed
			if (duplicatedPromo != null) {
				responseMsg = "Mã khuyến mãi đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				promoDao.saveAndFlush(promoEntity);
				responseMsg = "Cập nhật thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when updating promo";
			LOGGER.error("Errorr when updating promo: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findPromoByIdApi(Long promoId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		PromoEntity promoEntity = null;
		try {
			promoEntity = promoDao.findByPromoId(promoId);
			if (promoEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding promo by ID";
			LOGGER.error("Error when finding promo by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, promoEntity);
	}

	@Override
	public ResponseDataModel findPromoByPromoCodeApi(String promoCode) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		PromoEntity promoEntity = null;
		try {
			promoEntity = promoDao.findByPromoCode(promoCode);			
			if(promoEntity != null) {
				long millis = System.currentTimeMillis();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date promoDate = new Date(millis);
				String promoCurrentDate = sdf.format(promoDate);
				String promoFromDate = sdf.format(promoEntity.getFromDate());
				String promoToDate = sdf.format(promoEntity.getToDate());
				
				if (promoCurrentDate.compareTo(promoFromDate) < 0) {
					responseMsg = "Mã khuyến mãi chưa được áp dụng";
					responseCode = Constants.RESULT_CD_FAIL;
				} else {
					if (promoCurrentDate.compareTo(promoToDate) > 0) {
						responseMsg = "Mã khuyến mãi đã hết hạn";
						responseCode = Constants.RESULT_CD_FAIL;
					}
					else {
						if (promoCurrentDate.compareTo(promoFromDate) >= 0 && promoCurrentDate.compareTo(promoToDate) <= 0) {
							responseMsg = "Áp dụng mã khuyến mãi thành công";
							responseCode = Constants.RESULT_CD_SUCCESS;
						}
					}
				}
			} else {
				responseMsg = "Mã khuyến mãi không tồn tại";
				responseCode = Constants.RESULT_CD_FAIL;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding promo by ID";
			LOGGER.error("Error when finding promo by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, promoEntity);
	}

	@Override
	public ResponseDataModel deleteApi(Long promoId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		PromoEntity promoEntity = promoDao.findByPromoId(promoId);
		try {
			if (promoEntity != null) {
				promoDao.deleteById(promoId);
				promoDao.flush();

				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when deleting promo";
			LOGGER.error("Error when delete promo: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "promoId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<PromoEntity> promoEntitiesPage = promoDao.findAll(PromoJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("promoList", promoEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, promoEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all promo: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
