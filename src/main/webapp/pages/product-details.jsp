<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>${productEntity.productName}</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/productdetails.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/webnav.css'/>">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="<c:url value='/moment.js-2.29.3/moment-with-locales.min.js'/>"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
    <!-- end header -->
	<div class="product-details">
		<div class="card sd">
			<div class="container-fluid">
				<form name="frmsanphamchitiet" id="frmsanphamchitiet">
					<a href="/${productEntity.categoryEntity.description}">${productEntity.categoryEntity.categoryName}</a>
					<span class="detail-category">›</span> <a href=" ">${productEntity.categoryEntity.categoryName} ${productEntity.brandEntity.brandName}</a><br>
					<h3 class="product-title pt">${productEntity.productName}</h3>
					<div class="wrapper row">
						<div class="preview col-md-6 s">
							<div class="preview-pic">
								<div class="tab-pane active" id="pic-3">
									<img src="<c:url value='${productEntity.image}'/>" id="pic">
								</div>
							</div>
						</div>
						<div class="details col-md-6">
							<h3 class="product-title">${productEntity.productName}</h3>
							<div class="rating">
								<div class="star">
									<span class="fa fa-star checked"></span> 
									<span class="fa fa-star checked"></span> 
									<span class="fa fa-star checked"></span> 
									<span class="fa fa-star checked"></span> 
									<span class="fa fa-star-half-full checked"></span> <span class="dg">750 đánh giá</span>
								</div>
							</div>
							<c:if test="${productEntity.discount != null && productEntity.discount != 0}">
								<h5 class="text-muted">Giá cũ: <s><fmt:formatNumber type="number" pattern="###,###,###" value="${productEntity.price}" /> VNĐ</s> <fmt:formatNumber type="number" pattern="###,###,###" value="${-productEntity.discount}" />%</h5>
							</c:if>
							<h4 class="price">Giá hiện tại: <span><fmt:formatNumber type="number" pattern="###,###,###" value="${productEntity.price - (productEntity.price*productEntity.discount/100)}" /> VNĐ</span></h4>
							<c:if test="${productEntity.quantity == 0}">
								<h5 class="pric">Tạm hết hàng</h5>
							</c:if>
							<c:if test="${productEntity.quantity > 0}">
								<h5 class="pric">Còn hàng</h5>
							</c:if>
							<c:if test="${productEntity.quantity > 0}">
								<div class="action">
									<a class="add-to-cart btn btn-default" id="btnThemVaoGioHang" data-id="${productEntity.productId}" href="/cart/add/${productEntity.productId}">MUA NGAY</a>
								</div>
							</c:if>
							<c:if test="${productEntity.quantity == 0}">
								<div class="action"><a class="add-to-cart btn btn-default disabled" id="btnThemVaoGioHang" data-id="${productEntity.productId}" href="/cart/add/${productEntity.productId}">MUA NGAY</a>
								</div>
							</c:if>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div class="card">
			<div class="container-fluid">
				<h4>Thông tin sản phẩm</h4>
				<div class="row">
					<div class="col">
						${productEntity.description}
					</div>
				</div>
			</div>
		</div>
		<div class="card">
			<div class="container-fluid">
				<div class="sub-comment">
					<div class="float-left sub-title">
						<h4>Bình luận</h4>
					</div>
				</div>
				<div class="comments" id="commentInfoModal">
					<div class="write-comment" role="document">
						<div class="">
							<form id="commentInfoForm" role="form" enctype="multipart/form-data">
								<div class="">
									<input type="hidden" class="form-control" id="productId" name="productEntity.productId" value="${productEntity.productId}" readonly> 
									<input type="hidden" class="form-control" id="userId" name="userEntity.userId" readonly>
									<c:if test="${pageContext.request.userPrincipal.name != null}">
										<div class="form-group">
											<textarea cols="30" rows="5" class="form-control" name="content" placeholder="Viết bình luận" id="content"></textarea>
										</div>
										<button type="button" class="btn btn-primary" id="saveCommentBtn">Gửi</button>
									</c:if>
									<c:if test="${pageContext.request.userPrincipal.name == null}">
										<div class="form-group">
											<textarea cols="30" disabled="disabled" rows="5" class="form-control" name="content" placeholder="Đăng nhập để bình luận"></textarea>
										</div>
										<button type="button" disabled="disabled" class="btn btn-primary" id="saveCommentBtn">Gửi</button>
									</c:if>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<div class="commentsLists"></div>
				<div class="d-flex justify-content-center">
					<ul class="pagination">
					</ul>
				</div>
			</div>
		</div>
	</div>
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
	<!-- End block content -->
    <!-- footer -->
	<footer class="footer mt-auto py-3">
		<div class="container">
			<span>@Pilot Project - <script>document.write(new Date().getFullYear());
			</script></span>
			<p class="float-right">
				<a href="#">Back to top</a>
			</p>
		</div>
	</footer>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/details.js'/>"></script>
</body>
</html>