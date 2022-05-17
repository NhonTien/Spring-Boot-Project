<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>Bảng điều khiển</title>
	<jsp:include page="../common/head.jsp" />
	<link rel="stylesheet" href="<c:url value='/css/admin.css'/>">
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<div class="column-right">
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
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

			<section class="content">
				<div class="container-fluid">
					<!-- Small boxes (Stat box) -->
					<div class="row">
						<div class="col-lg-3 col-6 cart-count">
							<!-- small box -->
							<div class="small-box bg-info">
								<div class="inner">
									<h3>${count} ${confirm}</h3>

									<p>Đơn đặt hàng mới</p>
								</div>
								<div class="icon">
									<i class="fas fa-shopping-cart"></i>
								</div>
								<a href="/admin/order" class="small-box-footer">Xem thêm thông tin <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6 cart-count">
							<!-- small box -->
							<div class="small-box bg-success">
								<div class="inner">
									<h3>
										${brand}
									</h3>

									<p>Thương hiệu</p>
								</div>
								<div class="icon">
									<i class="fas fa-cube"></i>
								</div>
								<a href="/admin/brand" class="small-box-footer">Xem thêm thông tin <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6 cart-count">
							<!-- small box -->
							<div class="small-box bg-warning">
								<div class="inner">
									<h3>${user}</h3>

									<p>Người dùng đăng ký</p>
								</div>
								<div class="icon">
									<i class="fa fa-user-plus"></i>
								</div>
								<a href="/admin/user/account-user" class="small-box-footer">Xem thêm thông tin <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-3 col-6 cart-count">
							<!-- small box -->
							<div class="small-box bg-danger">
								<div class="inner">
									<h3>${comment}</h3>

									<p>Bình luận</p>
								</div>
								<div class="icon">
									<i class="far fa-comments"></i>
								</div>
								<a href="/admin/comment" class="small-box-footer">Xem thêm thông tin <i
									class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
					</div>
				</div>
			</section>
			<div class="category-stats">
				<div class="columns-left">
					<canvas id="myCategoryStatsChart"></canvas>
				</div>
				<div class="columns-right">
					<canvas id="myProductMonthStatsChart"></canvas>
				</div>
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
	<div id="goTop" title="Go to top">
		<i class="fa fa-angle-double-up"></i>
	</div>
	<jsp:include page="../common/footer.jsp" />
	<script src="<c:url value='/js/stats.js'/>"></script>
	<script>
		let categoryLabels=[], categoryInfo=[]
		<c:forEach items="${categoryStats}" var="p">
			categoryLabels.push('${p[1]}')
			categoryInfo.push(${p[2]})
		</c:forEach>

		function generateColor(){
			let r = parseInt(Math.random()*255);
			let g = parseInt(Math.random()*255);
			let b = parseInt(Math.random()*255);
			return "rgb(" + r + "," + g + "," + b + ")"
		}
	
		function categoryChart(id, categoryLabels, categoryInfo){
			let colors=[]
			for (let i = 0; i < categoryInfo.length; i++)
				colors.push(generateColor())
			const data = {
				labels: categoryLabels,
				datasets: [{
					label: 'Thống kê số lượng theo danh mục',
					data: categoryInfo,
					backgroundColor: colors,
					borderColor: colors,
					hoverOffset: 4
				}]
			};
			//type: 'line', 'bar', 'pie', '' 
			const config = {
				type: 'doughnut',
				data: data,
			};
			
			let ctx = document.getElementById(id).getContext("2d")
			
			new Chart(ctx, config)
		}
	</script> 
	<script>
		let productLabels=[], productInfo=[]
		<c:forEach items="${productMonthStats}" var="p">
			productLabels.push('${p[0]}/${p[1]}')
			productInfo.push(${p[2]})
		</c:forEach>
			
		function generateColor(){
			let r = parseInt(Math.random()*255);
			let g = parseInt(Math.random()*255);
			let b = parseInt(Math.random()*255);
			return "rgb(" + r + "," + g + "," + b + ")"
		}

		function productMonthChart(id, productLabels, productInfo){
			let colors=[]
			for (let i = 0; i < productInfo.length; i++)
				colors.push(generateColor())
			const data = {
				labels: productLabels,
				datasets: [{
					label: 'Thống kê doanh thu theo tháng',
					data: productInfo,
					backgroundColor: colors,
					borderColor: colors,
					hoverOffset: 4
				}]
			};
			//type: 'line', 'bar', 'pie', '' 
			const config = {
				type: 'line',
				data: data,
			};
			
			let ctx = document.getElementById(id).getContext("2d")
			
			new Chart(ctx, config)
		}
	</script>
	<script type="text/javascript">
		$( document ).ready(function() {
			categoryChart("myCategoryStatsChart", categoryLabels, categoryInfo);
			productMonthChart("myProductMonthStatsChart", productLabels, productInfo);
		});
	</script>	
</body>
</html>