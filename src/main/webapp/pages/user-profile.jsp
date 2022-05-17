<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>Quản lý thông tin cá nhân</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/profile.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/webnav.css'/>">
</head>
<body>
	<jsp:include page="header.jsp" />
	
		<div class="container">
			<div class="sub-header">
				<div class="float-left sub-title">Quản lý Thông tin cá nhân</div>
				<div class="float-right"><a class="btn btn-success add-btn" id="userChangePasswordInfoModal"><i class="fas fa-key"></i> Đổi mật khẩu</a></div>
			</div>
		</div>
		<div class="profile">
			<div class="columns-lefts">
				<div class="form-group" id="userImage">
					<div class="preview-image-upload" id="logoImgs">
						<img src="<c:url value='/images/image-user.png'/>" alt="image">
					</div>
					<a class="btn edit-btn" id="editProfileInfoModal"><i class="fas fa-edit"></i> Cập nhật</a>
				</div>
			</div>
			<div class="columns-rights">
				<div class="info">
					<div class="form-group col-md-6">
						<label for="lastname">Họ</label>
						<input type="text" class="form-control" id="lastname1" name="lastname" placeholder="Họ" readonly>
					</div>
					<div class="form-group col-md-6">
						<label for="firstname">Tên</label>
						<input type="text" class="form-control" id="firstname1" name="firstname" placeholder="Tên" readonly>
					</div>
				</div>
				<div class="form-group col-md-12">
					<label for="phoneNumber">Số điện thoại</label> 
					<input type="text" class="form-control" id="phoneNumber1" name="phoneNumber" placeholder="Số điện thoại" readonly>
				</div>
				<div class="form-group col-md-12">
					<label for="username">Username</label>
					<input type="text" class="form-control" id="username1" name="username" placeholder="Username" readonly>
				</div>
			</div>
		</div>
		<!-- Modal Add and Edit Product -->
		<div class="modal fade" id="userInfoModal">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<form id="userInfoForm" role="form" enctype="multipart/form-data">
						<div class="modal-header">
							<h5 class="modal-title">Cập nhật thông tin</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="form-group d-none">
								<label>ID</label>
								<input type="text" class="form-control" name="userId" id="userId" placeholder="ID" readonly>
							</div>
							<div class="form-group" id="userImage">
								<label for="logo">Ảnh đại diện</label>
								<div class="preview-image-upload" id="logoImg">
									<img src="<c:url value='/images/image-user.png'/>" alt="image">
								</div>
								<input type="file" class="form-control upload-image" name="imageFiles" accept="image/*" /> 
								<input type="hidden" class="old-img" id="image" name="image">
							</div>
							<div class="form-group">
								<label for="lastname">Họ <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="lastname" name="lastname" placeholder="Họ">
							</div>
							<div class="form-group">
								<label for="firstname">Tên <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Tên">
							</div>
							<div class="form-group">
								<label for="phoneNumber">Số điện thoại <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Số điện thoại">
							</div>
							<!--  --><div class="form-group d-none">
								<label for="username">Username <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="username" name="username" placeholder="Username">
							</div>
							<div class="form-group d-none">
								<label for="password">Mật khẩu <span class="required-mask">(*)</span></label>
								<input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu">
							</div>
							<div class="form-group d-none">
								<label for="role">Quyền <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="role" name="role" placeholder="Quyền">
							</div>
						</div>
						<div class="modal-footer">
							<a href="/"><button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button></a>
							<button type="button" class="btn btn-primary" id="saveUserProfileBtn">Cập nhật</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="changePasswordInfoModal">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<form id="changePasswordInfoForm" role="form" enctype="multipart/form-data">
						<div class="modal-header">
							<h5 class="modal-title">Đổi mật khẩu</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input type="hidden" class="form-control" id="checkPassword">
							</div>
							<div class="form-group">
								<label for="currentPassword">Mật khẩu hiện tại <span class="required-mask">(*)</span></label>
								<input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Mật khẩu hiện tại">
							</div>
							<div class="form-group">
								<label for="newPassword">Mật khẩu mới <span class="required-mask">(*)</span></label>
								<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Mật khẩu mới">
							</div>
							<div class="form-group">
								<label for="confirmPassword">Nhập lại mật khẩu mới <span class="required-mask">(*)</span></label>
								<input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Nhập lại mật khẩu mới">
							</div>
						</div>
						<div class="modal-footer">
							<a href="/"><button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button></a>
							<button type="button" class="btn btn-primary" id="changePasswordProfileBtn">Đổi mật khẩu</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		
		<!-- Modal Confirm Sign Out -->
		<div class="modal fade" id="confirmDeleteModal1">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content" align="center">
					<h5 class="modal-title1">
						<b>Đăng xuất</b>
					</h5>
					<p class="ss">Bạn chắc chắn muốn đăng xuất ?</p>
					<div class="modal-header" id="r">
					</div>
					<div class="modal-body" data-dismiss="modal">
						<input type="button" class="btnp btn-secondary1" value="Hủy" data-dismiss="modal">
					</div>
					<div class="modal-footer" onclick="window.location.href='/logout'">
					</div>
					<input type="button" class="btnp btn-primary1" value="Đăng xuất" onclick="window.location.href='/logout'" id="deleteSubmitBtn">
				</div>
			</div>
		</div>

	<footer class="footer mt-auto py-2" id="footer">
        <div class="container">
            <span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>
        </div>
    </footer>
	<div id="goTop" title="Go to top">
		<i class="fa fa-angle-double-up"></i>
	</div>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/profile.js'/>"></script>
	<script src="<c:url value='/js/changepassword.js'/>"></script>
</body>
</html>