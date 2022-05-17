package com.training.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.training.entity.CommentEntity;

@Repository
public interface ICommentDao extends JpaRepository<CommentEntity, Long>, JpaSpecificationExecutor<CommentEntity> {
	
	CommentEntity findByCommentId(Long commentId);
	
	List<CommentEntity> findByProductEntity_ProductId(Long productId);

	List<CommentEntity> findByProductEntity_ProductId(Long productId, Sort sortInfo);

	Page<CommentEntity> findByProductEntity_ProductId(Long productId, Pageable pageable);

}
