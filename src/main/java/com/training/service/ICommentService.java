package com.training.service;

import java.util.List;
import java.util.Map;

import com.training.entity.CommentEntity;
import com.training.model.ResponseDataModel;

public interface ICommentService {

	CommentEntity findByCommentId(Long commentId);

	List<CommentEntity> getAll();

	ResponseDataModel findAllCommentByIdApi(Long productId);

	List<CommentEntity> findAllByCommentProductId(Long productId);

	ResponseDataModel addApi(CommentEntity commentEntity);

	ResponseDataModel findAllWithPagerApi(Long productId, int pageNumber);

	ResponseDataModel deleteApi(Long commentId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditions, int pageNumber);

}
