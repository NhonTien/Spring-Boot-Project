<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Quản lý Khách hàng</title>
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
				<div class="float-left sub-title">Quản lý Khách hàng</div>
			</div>	
			<div class="form-search">
	        	<div class="vals">
	            	<input type="text" name="keyword" id="keyword" class="sr" placeholder="Username"/>
	            	<input type="hidden" name="role" id="role" class="sr" placeholder="Role" value="ROLE_USER"/>
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