<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Đăng ký</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/register.css'/>">
</head>
<body>
	<div class="bg"></div>
	<div class="bg bg2"></div>
	<div class="bg bg3"></div>
	<!-- Modal Add and Edit Product -->
	<div class="" id="userInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="userInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Đăng ký</h5>
					</div>
					<div class="modal-body">
						<div class="form-group col-md-12">
							<input type="hidden" class="form-control" id="message">
						</div>
						<div class="info">
							<div class="form-group col-md-6">
								<label for="lastname">Họ <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="lastname" name="lastname" placeholder="Họ">
							</div>
							<div class="form-group col-md-6">
								<label for="firstname">Tên <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Tên">
							</div>
						</div>
						<div class="form-group col-md-12">
							<label for="phoneNumber">Số điện thoại <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Số điện thoại">
						</div>
						<div class="form-group col-md-12">
							<label for="username">Username <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" id="username" name="username" placeholder="username@gmail.com">
						</div>
						<div class="form-group col-md-12">
							<label for="password">Mật khẩu <span class="required-mask">(*)</span></label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Mật khẩu">
						</div>
						<div class="form-group col-md-12">
							<label for="passwordConfirm">Nhập lại mật khẩu <span class="required-mask">(*)</span></label>
							<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Nhập lại mật khẩu">
						</div>
					</div>
					<div class="modal-footer">
						<a href="/"><button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button></a>
						<button type="button" class="btn btn-primary" id="saveUserBtn">Đăng ký</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-------------------------------------------------------------->	
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/user.js'/>"></script>
</body>
</html>