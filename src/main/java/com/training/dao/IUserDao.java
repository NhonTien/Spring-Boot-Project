package com.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.training.entity.UserEntity;

public interface IUserDao extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findByUsername(String username);

	UserEntity findByUserId(Long userId);

	UserEntity findByUsernameAndUserIdNot(String username, Long userId);
}
