package com.training.service.impl;

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
import com.training.dao.ICategoryDao;
import com.training.dao.jpaspec.CategoryJpaSpecification;
import com.training.entity.CategoryEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.ICategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	ICategoryDao categoryDao;

	@Override
	public CategoryEntity findByCategoryId(Long categoryId) {
		return categoryDao.findByCategoryId(categoryId);
	}

	@Override
	public List<CategoryEntity> getAll() {
		return categoryDao.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
	}
	
	@Override
	public List<CategoryEntity> getAllCategory() {
		return categoryDao.findAll();
	}

	@Override
	public CategoryEntity findByCategoryName(String categoryName) {
		return categoryDao.findByCategoryName(categoryName);
	}
	
	@Override
	public CategoryEntity findByCategorySlug(String categorySlug) {
		return categoryDao.findByCategorySlug(categorySlug);
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "categoryId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<CategoryEntity> categoryEntitiesPage = categoryDao.findAll(pageable);
			responseMap.put("categoriesList", categoryEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, categoryEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all category: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel addApi(CategoryEntity categoryEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByCategoryName(categoryEntity.getCategoryName()) != null) {
				responseMsg = "Danh mục đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				categoryDao.saveAndFlush(categoryEntity);
				responseMsg = "Thêm thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when adding category";
			LOGGER.error("Error when adding category: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(CategoryEntity categoryEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {

			CategoryEntity duplicatedCategory = categoryDao.findByCategoryNameAndCategoryIdNot(categoryEntity.getCategoryName(),
					categoryEntity.getCategoryId());

			// Check if brand name existed
			if (duplicatedCategory != null) {
				responseMsg = "Danh mục đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				categoryDao.saveAndFlush(categoryEntity);
				responseMsg = "Cập nhật thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when updating category";
			LOGGER.error("Errorr when updating category: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findCategoryByIdApi(Long categoryId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		CategoryEntity categoryEntity = null;
		try {
			categoryEntity = categoryDao.findByCategoryId(categoryId);
			if (categoryEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding category by ID";
			LOGGER.error("Error when finding category by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, categoryEntity);
	}

	@Override
	public ResponseDataModel deleteApi(Long categoryId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		CategoryEntity categoryEntity = categoryDao.findByCategoryId(categoryId);
		try {
			if (categoryEntity != null) {
				categoryDao.deleteById(categoryId);
				categoryDao.flush();

				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when deleting category";
			LOGGER.error("Error when delete category: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "categoryId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<CategoryEntity> categoryEntitiesPage = categoryDao.findAll(CategoryJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("categoryList", categoryEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, categoryEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all category: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
