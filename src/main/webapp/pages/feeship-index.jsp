<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<title>Quản lý phí giao hàng</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/category.css'/>">
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	
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
				<div class="float-left sub-title">Quản lý Phí giao hàng</div>
				<div class="float-right"><a class="btn btn-success add-btn" id="addFeeShipInfoModal"><i class="fas fa-plus-square"></i> Thêm phí giao hàng</a></div>
			</div>
			<div class="form-search">
				<div class="vals">
					<input type="text" name="keyword" id="keyword" class="sr" placeholder="Phí giao hàng" />
					<input type="submit" id="search" value="Tìm kiếm" class="srb" />
				</div>
			</div>
			
			<table class="table table-bordered" id="feeShipInfoTable">
				<thead>
					<tr class="text-center">
						<th scope="col">#</th>
						<th scope="col">Tỉnh/Thành phố</th>
						<th scope="col">Quận/Huyện</th>
						<th scope="col">Phường/Xã</th>
						<th scope="col">Phí giao hàng</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="d-flex justify-content-center">
				<ul class="pagination">
				</ul>
			</div>
		</div>
	</div>
	<!-- Modal Add and Edit Brand -->
	<div class="modal fade" id="feeShipInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="feeShipInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Thêm</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group d-none">
							<label>ID</label>
							<input type="text" class="form-control" name="feeShipId" id="feeShipId" placeholder="ID" readonly>
						</div>
						<div class="form-group">
							<label for="province">Tỉnh/Thành phố <span class="required-mask">(*)</span></label>
							<select class="form-control" id="province" name="province.id">
								<option value="0" selected disabled>Chọn Tỉnh/Thành phố</option>
							</select>
						</div>
						<div class="form-group">
							<label for="district">Quận/Huyện <span class="required-mask">(*)</span></label>
							<select class="form-control" id="district" name="district.id">
								<option value="0" selected disabled>Chọn Quận/Huyện</option>
							</select>
						</div>
						<div class="form-group">
							<label for="ward">Phường/Xã <span class="required-mask">(*)</span></label>
							<select class="form-control" id="ward" name="ward.id">
								<option value="0" selected disabled>Chọn Phường/Xã</option>
							</select>
						</div>
						<div class="form-group">
							<label for="feeShip">Phí giao hàng <span class="required-mask">(*)</span></label>
							<input type="number" class="form-control" id="feeShip" name="feeShip" placeholder="Phí giao hàng">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="saveFeeShipBtn">Lưu</button>
					</div>
				</form>
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
	<!-- Modal Confirm Deleting Brand -->
	<div class="modal fade" id="confirmDeleteModal" >
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Xóa phí giao hàng</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Bạn chắc chắn muốn xóa <b id="deletedFeeShip"></b>?</p>
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
	<div id="goTop" title="Go to top">
		<i class="fa fa-angle-double-up"></i>
	</div>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/feeship.js'/>"></script>
</body>
</html>