<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Menu -->
	<nav class="navbar">
		<div class="navbar__container">
			<a href="/" id="navbar__logo">TECH STORE</a>
			<div class="navbar__toggle" id="mobile-menu">
				<span class="bar"></span>
				<span class="bar"></span>
				<span class="bar"></span>
			</div>
			<ul class="navbar__menus">
				<li class="navbar__item">
					<a href="/order-history" class="navbar__linkss"></i>Lịch sử đặt hàng</a>
				</li>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li class="navbar__item shop-cart">
						<a href="/cart" class="navbar__linkss"><i class="fas fa-shopping-cart"></i><div class="icon" data-icon-label="${TOTALQTY}"></div></a>
					</li>
					<li class="navbar__item">
						<div id="picture-admins">
							<div class="form-group" id="userImages">
								<div class="preview-image-upload image" id="logoImgs">
									<a href="/view-profile"><img src="<c:url value='/images/image-user.png'/>" alt="image" id="userAdminImage" title="Quản lý thông tin cá nhân"></a>
								</div>
							</div>
							<div>
								<a class="navbar__linkss" href="/view-profile" id="userAdmin"></a>
							</div>
						</div> 
						<input type="hidden" id="user" value="${pageContext.request.userPrincipal.name}"></li>
						<input type="hidden" class="form-control" id="userId1">
					<li class="navbar__item" id="pow">
						<a class="navbar__links sg">
							<i class="fa fa-power-off" title="Đăng xuất" aria-hidden="true"></i>
						</a>
					</li>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<li class="navbar__item">
						<a href="/cart" class="navbar__linkss"><i class="fas fa-shopping-cart"></i><div class="icon" data-icon-label="${TOTALQTY}"></div></a>
					</li>
					<li class="navbar__item sign"><a href="/login" class="navbar__linkss">Đăng nhập</a></li>
				</c:if>
			</ul>
		</div>
		<div class="navbar__containers">
			<ul class="navbar__menu">
			</ul>
		</div>
	</nav>