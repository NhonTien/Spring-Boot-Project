package com.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.training.common.constant.Constants;
import com.training.common.util.FileHelper;
import com.training.dao.IBrandDao;
import com.training.dao.jpaspec.BrandJpaSpecification;
import com.training.entity.BrandEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.IBrandService;

@Service
@Transactional
public class BrandServiceImpl implements IBrandService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Value("${parent.folder.images.brand}")
	private String brandLogoFolderPath;

	@Autowired
	IBrandDao brandDao;

	@Override
	public BrandEntity findByBrandId(Long brandId) {
		return brandDao.findByBrandId(brandId);
	}

	@Override
	public List<BrandEntity> getAll() {
		return brandDao.findAll(Sort.by(Sort.Direction.ASC, "brandName"));
	}

	@Override
	public BrandEntity findByBrandName(String brandName) {
		return brandDao.findByBrandName(brandName);
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "brandId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<BrandEntity> brandEntitiesPage = brandDao.findAll(pageable);
			responseMap.put("brandsList", brandEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, brandEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel addApi(BrandEntity brandEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByBrandName(brandEntity.getBrandName()) != null) {
				responseMsg = "Thương hiệu đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				MultipartFile[] logoFiles = brandEntity.getLogoFiles();
				if (logoFiles != null && logoFiles[0].getSize() > 0) {
					String imagePath = FileHelper.addNewFile(brandLogoFolderPath, logoFiles);
					brandEntity.setLogo(imagePath);
				}
				brandDao.saveAndFlush(brandEntity);
				responseMsg = "Thêm thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when adding brand";
			LOGGER.error("Error when adding brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(BrandEntity brandEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			
			BrandEntity duplicatedBrand = brandDao.findByBrandNameAndBrandIdNot(brandEntity.getBrandName(), brandEntity.getBrandId());

			// Check if brand name existed
			if (duplicatedBrand != null) {
				responseMsg = "Thương hiệu đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				MultipartFile[] logoFiles = brandEntity.getLogoFiles();
				if (logoFiles != null && logoFiles[0].getSize() > 0) {
					String imagePath = FileHelper.editFile(brandLogoFolderPath, logoFiles, brandEntity.getLogo());
					brandEntity.setLogo(imagePath);
				}
				brandDao.saveAndFlush(brandEntity);
				responseMsg = "Cập nhật thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when updating brand";
			LOGGER.error("Errorr when updating brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findBrandByIbApi(Long brandId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		BrandEntity brandEntity = null;
		try {
			brandEntity = brandDao.findByBrandId(brandId);
			if (brandEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding brand by ID";
			LOGGER.error("Error when finding brand by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, brandEntity);
	}

	@Override
	public ResponseDataModel deleteApi(Long brandId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		BrandEntity brandEntity = brandDao.findByBrandId(brandId);
		try {
			if (brandEntity != null) {
				brandDao.deleteById(brandId);
				brandDao.flush();

				// Remove logo of brand from store folder
				FileHelper.deleteFile(brandEntity.getLogo());
				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch(Exception e) {
			responseMsg = "Error when deleting brand";
			LOGGER.error("Error when delete brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "brandId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<BrandEntity> brandEntitiesPage = brandDao.findAll(BrandJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("brandList", brandEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, brandEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}