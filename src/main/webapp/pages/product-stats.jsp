<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Thống kê doanh thu theo sản phẩm</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/stats.css'/>">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
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
				<div class="float-left sub-title">Thống kê Doanh thu theo sản phẩm</div>
			</div>
			<div id="form-report">
				<input type="text" class="sr title-stats" value="Tên sản phẩm" readonly>
				<input type="text" class="sr title-stats" value="Từ ngày" readonly>
				<input type="text" class="sr title-stats" value="Đến ngày" readonly>
			</div>
			<form action="" id="form-report" class="form-stats">
				<input type="text" name="keyword" class="sr" placeholder="Tên sản phẩm">
				<input type="date" name="fromDate" class="sr">
				<input type="date" name="toDate" class="sr">
				<input type="submit" value="Thống kê" class="srb">
			</form>
			<div>
				<canvas id="myProductStatsChart"></canvas>
			</div>

			<table class="table">
				<tr align="center">
					<th scope="col">ID</th>
					<th scope="col">Tên sản phẩm</th>
					<th scope="col">Tổng doanh thu</th>
				</tr>
				<c:forEach items="${productStats}" var="p">
					<tr>
						<td align="center">${p[0]}</td>
						<td id="product-name">${p[1]}</td>
						<td align="right" id="product-total"><fmt:formatNumber type="number" pattern="###,###,###" value="${p[2]}" /> VNĐ</td>
					</tr>
				</c:forEach>
			</table>
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
	<div class="column-right">
		<footer class="footer mt-auto py-2" id="footer">
			<div class="container">
				<span>@Pilot Project - <script>document.write(new Date().getFullYear());</script></span>
			</div>
		</footer>
	</div>
	<div id="goTop" title="Go to top">
		<i class="fa fa-angle-double-up"></i>
	</div>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/stats.js'/>"></script>
	<script>
		let productLabels=[], productInfo=[]
		<c:forEach items="${productStats}" var="p">
			productLabels.push('${p[1]}')
			productInfo.push(${p[2]})
		</c:forEach>
		window.onload = function(){
			productChart("myProductStatsChart", productLabels, productInfo)
		}
		function generateColor(){
			let r = parseInt(Math.random()*255);
			let g = parseInt(Math.random()*255);
			let b = parseInt(Math.random()*255);
			return "rgb(" + r + "," + g + "," + b + ")"
		}
		
		function productChart(id, productLabels, productInfo){
			let colors=[]
			for (let i = 0; i < productInfo.length; i++)
				colors.push(generateColor())
			const data = {
				labels: productLabels,
				datasets: [{
					label: 'Thống kê doanh thu theo sản phẩm',
					data: productInfo,
					backgroundColor: colors,
					borderColor: colors,
					hoverOffset: 4
				}]
			};
			//type: 'line', 'bar', 'pie', '' 
			const config = {
				type: 'bar',
				data: data,
			};
			
			let ctx = document.getElementById(id).getContext("2d")
			
			new Chart(ctx, config)
		}
	</script>
</body>
</html>