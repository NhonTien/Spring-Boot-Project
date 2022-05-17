<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>Website</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/web.css'/>">
</head>
<body>
	<jsp:include page="header.jsp" />
	
	<div class="slideshow-container" id="myCarousel">
		<c:forEach items="${banner}" var="items">
			<c:if test="${items.status == 1}">
				<div class="mySlides fades">
					<img src="${items.image}" style="width: 100%">
				</div>
			</c:if>
		</c:forEach>
		<a class="prev" onclick="plusSlides(-1)">❮</a> <a class="next"
			onclick="plusSlides(1)">❯</a>
	</div>
	<div class="banner" align="center">
		<c:set var="count" value="0" scope="page" />
		<c:forEach items="${banner}" var="items">
			<c:if test="${items.status == 1}">
				<c:set var="count" value="${count + 1}" scope="page" />
				<div class="des dot" onclick="currentSlide(${count})">${items.bannerName}</div>
			</c:if>
		</c:forEach>
	</div>
	<br>

	<!-- <div class="container b">
		<div class="sub-header">
			<div class="float-center sub-title">SmartPhone</div>
		</div>
	</div>

	<div class="form-search" align="center">
    	<div class="vals">
    		<input type="text" name="keywords" id="keywords" class="sr" placeholder="Product Name">
			<select name="keyword" id="keyword" class="sr">
            	<option value="" selected>All Brand</option>
            </select>
            <select name="price" id="price" class="sr">
				<option data-priceFrom="" data-priceTo="" selected>Price</option>
				<option data-priceFrom="" data-priceTo="8000000">Under 8.000.000</option>
				<option data-priceFrom="8000000" data-priceTo="15000000">8.000.000 - 15.000.000</option>
				<option data-priceFrom="15000000" data-priceTo="22000000">15.000.000 - 22.000.000</option>
				<option data-priceFrom="22000000" data-priceTo="30000000">22.000.000 - 30.000.000</option>
				<option data-priceFrom="30000000" data-priceTo="">Over 30.000.000</option>
			</select> 
			<input type="submit" id="search" value="Search" class="srb"/>
		</div>
 	</div> -->

	<div class="section-product" align="center">
		<div class="containers">
			<br>
			<div class="alert-success topproducts"><h5>SmartPhone nổi bật</h5></div>
			<br>
			<div class="row">
			</div>
			<br>
			<div class="alert-success topproducts"><h5>Laptop nổi bật</h5></div>
			<br>
			<div class="row1" align="left">
			</div>
			<br>
			<div class="alert-success topproducts"><h5>Tablet nổi bật</h5></div>
			<br>
			<div class="row2" align="left">
			</div>
			<br>
			<div class="alert-success topproducts"><h5>Accessory nổi bật</h5></div>
			<br>
			<div class="row3" align="left">
			</div>
			<br>
			<div class="alert-success topproducts"><h5>Smart Watch nổi bật</h5></div>
			<br>
			<div class="row4" align="left">
			</div>
			<br>
			<div class="alert-success topproducts"><h5>PC nổi bật</h5></div>
			<br>
			<div class="row5" align="left">
			</div>
			<div class="d-flex justify-content-center">
				<ul class="pagination">
				</ul>
			</div>
		</div>
	</div>
	<div class="section-product">
		<div class="containers">
			
			<div class="d-flex justify-content-center">
				<ul class="pagination">
				</ul>
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
	<footer class="footer mt-auto py-1" id="footer">
        <div class="container">
            <span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>
        </div>
    </footer>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/webtop.js'/>"></script>
	<script src="<c:url value='/js/userweb.js'/>"></script>
</body>
</html>