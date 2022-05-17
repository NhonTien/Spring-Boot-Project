<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Quản lý Quản trị viên</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/banner.css'/>">
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<!-- Menu -->
	<div class="column-right">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<p class="m-0">Bảng điều khiển</p>
					</div>
					<!-- /.col -->
					<div class="col-sm-6">
						<ol class="breadcrumb float-sm-right">
							<li class="breadcrumb-item"><a href="/admin/home">Trang chủ</a></li>
							<li class="breadcrumb-item active">Bảng điều khiển</li>
						</ol>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<div class="container">
			<div class="sub-header">
				<div class="float-left sub-title">Quản lý Quản trị viên</div>
				<div class="float-right">
					<a class="btn btn-success add-btn" id="addUserInfoModal"><i class="fas fa-plus-square"></i> Thêm tài khoản</a>
				</div>
			</div>	
			<div class="form-search">
	        	<div class="vals">
	            	<input type="text" name="keyword" id="keyword" class="sr" placeholder="Username"/>
	            	<input type="hidden" name="role" id="role" class="sr" placeholder="Role" value="ROLE_ADMIN"/>
	                <input type="submit" id="search" value="Tìm kiếm" class="srb"/>
	            </div>
	        </div>
	        
	        <table class="table table-bordered" id="userInfoTableSearch">
				<thead>
					<tr class="text-center">
						<th scope="col">#</th>
						<th scope="col">Họ</th>
						<th scope="col">Tên</th>
						<th scope="col">Username</th>
						<th scope="col">Số điện thoại</th>
						<th scope="col">Ảnh đại diện</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody align="center">
				</tbody>
			</table>
			
			<div class="d-flex justify-content-center">
				<ul class="pagination">
				</ul>
			</div>
		</div>
	</div>
	<!-- Modal Add and Edit Product -->
	<div class="modal fade" id="userAdminInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="userAdminInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Thêm</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group col-md-12">
							<input type="hidden" class="form-control" id="message">
						</div>
						<div class="info">
							<div class="form-group col-md-6">
								<label for="lastname">Họ <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="lastname2" name="lastname" placeholder="Họ">
							</div>
							<div class="form-group col-md-6">
								<label for="firstname">Tên <span class="required-mask">(*)</span></label>
								<input type="text" class="form-control" id="firstname2" name="firstname" placeholder="Tên">
							</div>
						</div>
						<div class="form-group col-md-12">
							<label for="phoneNumber">Số điện thoại <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" id="phoneNumber2" name="phoneNumber" placeholder="Số điện thoại">
						</div>
						<div class="form-group col-md-12">
							<label for="username">Username <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" id="username2" name="username" placeholder="username@gmail.com">
						</div>
						<div class="form-group col-md-12">
							<label for="role">Quyền <span class="required-mask">(*)</span></label>
							<select class="form-control" id="role2" name="role">
								<option selected disabled value="">Chọn quyền</option>
								<option value="ROLE_ADMIN">Admin</option>
								<option value="ROLE_USER">User</option>
							</select>
						</div>
						<div class="form-group col-md-12">
							<label for="password">Mật khẩu <span class="required-mask">(*)</span></label>
							<input type="password" class="form-control" id="password2" name="password" placeholder="Mật khẩu">
						</div>
						<div class="form-group col-md-12">
							<label for="passwordConfirm">Nhập lại mật khẩu <span class="required-mask">(*)</span></label>
							<input type="password" class="form-control" id="passwordConfirm" name="passwordConfirm" placeholder="Nhập lại mật khẩu">
						</div>
					</div>
					<div class="modal-footer">
						<a href="/"><button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button></a>
						<button type="button" class="btn btn-primary" id="saveUserAdminBtn">Đăng ký</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Modal Confirm Deleting Brand -->
	<div class="modal fade" id="confirmDeleteModal" >
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Xóa tài khoản</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Bạn chắc chắn muốn xóa <b id="deletedUsername"></b>?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
					<button type="button" class="btn btn-primary" id="deleteSubmitBtn">Xóa</button>
				</div>
			</div>
		</div>
	</div>
	<!-------------------------------------------------------------->

	<!-- Modal Confirm Deleting Brand -->
	<div class="modal fade" id="confirmDeleteModal1" >
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content" align="center">
				<h5 class="modal-title1"><b>Đăng xuất</b></h5>
				<p class="ss">Bạn chắc chắn muốn đăng xuất ?</p>
				<div class="modal-header" id="r">
				</div>
				<div class="modal-body" data-dismiss="modal">
					<input type="button" class="btnp btn-secondary1" value="Hủy" data-dismiss="modal">
				</div>
				<div class="modal-footer" onclick="window.location.href='/logout'">
				</div>
				<input type="button" class="btnp btn-primary1" value="Đăng xuất" onclick="window.location.href='/logout'" id="deleteSubmitBtn" >
			</div>
		</div>
	</div>	
	<div class="column-right">
		<footer class="footer mt-auto py-2" id="footer">
	        <div class="container">
	            <span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>
	        </div>
	    </footer>
    </div>
	<div id="goTop" title="Go to top"><i class="fa fa-angle-double-up"></i></div>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/user.js'/>"></script>
</body>
</html>