<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Quản lý Banner</title>
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
				<div class="float-left sub-title">Quản lý Banner</div>
				<div class="float-right"><a class="btn btn-success add-btn" id="addBannerInfoModal"><i class="fas fa-plus-square"></i> Thêm Banner</a></div>
			</div>	
			<div class="form-search">
	        	<div class="vals">
	            	<input type="text" name="keyword" id="keyword" class="sr" placeholder="Tên Banner"/>
	                <input type="submit" id="search" value="Tìm kiếm" class="srb"/>
	            </div>
	        </div>
	        
	        <table class="table table-bordered" id="bannerInfoTableSearch">
				<thead>
					<tr class="text-center">
						<th scope="col">#</th>
						<th scope="col">Tên Banner</th>
						<th scope="col">Hình ảnh</th>
						<th scope="col">Trạng thái</th>
						<th scope="col">Mô tả</th>
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
	<div class="modal fade" id="bannerInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<form id="bannerInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Thêm</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group d-none">
							<label>ID</label>
							<input type="text" class="form-control" name="bannerId" id="bannerId" placeholder="ID" readonly>
						</div>
						<div class="form-group">
							<label for="description">Tên Banner <span class="required-mask">(*)</span></label>
							<input type="text" class="form-control" name="bannerName" id="bannerName" placeholder="Tên Banner">
						</div>
						<div class="form-group" id="bannerImage">
							<label for="image">Hình ảnh <span class="required-mask">(*)</span></label>
							<div class="preview-image-upload" id="logoImg">
								<img src="<c:url value='/images/image-demo.png'/>" alt="image">
							</div>
							<input type="file" class="form-control upload-image" name="imageFiles" accept="image/*" />
							<input type="hidden" class="old-img" id="image" name="image">
						</div>
						<div class="form-group" id="selectStatus">
							<label for="status">Trạng thái <span class="required-mask">(*)</span></label>
							<select class="form-control" id="status" name="status">
								<option value="" selected disabled>--- Chọn trạng thái ---</option>
								<option value="1">Hiển thị</option>
								<option value="0">Ẩn</option>
							</select>
						</div>
						<div class="form-group">
							<label for="description">Mô tả</label>
							<textarea name="description" id="description" cols="30" rows="3" class="form-control" placeholder="Mô tả"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="saveBannerBtn">Lưu</button>
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
					<h5 class="modal-title">Xóa Banner</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Bạn chắc chắc muốn xóa <b id="deletedBannerName"></b>?</p>
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
				<p class="ss">Bạn chắn chắn muốn đăng xuất ?</p>
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
	<script src="<c:url value='/js/banner.js'/>"></script>
</body>
</html>