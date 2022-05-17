package com.training.security;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.training.common.constant.Constants;
import com.training.common.util.FileHelper;
import com.training.dao.IUserDao;
import com.training.dao.jpaspec.UserJpaSpecification;
import com.training.entity.UserEntity;
import com.training.model.PagerModel;
import com.training.model.ResponseDataModel;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Value("${parent.folder.images.user}")
	private String userLogoFolderPath;
	
    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    
    @Override
    public UserEntity findByUserId(Long userId) {
        return userDao.findByUserId(userId);
    }
    
    @Override
    public List<UserEntity> getAll() {
        return userDao.findAll(Sort.by(Sort.Direction.DESC, "userId"));
    }

	@Override
	public ResponseDataModel addApi(UserEntity userEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByUsername(userEntity.getUsername()) != null) {
				responseMsg = "Username đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				if (!userEntity.getPasswordConfirm().equals(userEntity.getPassword())) {
					responseMsg = "Xác nhận mật khẩu mới không đúng";
					responseCode = Constants.RESULT_CD_DUPL;
				} else {
					userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
			        userEntity.setRole("ROLE_USER");
					userDao.saveAndFlush(userEntity);
					responseMsg = "Đăng ký tài khoản thành công";
					responseCode = Constants.RESULT_CD_SUCCESS;
				}
			}
		} catch (Exception e) {
			responseMsg = "Error when registry user";
			LOGGER.error("Error when registry user: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel addAminApi(UserEntity userEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			if (findByUsername(userEntity.getUsername()) != null) {
				responseMsg = "Username đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				if (!userEntity.getPasswordConfirm().equals(userEntity.getPassword())) {
					responseMsg = "Xác nhận mật khẩu mới không đúng";
					responseCode = Constants.RESULT_CD_DUPL;
				} else {
					userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
					userDao.saveAndFlush(userEntity);
					responseMsg = "Đăng ký tài khoản thành công";
					responseCode = Constants.RESULT_CD_SUCCESS;
				}
			}
		} catch (Exception e) {
			responseMsg = "Error when registry user";
			LOGGER.error("Error when registry user: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel findUserByIdApi(Long userId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		UserEntity userEntity = null;
		try {
			userEntity = userDao.findByUserId(userId);
			if (userEntity != null) {
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when finding user by ID";
			LOGGER.error("Error when finding user by ID: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, userEntity);
	}
	
	@Override
	public ResponseDataModel updateApi(UserEntity userEntity) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			
			UserEntity duplicatedUser = userDao.findByUsernameAndUserIdNot(userEntity.getUsername(), userEntity.getUserId());

			// Check if brand name existed
			if (duplicatedUser != null) {
				responseMsg = "Username đã tồn tại";
				responseCode = Constants.RESULT_CD_DUPL;
			} else {
				MultipartFile[] imageFiles = userEntity.getImageFiles();
				if (imageFiles != null && imageFiles[0].getSize() > 0) {
					String imagePath = FileHelper.editFile(userLogoFolderPath, imageFiles, userEntity.getImage());
					userEntity.setImage(imagePath);
				}
				userDao.saveAndFlush(userEntity);
				responseMsg = "Cập nhật thông tin thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch (Exception e) {
			responseMsg = "Error when updating profile";
			LOGGER.error("Errorr when updating profile: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

//	@Override
//	public ResponseDataModel updateApi(Long userId, MultipartFile[] imageFiles, String lastname, String firstname, String phoneNumber) {
//
//		int responseCode = Constants.RESULT_CD_FAIL;
//		String responseMsg = StringUtils.EMPTY;
//		try {
//			UserEntity userEntity = userDao.findByUserId(userId);
//			UserEntity duplicatedUser = userDao.findByUsernameAndUserIdNot(userEntity.getUsername(), userEntity.getUserId());
//
//			// Check if brand name existed
//			if (duplicatedUser != null) {
//				responseMsg = "Username is duplicated";
//				responseCode = Constants.RESULT_CD_DUPL;
//			} else {
//				//MultipartFile[] imageFiles = userEntity.getImageFiles();
//				imageFiles = userEntity.getImageFiles();
//				if (imageFiles != null && imageFiles[0].getSize() > 0) {
//					String imagePath = FileHelper.editFile(userLogoFolderPath, imageFiles, userEntity.getImage());
//					userEntity.setImage(imagePath);
//				}
//				userEntity.setLastname(lastname);
//				userEntity.setFirstname(firstname);
//				userEntity.setPhoneNumber(phoneNumber);
//				userDao.saveAndFlush(userEntity);
//				responseMsg = "Profile is updated successfully";
//				responseCode = Constants.RESULT_CD_SUCCESS;
//			}
//		} catch (Exception e) {
//			responseMsg = "Error when updating profile";
//			LOGGER.error("Errorr when updating profile: ", e);
//		}
//		return new ResponseDataModel(responseCode, responseMsg);
//	}
	
	@Override
	public ResponseDataModel changePasswordApi(String currentPassword, String newPassword, String confirmPassword,Principal principal) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		try {
			
			String username = principal.getName();
			UserEntity userEntity = userDao.findByUsername(username);

			// Check if brand name existed
			if (bCryptPasswordEncoder.matches(currentPassword, userEntity.getPassword()) && confirmPassword.equals(newPassword)) {
				userEntity.setPassword(bCryptPasswordEncoder.encode(newPassword));
				userDao.saveAndFlush(userEntity);
				responseMsg = "Đổi mật khẩu thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			} else {
				if (!confirmPassword.equals(newPassword)) {
					responseMsg = "Xác nhận mật khẩu mới không đúng";
					responseCode = Constants.RESULT_CD_DUPL;
				} else {
					responseMsg = "Mật khẩu hiện tại không đúng";
					responseCode = Constants.RESULT_CD_FAIL;
				}
			}
		} catch (Exception e) {
			responseMsg = "Error when updating profile";
			LOGGER.error("Errorr when updating profile: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}

	@Override
	public ResponseDataModel deleteApi(Long userId) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		UserEntity userEntity = userDao.findByUserId(userId);
		try {
			if (userEntity != null) {
				userDao.deleteById(userId);
				userDao.flush();

				// Remove logo of brand from store folder
				FileHelper.deleteFile(userEntity.getImage());
				responseMsg = "Xóa thành công";
				responseCode = Constants.RESULT_CD_SUCCESS;
			}
		} catch(Exception e) {
			responseMsg = "Error when deleting user";
			LOGGER.error("Error when delete user: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg);
	}
	
	@Override
	public ResponseDataModel searchWithConditions(Map<String, Object> searchConditionsWeb, int pageNumber) {

		int responseCode = Constants.RESULT_CD_FAIL;
		String responseMsg = StringUtils.EMPTY;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			Sort sortInfo = Sort.by(Sort.Direction.DESC, "userId");
			Pageable pageable = PageRequest.of(pageNumber - 1, Constants.PAGE_SIZE, sortInfo);
			Page<UserEntity> userEntitiesPage = userDao.findAll(UserJpaSpecification.getSearchCriteria(searchConditionsWeb), pageable);
			responseMap.put("userList", userEntitiesPage.getContent());
			responseMap.put("paginationInfo", new PagerModel(pageNumber, userEntitiesPage.getTotalPages()));
			responseCode = Constants.RESULT_CD_SUCCESS;
		} catch (Exception e) {
			responseMsg = e.getMessage();
			LOGGER.error("Error when get all user: ", e);
		}
		return new ResponseDataModel(responseCode, responseMsg, responseMap);
	}
}
