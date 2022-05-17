package com.training.security;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.training.entity.UserEntity;
import com.training.model.ResponseDataModel;

public interface UserService {

    UserEntity findByUsername(String username);

	List<UserEntity> getAll();

	ResponseDataModel addApi(UserEntity userEntity);

	UserEntity findByUserId(Long userId);

	ResponseDataModel findUserByIdApi(Long userId);

	ResponseDataModel updateApi(UserEntity userEntity);

	ResponseDataModel addAminApi(UserEntity userEntity);
	
	ResponseDataModel deleteApi(Long userId);

	ResponseDataModel searchWithConditions(Map<String, Object> searchConditionsWeb, int pageNumber);

	ResponseDataModel changePasswordApi(String currentPassword, String newPassword, String confirmPassword, Principal principal);
	
}
