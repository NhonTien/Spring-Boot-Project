<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!--  <header>
	<div class="container">
		<div class="d-flex">
			<div class="main-logo">Pilot Project</div>
			<a class="ml-5 nav-link" href="/product">Product</a>
			<a class="nav-link active" href="/brand">Brand</a>
		</div>
	</div>
</header>-->
<div class="column-left">
		<div class="navs">
			<nav>
					<ul class="mcd-menu">
						<li>
							<a href="/admin/view-profile" class="ad-dash">
								<div class="dash">
									<div id="picture-admins">
										<div class="form-group" id="userImages">
											<div class="preview-image-upload image" id="logoImgs">
												<img src="<c:url value='/images/image-user.png'/>" alt="image" id="userAdminImage" title="View Profile">
											</div>
										</div>
									</div>
									<div id="userAdmin"></div>
									<input type="hidden" id="user" value="${pageContext.request.userPrincipal.name}">
									<input type="hidden" class="form-control" id="userId1">
								</div>
							</a>
						</li>
						<hr class="dashb">
						<li>
							<a href="/admin/home" class="admin-das">
								<i class="fa fa-home"></i>
								<p>Bảng điều khiển</p>
							</a>
						</li>
						<li>
							<a href="/admin/category" class="">
								<i class="fas fa-list"></i>
								<p>Quản lý danh mục</p>
							</a>
						</li>
						<li>
							<a href="/admin/banner">
								<i class="fas fa-audio-description"></i>
								<p>Quản lý banner</p>
							</a>
						</li>
						<li>
							<a href="/admin/brand">
								<i class="fas fa-cube"></i>
								<p>Quản lý thương hiệu</p>
							</a>
						</li>
						<li>
							<a href="/admin/product">
								<i class="fas fa-laptop"></i>
								<p>Quản lý sản phẩm</p>
							</a>
						</li>
						<!-- <li>
							<a href="/admin/order">
								<i class="fas fa-shopping-cart"></i>
								<p>Quản lý đặt hàng</p>
							</a>
						</li>-->
						<li class='sub-menu'>
							<a>
								<i class="fas fa-shopping-cart"></i>
								<p>Quản lý đặt hàng</p>
								<div class='fa fa-angle-down right dropmenu'></div>
							</a>
							<ul>
					              <li class="nav-item">
					                <a href="/admin/feeship" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Quản lý phí giao hàng</p>
					                </a>
					              </li>
					              <li class="nav-item">
					                <a href="/admin/order" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Quản lý đơn hàng</p>
					                </a>
					              </li>
					    	</ul>
						</li>
						<li>
							<a href="/admin/promo">
								<i class="fas fa-gift"></i>
								<p>Quản lý khuyến mãi</p>
							</a>
						</li>
						<li class='sub-menu'>
							<a>
								<i class="fas fa-user"></i>
								<p>Quản lý người dùng</p>
								<div class='fa fa-angle-down right dropmenu'></div>
							</a>
							<ul>
					              <li class="nav-item">
					                <a href="/admin/user/account-admin" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Quản trị viên</p>
					                </a>
					              </li>
					              <li class="nav-item">
					                <a href="/admin/user/account-user" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Khách hàng</p>
					                </a>
					              </li>
					    	</ul>
						</li>
						<li>
							<a href="/admin/comment">
								<i class="fas fa-comments"></i>
								<p>Quản lý bình luận</p>
							</a>
						</li>
						<li class='sub-menu'>
							<a>
								<i class="fas fa-line-chart"></i>
								<p>Thống kê</p>
								<div class='fa fa-angle-down right dropmenu'></div>
							</a>
							<ul>
					              <li class="nav-item">
					                <a href="/admin/stats" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Danh mục</p>
					                </a>
					              </li>
					              <li class="nav-item">
					                <a href="/admin/stats/brand-stats" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Thương hiệu</p>
					                </a>
					              </li>
					              <li class="nav-item">
					                <a href="/admin/stats/product-stats" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Doanh thu sản phẩm</p>
					                </a>
					              </li>
					              <li class="nav-item">
					                <a href="/admin/stats/month-stats" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Doanh thu theo tháng</p>
					                </a>
					              </li>
					              <li class="nav-item">
					                <a href="/admin/stats/comment-stats" class="nav-link">
					                  <i class="far fa-circle nav-icon"></i>
					                  <p>Bình luận</p>
					                </a>
					              </li>
					            </ul>
						</li>
						<li id="pow">
							<a class="sg">
								<i class="bi bi-power"></i>
								<p>Đăng xuất</p>
							</a>
						</li>
					</ul>
				</nav>
			</div>
	</div>