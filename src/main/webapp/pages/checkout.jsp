<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi" class="h-100">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Thanh toán</title>
    <jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/checkout.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/webnav.css'/>">
</head>
<body>
    <!-- header -->
    <jsp:include page="header.jsp" />
    <!-- end header -->
    <main role="main">
        <!-- Block content - Đục lỗ trên giao diện bố cục chung, đặt tên là `content` -->
        <div class="container mt-4">
        	<form  name="saveOrder" onsubmit="return checkStuff()" action="/cart/save/order"  method="post">
                <div class="py-5 text-center">
                    <i class="fa fa-credit-card fa-4x" aria-hidden="true"></i>
                    <h2>Thanh toán</h2>
                    <p class="lead">Vui lòng kiểm tra thông tin Khách hàng, thông tin Giỏ hàng trước khi đặt hàng</p>
                </div>

                <div class="row">
                    <div class="col-md-4 order-md-2 mb-4">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-muted">Giỏ hàng</span>
                            <span class="badge badge-secondary badge-pill">${TOTALQTY}</span>
                        </h4>
                        <ul class="list-group mb-3">
							<c:forEach var="item" items="${CART_ITEMS}">
	                            <li class="list-group-item d-flex justify-content-between lh-condensed">
	                                <div>
	                                    <h6 class="my-0">${item.name}</h6>
	                                    <small class="text-muted"><fmt:formatNumber type = "number" pattern = "###,###,###" value = "${item.price*(1 - item.discount/100)}" /><span class="cart-total"><sup><u>đ</u></sup></span> x ${item.qty}</small>
	                                </div>
	                                <span class="text-muted df"><fmt:formatNumber type = "number" pattern = "###,###,###" value = "${(item.price*(1 - item.discount/100)) * item.qty}" /><span class="cart-total"><sup><u>đ</u></sup></span></span>
	                            </li>
                        	</c:forEach>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Tổng tiền: </span>
                                <strong><fmt:formatNumber type = "number" pattern = "###,###,###" value = "${TOTAL}" /><span class="cart-total"><sup><u>đ</u></sup></span></strong>
                            </li>
                            <li class="list-group-item justify-content-between d-none" id="divfee">
                                <span>Phí giao hàng: </span>
                                <div id="fee"></div>
                            </li>
                            <li class="list-group-item justify-content-between d-none" id="divPromo">
                                <span>Quà tặng: </span>
                                <div id="promoCodePriceGift"></div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between" id="divfeetotal">
                                <span>Thanh toán: </span>
                                <div id="totalPrice"></div>
                            </li>
                        </ul>
                        <input type="text" class="form-control d-none" id="promoC" placeholder="Promo Code" name="promoCode" readonly>
                        <input type="text" class="form-control d-none" id="ttpri" placeholder="Total Price" readonly value="0">
                        <input type="text" class="form-control d-none" id="ttpr" placeholder="Total Price" readonly value="0">
                        <input type="text" class="form-control d-none" id="feeShip" placeholder="Fee Ship" value="0" name="feeShip" readonly>
                        <input type="text" class="form-control d-none" id="promoCodePrice" placeholder="Promo code" value="0" name="discount" readonly>
                        <div class="input-group">
                            <input type="text" class="form-control" id="promoCode" placeholder="Mã khuyến mãi">
                            <div class="input-group-append">
                                <button type="button" class="btn btn-secondary" id="promoCodeCheckBtn">Áp dụng</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8 order-md-1">
                        <h4 class="mb-3">Thông tin khách hàng</h4>
                        <div class="row">
                            <div class="col-md-6">
                            	<input type="hidden" class="form-control" id="userId" name="userEntity.userId">
                                <label for="name">Họ và tên <span class="required-mask">(*)</span></label>
                                <input type="text" class="form-control" id="username">
                                <div class="form-control alert alert-danger msg" id="msgName"><p class="mes">Vui lòng nhập họ và tên</p></div>
                            </div>
                            <div class="col-md-6">
                                <label for="phoneNumber">Số điện thoại <span class="required-mask">(*)</span></label>
                                <input type="text" class="form-control" id="phoneNumber">
                                <div class="form-control alert alert-danger msg" id="msgPhoneNumber"><p class="mes">Vui lòng nhập số điện thoại</p></div>
                            </div>
                            <input type="hidden" class="form-control" id="available" name="available" value="1">
                            <input type="hidden" class="form-control" id="available" name="confirm" value="0">
	                        <div class="col-md-6">
								<label>Tỉnh/Thành phố <span class="required-mask">(*)</span></label>
								<select id="province" class="form-control form-color" name="province.id">
									<option value="0">Chọn Tỉnh/Thành phố</option>
								</select>
							</div>
							<div class="col-md-6">
								<label>Quận/Huyện <span class="required-mask">(*)</span></label>
								<select id="district" class="form-control form-color" name="district.id">
								    <option value="0">Chọn Quận/Huyện</option>
								</select>
							</div>
							<div class="col-md-6">
							    <label>Phường/Xã <span class="required-mask">(*)</span></label>
							    <select id="ward" class="form-control form-color" name="ward.id">
							        <option value="0">Chọn Phường/Xã</option>
							    </select>
							</div>
							<div class="col-md-6">
							    <label>Số nhà, Tên đường <span class="required-mask">(*)</span></label>
							    <input id="homeNumber" class="form-control" placeholder="Số nhà, Tên đường" name="aptSuit">
							</div>
							<div class="col-md-12">
							    <label>Phương thức thanh toán <span class="required-mask">(*)</span></label><br>
							    <input type="radio" id="paymentMethod" class="" name="paymentMethod" value="Tiền mặt" checked/> <span> Tiền mặt</span>
							</div>
                            <div class="col-md-12">
                                <input type="hidden" class="form-control" name="total" id="total" value ="${TOTAL}">
                            </div>
                            <div class="col-md-12">
                                <input type="hidden" class="form-control" name="totalQuantity" id="totalQuantity" value ="${TOTALQTY}">
                            </div>
                        </div>
                        <hr class="mb-4">
                        <button class="btn btn-primary btn-lg btn-block" type="submit" name="btnDatHang">Đặt hàng</button>
                    </div>
                </div>
			</form>
        </div>
        <!-- End block content -->
    </main>
	<!-- Modal Confirm Deleting Brand -->
	<div class="modal fade" id="confirmDeleteModal1">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content" align="center">
				<h5 class="modal-title1">
					<b>Đăng xuất</b>
				</h5>
				<p class="ss">Bạn chắc chắn muốn đăng xuất ?</p>
				<div class="modal-header" id="r"></div>
				<div class="modal-body" data-dismiss="modal">
					<input type="button" class="btnp btn-secondary1" value="Hủy"
						data-dismiss="modal">
				</div>
				<div class="modal-footer" onclick="window.location.href='/logout'">
				</div>
				<input type="button" class="btnp btn-primary1" value="Đăng xuất"
					onclick="window.location.href='/logout'" id="deleteSubmitBtn">
			</div>
		</div>
	</div>
	<div id="goTop" title="Go to top"><i class="fa fa-angle-double-up"></i></div>
    <!-- footer -->
    <footer class="footer mt-auto py-3">
        <div class="container">
            <span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>
            <p class="float-right">
                <a href="#">Back to top</a>
            </p>
        </div>
    </footer>
    <!-- end footer -->
    <jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/checkout.js'/>"></script>
</body>
</html>