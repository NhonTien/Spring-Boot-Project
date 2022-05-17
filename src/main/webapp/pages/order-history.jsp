<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Lịch sử đặt hàng</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/orderhistory.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/webnav.css'/>">
</head>
<body>	
	<!-- Menu -->
	<jsp:include page="header.jsp" />
	
	<input type="hidden" class="form-control" id="userId" name="id" readonly>
	<div class="container">
		<div class="sub-header">
			<div class="float-left sub-title">Lịch sử đặt hàng</div>
		</div>
		
		<table class="table table-bordered" id="orderInfoTable">
			<thead>
				<tr class="text-center">
					<th scope="col">#</th>
					<th scope="col">Họ và tên</th>
					<th scope="col">Ngày đặt hàng</th>
					<th scope="col">Tổng tiền (VNĐ)</th>
					<th scope="col">Trạng thái đặt hàng</th>
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
	<!-- Modal Add and Edit Product -->
	<div class="modal fade orderdatails" id="orderInfoModal">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content orderdetails">
				<form id="orderInfoForm" role="form" enctype="multipart/form-data">
					<div class="modal-header">
						<h5 class="modal-title">Chi tiết đơn hàng</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true" id="close">&times;</span>
						</button>
					</div>
					<div class="modal-body orderdetail">
					<div class="customerForm">
						<div class="form-group d-none">
							<label for="id">Order Id</label>
							<input type="text" class="form-control" id="id" name="id" placeholder="Product ID" readonly>
						</div>
						<input class="customerInfo" id="addresss" readonly>
						<hr>
						<input class="customerInfo" id="status" readonly>
						<hr>
					</div>
					
					<table class="table table-bordered" id="orderDetailsTable">
						<thead align="center">
							<tr>
		                        <th scope="col">#</th>
		                        <th scope="col">Tên sản phẩm</th>
		                        <th scope="col">Hình ảnh</th>
		                        <th scope="col">Đơn giá (VNĐ)</th>
		                        <th scope="col">Số lượng</th>
		                        <th scope="col">Thành tiền (VNĐ)</th>
		                    </tr>
						</thead>
						<tbody align="center">
						</tbody>
						<tfoot>
							<tr>
		                        <td scope="col" colspan="4" id="totalprice" class="info-price">Tổng tiền</td>
		                        <td scope="col" align="center"><input id="totalQuantity" readonly></td>
		                        <td scope="col" align="right"><input id="total" readonly><span class="total-price"><sup><u>đ</u></sup></span></td>
		                    </tr>
		                    <tr>
			                	<td scope="col" colspan="5" id="totalprice" class="info-price">Phí giao hàng</td>
			                    <td scope="col" align="right" class="feeShip"><input id="feeShip" readonly><span class="total-price"><sup><u>đ</u></sup></span></td>
			                    <td scope="col" align="right" class="feeship d-none "><input id="feeship" readonly></td>
			                </tr>
			                <tr class="discount">
			                    <td scope="col" colspan="5" id="totalprice" class="info-price">Quà tặng</td>
			                    <td scope="col" align="right"><input id="discount" readonly><span class="total-price"><sup><u>đ</u></sup></span></td>
			                </tr>
			                <tr>
			                    <td scope="col" colspan="5" id="totalprice">Thanh toán</td>
			                    <td scope="col" align="right"><input id="totalPrice" readonly><span><sup><u>đ</u></sup></span></td>
			                </tr>
						</tfoot>
					</table>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
						<button type="button" class="btn btn-primary" id="saveOrderBtn">Hủy đơn hàng</button>
					</div>
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
					<h5 class="modal-title">Xóa sản phẩm</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<p>Bạn chắc chắn muốn xóa <b id="deletedProductName"></b>?</p>
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
					<!--<p>Are you sure you want to sign out ?</p>-->
					<input type="button" class="btnp btn-secondary1" value="Hủy" data-dismiss="modal">
				</div>
				<div class="modal-footer" onclick="window.location.href='/logout'">
				</div>
				<input type="button" class="btnp btn-primary1" value="Đăng xuất" onclick="window.location.href='/logout'" id="deleteSubmitBtn" >
			</div>
		</div>
	</div>	
	<div id="goTop" title="Go to top"><i class="fa fa-angle-double-up"></i></div>
	<footer class="footer mt-auto py-2" id="footer">
        <div class="container">
            <span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>
        </div>
    </footer>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/orderhistory.js'/>"></script>
</body>
</html>