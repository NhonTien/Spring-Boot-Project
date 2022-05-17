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
    <title>Cảm ơn quý khách</title>
    <jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/cart.css'/>">
	<link rel="stylesheet" href="<c:url value='/css/webnav.css'/>">
</head>

<body>
	<jsp:include page="header.jsp" />
    
    <main role="main">
        <div class="container mt-4">
            <div id="thongbao" class="alert alert-danger d-none face" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
				<div class="alert">
					<i class="bi bi-cart-check"></i>
					<p class="cart-alert success">Đặt hàng thành công</p>
					<p class="cart-alert">Cảm ơn quý khách đã đặt hàng</p>
					<div align="center">
		            	<a href="/" class="btn btn-primary btn-md ms"><i class="fa fa-arrow-left"
		                            aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;Mua thêm sản phẩm khác</a>
		            </div>
				</div>
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
    <footer class="footer mt-auto py-3" id="footer">
        <div class="container">
            <span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>

            <p class="float-right">
                <a href="#">Back to top</a>
            </p>
        </div>
    </footer>
    <!-- end footer -->
  	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/cart.js'/>"></script>
	<script src="<c:url value='/js/userweb.js'/>"></script>
</body>

</html>