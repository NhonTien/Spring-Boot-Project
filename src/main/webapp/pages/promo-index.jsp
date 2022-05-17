<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	<title>Quản lý khuyến mãi</title>
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
				<div class="float-left sub-title">Quản lý Khuyến mãi</div>
				<div class="float-right"><a class="btn btn-success add-btn" id="addPromoInfoModal"><i class="fas fa-plus-square"></i> Thêm khuyến mãi</a></div>
			</div>
			<div class="form-search">
				<div class="vals">
					<input type="text" name="keyword" id="keyword" class="sr" placeholder="Tên chương trình khuyến mãi" />
					<input type="submit" id="search" value="Tìm kiếm" class="srb" />
				</div>
			</div>
			
			<table class="table table-bordered" id="promoInfoTable">
				<thead>
					<tr class="text-center">
						<th scope="col">#</th>
						<th scope="col">Tên chương trình khuyến mãi</th>
						<th scope="col">Mã khuyến mãi</th>
						<th scope="col">Giảm giá (VNĐ)</th>
						<th scope="col">Ngày bắt đầu</th>
						<th scope="col">Ngày kết thúc</th>
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
	<div class="modal fade" id="promoInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="promoInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Thêm</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group d-none">
							<label>ID</label>
							<input type="text" class="form-control" name="promoId" id="promoId" placeholder="ID" readonly>
						</div>
						<div class="form-group">
							<label for="promoName">Tên chương trình khuyến mãi <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" id="promoName" name="promoName" placeholder="Tên chương trình khuyến mãi">
						</div>
						<div class="form-group">
							<label for="promoCode">Mã khuyến mãi <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" id="promoCode" name="promoCode" placeholder="Mã khuyến mãi">
						</div>
						<div class="form-group">
							<label for="discount">Giảm giá <span class="required-mask">(*)</span></label>
							<input type="number" class="form-control" id="discount" name="discount" placeholder="Giảm giá">
						</div>
						<div class="form-group">
							<label for="fromDate">Ngày bắt đầu <span class="required-mask">(*)</span></label> 
							<input type="date" class="form-control" id="fromDate" name="fromDate" placeholder="Ngày bắt đầu">
						</div>
						<div class="form-group">
							<label for="toDate">Ngày kết thúc <span class="required-mask">(*)</span></label> 
							<input type="date" class="form-control" id="toDate" name="toDate" placeholder="Ngày kết thúc">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="savePromoBtn">Lưu</button>
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
					<h5 class="modal-title">Xóa khuyến mãi</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Bạn chắc chắn muốn xóa <b id="deletedPromoName"></b>?</p>
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
	<script src="<c:url value='/js/promo.js'/>"></script>
</body>
</html>