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
    <title>Giỏ hàng</title>
    <jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/cart.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/webnav.css'/>">
</head>

<body>
    <!-- header -->
    <jsp:include page="header.jsp" />
    <!-- end header -->
    
    <main role="main">
        <div class="container mt-4">
            <div id="thongbao" class="alert alert-danger d-none face" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <c:if test="${not empty message}">
				<div class="message-area" align="center">
					<div class="error-message-invalid">${message}</div>
				</div>
			</c:if>
			<c:if test="${TOTAL == 0}">
				<div class="alert">
					<i class="bi bi-cart-x"></i>
					<p class="cart-alert">Không có sản phẩm nào trong giỏ hàng</p>
					<a href="/" class="btn btn-primary btn-md ms">
						<i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;Quay lại trang chủ
					</a>
				</div>
			</c:if>
        </div>
        <!-- End block content -->
    </main>
	<c:if test="${TOTAL != 0}">
		<!-- <h3 class="text-center yourcard">Your Cart</h3>-->
		<div class="layout">
			<div class="column side"></div>
			<div class="column middle">
				<ul class="listing-cart">
					<c:forEach var="item" items="${CART_ITEMS}">
						<form action="/cart/update" method="post">
							<input type="hidden" name="productId" value="${item.productId}" />
							<li class="product-item listpro">
								<div class="imgsp">
									<div class="row">
										<div class="col-sm-2">
											<a href="/productdetail/${item.categorySlug}/${item.productSlug}" target="_blank">
												<img src="${item.image}" width="100px" height="100px" alt="Điện thoại ${item.image}" class=" ls-is-cached lazyloaded">
											</a>
											<div class="del" align="center">
												<a class="dele" href="cart/del/${item.productId}"><i class="bi bi-x-circle-fill"></i> Xóa</a>
											</div>
										</div>
										<div class="col-sm-8">
											<a href="/productdetail/${item.categorySlug}/${item.productSlug}" target="_blank" class="proname">${item.category} ${item.name}</a>
										</div>
										<div class="col-sm-2 right">
											<label class="price"><fmt:formatNumber type="number" pattern="###,###,###" value="${item.price*(1 - item.discount/100)}" /><span><sup><u>đ</u></sup></span></label> 
											<c:if test="${item.discount != null && item.discount != 0}">
												<label class="pricesaleoff"><strike><fmt:formatNumber type="number" pattern="###,###,###" value="${item.price}" /></strike><span><sup><u>đ</u></sup></span></label>
											</c:if>
											<div class="form-group cart-add">
												<div class="buttons_added">
													<div class="minus is-form" id="minus" onclick="var result = document.getElementById('qty'); var qty = result.value; if( !isNaN(qty) &amp; qty > 1 ) result.value--;return false;">
														<i class="bi bi-dash"></i>
													</div>
													<input aria-label="quantity" class="input-qty" max="5" min="1" name="qty" type="number" value="${item.qty}" onblur="this.form.submit()" id="qty">
													<div class="plus is-form" onclick="var result = document.getElementById('qty'); var qty = result.value; if( !isNaN(qty)) result.value++;return false;">
														<i class="bi bi-plus"></i>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<hr>
							</li>
						</form>
					</c:forEach>
				</ul>
				<ul class="listing-cart totl">
					<li class="product-item">
						<div class="imgsp">
							<div class="row totl">
								<div class="col-sm-9 totalqty">
									<p>Tạm Tính (${TOTALQTY} sản phẩm)</p>
								</div>
								<div class="col-sm-3 totalprice">
									<p><fmt:formatNumber type="number" pattern="###,###,###" value="${TOTAL}" /><span><sup><u>đ</u></sup></span></p>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
			<div class="column side"></div>
		</div>
		<div align="center">
			<a class="btn btn-primary btn-md" href="/cart/clear">Xóa giỏ hàng</a> 
			<a href="/" class="btn btn-warning btn-md"><i class="fa fa-arrow-left" aria-hidden="true"></i>&nbsp;Quay lại trang chủ</a> 
			<a href="/cart/checkout" id="tt" class="btn btn-primary btn-md"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;Thanh toán</a>
		</div>
	</c:if>

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
    <c:if test="${TOTAL == 0}">
    <footer class="footer mt-auto py-3" id="footer">
        <div class="container">
            <span>@Pilot Project</a> - <script>document.write(new Date().getFullYear());</script></span>

            <p class="float-right">
                <a href="#">Back to top</a>
            </p>
        </div>
    </footer>
    </c:if>
    <c:if test="${TOTAL != 0}">
    	<footer class="footer mt-auto py-3">
        <div class="container">
            <span>@Pilot Project</a> - <script>document.write(new Date().getFullYear());</script></span>

            <p class="float-right">
                <a href="#">Back to top</a>
            </p>
        </div>
    </footer>
    </c:if>
    <!-- end footer -->
  	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/cart.js'/>"></script>
	<script src="<c:url value='/js/userweb.js'/>"></script>
</body>

</html>