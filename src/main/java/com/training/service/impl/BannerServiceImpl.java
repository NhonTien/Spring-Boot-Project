package com.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
import org.springframework.web.multipart.MultipartFile;

import com.training.common.constant.Constants;
import com.training.common.util.FileHelper;
import com.training.dao.IBannerDao;
import com.training.dao.jpaspec.BannerJpaSpecification;
import com.training.entity.BannerEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.IBannerService;

@Service
@Transactional
public class BannerServiceImpl implements IBannerService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Value("${parent.folder.images.banner}")
	private String bannerLogoFolderPath;
	
	@Autowired
	IBannerDao bannerDao;

	@Override
	public BannerEntity findByBannerId(Long bannerId) {
		return bannerDao.findByBannerId(bannerId);
	}

	@Override
	public BannerEntity findByBannerName(String bannerName) {
		return bannerDao.findByBannerName(bannerName);
	}

	@Override
	public List<BannerEntity> getAll() {
		return bannerDao.findAll(Sort.by(Sort.Direction.ASC, "bannerId"));
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "bannerId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<BannerEntity> bannerEntitiesPage = bannerDao.findAll(pageable);
			responseMap.put("bannersList", bannerEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, bannerEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all banner: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel addApi(BannerEntity bannerEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByBannerName(bannerEntity.getBannerName()) != null) {
				responseMsg = "Banner đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				MultipartFile[] imageFiles = bannerEntity.getImageFiles();
				if (imageFiles != null && imageFiles[0].getSize() > 0) {
					String imagePath = FileHelper.addNewFile(bannerLogoFolderPath, imageFiles);
					bannerEntity.setImage(imagePath);
				}
				bannerDao.saveAndFlush(bannerEntity);
				responseMsg = "Thêm thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when adding banner";
			LOGGER.error("Error when adding banner: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(BannerEntity bannerEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			
			BannerEntity duplicatedBanner = bannerDao.findByBannerNameAndBannerIdNot(bannerEntity.getBannerName(),bannerEntity.getBannerId());

			// Check if brand name existed
			if (duplicatedBanner != null) {
				responseMsg = "Banner đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				MultipartFile[] imageFiles = bannerEntity.getImageFiles();
				if (imageFiles != null && imageFiles[0].getSize() > 0) {
					String imagePath = FileHelper.editFile(bannerLogoFolderPath, imageFiles, bannerEntity.getImage());
					bannerEntity.setImage(imagePath);
				}
				bannerDao.saveAndFlush(bannerEntity);
				responseMsg = "Cập nhật thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when updating banner: ";
			LOGGER.error("Error when updating banner: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findBannerByIdApi(Long bannerId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		BannerEntity bannerEntity = null;
		try {
			bannerEntity = bannerDao.findByBannerId(bannerId);
			if (bannerEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding banner by ID";
			LOGGER.error("Error when finding banner by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, bannerEntity);
	}

	@Override
	public ResponseDataModel deleteApi(Long bannerId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		BannerEntity bannerEntity = bannerDao.findByBannerId(bannerId);
		try {
			if (bannerEntity != null) {
				bannerDao.deleteById(bannerId);
				bannerDao.flush();

				// Remove logo of brand from store folder
				FileHelper.deleteFile(bannerEntity.getImage());
				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch(Exception e) {
			responseMsg = "Error when deleting banner";
			LOGGER.error("Error when delete banner: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "bannerId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<BannerEntity> bannerEntitiesPage = bannerDao.findAll(BannerJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("bannerList", bannerEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, bannerEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all banner: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
