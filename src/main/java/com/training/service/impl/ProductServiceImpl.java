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
import com.training.dao.IOrderDetailDao;
import com.training.dao.IProductDao;
import com.training.dao.jpaspec.ProductJpaSpecification;
import com.training.entity.OrderDetail;
import com.training.entity.ProductEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.IProductService;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Value("${parent.folder.images.product}")
	private String productLogoFolderPath;
	
	@Autowired
	IProductDao productDao;
	
	@Override
	public ProductEntity findByProductId(Long productId) {
		return productDao.findByProductId(productId);
	}

	@Override
	public List<ProductEntity> getAll() {
		return productDao.findAll(Sort.by(Sort.Direction.DESC, "productId"));
	}

	@Override
	public ProductEntity findByProductName(String productName) {
		return productDao.findByProductName(productName);
	}
	
	@Override
	public List<ProductEntity> findAllByCategoryId(Long categoryId) {
		return productDao.findByCategoryEntity_CategoryId(categoryId);
	}

	@Override
	public ProductEntity findByProductSlug(String productSlug) {
		return productDao.findByProductSlug(productSlug);
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "productId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<ProductEntity> productEntitiesPage = productDao.findAll(pageable);
			responseMap.put("productsList", productEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, productEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all product: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}

	@Override
	public ResponseDataModel addApi(ProductEntity productEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByProductName(productEntity.getProductName()) != null) {
				responseMsg = "Sản phẩm đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				if (findByProductSlug(productEntity.getProductSlug()) != null) {
					responseMsg = "Slug đã tồn tại";
					responseCode = Constants.RESULT_CD_DUPL;
				} else {
					MultipartFile[] imageFiles = productEntity.getImageFiles();
					if (imageFiles != null && imageFiles[0].getSize() > 0) {
						String imagePath = FileHelper.addNewFile(productLogoFolderPath, imageFiles);
						productEntity.setImage(imagePath);
					}
					productDao.saveAndFlush(productEntity);
					responseMsg = "Thêm thành công";
					responseCode = Constants.RESULT_CD_SUCCESS;
				}
			}
		} catch (Exception e) {
			responseMsg = "Error when adding product";
			LOGGER.error("Error when adding product: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel updateApi(ProductEntity productEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			
			ProductEntity duplicatedProduct = productDao.findByProductNameAndProductIdNot(productEntity.getProductName(), productEntity.getProductId());
			ProductEntity duplicatedProductSlug = productDao.findByProductSlugAndProductIdNot(productEntity.getProductSlug(), productEntity.getProductId());
			// Check if brand name existed
			if (duplicatedProduct != null) {
				responseMsg = "Sản phẩm đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				if (duplicatedProductSlug != null) {
					responseMsg = "Slug đã tồn tại";
					responseCode = Constants.RESULT_CD_DUPL;
				} else {
					MultipartFile[] imageFiles = productEntity.getImageFiles();
					if (imageFiles != null && imageFiles[0].getSize() > 0) {
						String imagePath = FileHelper.editFile(productLogoFolderPath, imageFiles, productEntity.getImage());
						productEntity.setImage(imagePath);
					}
					productDao.saveAndFlush(productEntity);
					responseMsg = "Cập nhật thành công";
					responseCode = Constants.RESULT_CD_SUCCESS;
				}
			} 
		} catch (Exception e) {
			responseMsg = "Error when updating product: ";
			LOGGER.error("Errorr when updating product: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findBrandByIdApi(Long productId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		ProductEntity productEntity = null;
		try {
			productEntity = productDao.findByProductId(productId);
			if (productEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding product by ID";
			LOGGER.error("Error when finding product by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, productEntity);
	}

//	@Override
//	public ResponseDataModel deleteApi(Long productId) {
//
//		int responseCode = Constants.RESULT_CD_FAIL;
//		String responseMsg = StringUtils.EMPTY;
//		ProductEntity productEntity = productDao.findByProductId(productId);
//		try {
//			if (productEntity != null) {
//				productDao.deleteById(productId);
//				productDao.flush();
//
//				// Remove logo of brand from store folder
//				FileHelper.deleteFile(productEntity.getImage());
//				responseMsg = "Product is deleted successfully";
//				responseCode = Constants.RESULT_CD_SUCCESS;
//			}
//		} catch(Exception e) {
//			responseMsg = "Error when deleting product";
//			LOGGER.error("Error when delete product: ", e);
//		}
//		return new ResponseDataModel(responseCode, responseMsg);
//	}
	
	@Autowired
	IOrderDetailDao orderDetailDao;
	
	@Override
	public ResponseDataModel deleteApi(Long productId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		ProductEntity productEntity = productDao.findByProductId(productId);
		List<OrderDetail> orderDetails = orderDetailDao.findAllByProductEntity_ProductId(productId);
		long countConfirm = 0;
		for (OrderDetail orderDetail : orderDetails) {
			if(orderDetail.getOrder().getConfirm().equals(false) && orderDetail.getOrder().getAvailable().equals(true)) {
				countConfirm++;
			}
		}
		System.out.println("CountConfirm= " + countConfirm);
		try {
			if(orderDetails != null && countConfirm != 0){
				//responseMsg = "Product has been ordered";
				responseMsg = "Tồn tại đơn hàng chưa được xác nhận";
				responseCode = Constants.RESULT_CD_FAIL;
			} else {
				if(productEntity != null && orderDetails != null && countConfirm == 0) {
					productDao.deleteById(productId);
					productDao.flush();
	
					// Remove logo of brand from store folder
					FileHelper.deleteFile(productEntity.getImage());
					responseMsg = "Xóa thành công";
					responseCode = Constants.RESULT_CD_SUCCESS;
				} else {
					responseMsg = "Đã xảy ra lỗi";
					responseCode = Constants.RESULT_CD_FAIL;
				}
			}
		} catch(Exception e) {
			responseMsg = "Error when deleting product";
			LOGGER.error("Error when delete product: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "productId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<ProductEntity> productEntitiesPage = productDao.findAll(ProductJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("productList", productEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, productEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
	
	@Override
	public ResponseDataModel searchWithConditionsWeb(Map<String, Object> searchConditionsWeb, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "productId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE_WEB, sortInfo);
			Page<ProductEntity> productEntitiesPage = productDao.findAll(ProductJpaSpecification.getSearchCriteria(searchConditionsWeb), pageable);
			responseMap.put("productListWeb", productEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, productEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all brand: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
	
	@Override
	public ResponseDataModel findAllProductByCategoryIdApi(Long categoryId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<ProductEntity> productEntity = null;
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "productId");
			productEntity = productDao.findByCategoryEntity_CategoryId(categoryId, sortInfo);
			if (productEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding product by ID";
			LOGGER.error("Error when finding product by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, productEntity);
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(Long categoryId, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "productId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE_WEB, sortInfo);
			Page<ProductEntity> productEntitiesPage = productDao.findByCategoryEntity_CategoryId(categoryId, pageable);
			responseMap.put("productsList", productEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, productEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all product: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}