package com.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.training.common.constant.Constants;
import com.training.dao.ICommentDao;
import com.training.dao.jpaspec.CommentJpaSpecification;
import com.training.entity.CommentEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;
import com.training.service.ICommentService;

@Service
@Transactional
public class CommentServiceImpl implements ICommentService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ICommentDao commentDao;

	@Override
	public CommentEntity findByCommentId(Long commentId) {
		return commentDao.findByCommentId(commentId);
	}

	@Override
	public List<CommentEntity> findAllByCommentProductId(Long productId) {
		return commentDao.findByProductEntity_ProductId(productId);
	}

	@Override
	public List<CommentEntity> getAll() {
		return commentDao.findAll(Sort.by(Sort.Direction.DESC, "commentId"));
	}

	@Override
	public ResponseDataModel findAllCommentByIdApi(Long productId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		List<CommentEntity> commentEntity = null;
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "commentId");
			commentEntity = commentDao.findByProductEntity_ProductId(productId, sortInfo);
			if (commentEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding product by ID";
			LOGGER.error("Error when finding product by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, commentEntity);
	}

	@Override
	public ResponseDataModel addApi(CommentEntity commentEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
				commentDao.saveAndFlush(commentEntity);
				responseMsg = "Comment is added successfully";
				responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = "Error when adding product";
			LOGGER.error("Error when adding product: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel findAllWithPagerApi(Long productId, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {
			Sort sortInfo = Sort.by(Sort.Direction.DESC, "commentId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<CommentEntity> commentEntitiesPage = commentDao.findByProductEntity_ProductId(productId, pageable);
			responseMap.put("commentsList", commentEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, commentEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all comment: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
	
	@Override
	public ResponseDataModel deleteApi(Long commentId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		CommentEntity commentEntity = commentDao.findByCommentId(commentId);
		try {
			if (commentEntity != null) {
				commentDao.deleteById(commentId);
				commentDao.flush();
				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch(Exception e) {
			responseMsg = "Error when deleting comment";
			LOGGER.error("Error when delete comment: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "commentId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<CommentEntity> commentEntitiesPage = commentDao.findAll(CommentJpaSpecification.getSearchCriteria(searchConditions), pageable);
			responseMap.put("commentList", commentEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, commentEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all comment: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
