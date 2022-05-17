<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Log in with your account</title>
      <link rel="stylesheet" href="<c:url value='/icons-1.8.1/font/bootstrap-icons.css'/>">
      <link rel="icon" href="<c:url value='/images/favicon.ico'/>" type="image/gif" sizes="16x16">
      <link rel="stylesheet" href="<c:url value='/css/login.css'/>">
  </head>

<body id="particles-js">
	<div class="bg"></div>
	<div class="bg bg2"></div>
	<div class="bg bg3"></div>
	<div class="animated bounceInDown">
		<div class="container">
		    <span class="error animated tada" id="msg"></span>
		    <form name="form1" class="box" onsubmit="return checkStuff()" method="post" action="/loginAction" class="form-signin">
		      	<h4>User<span> Account</span></h4>
		      	<h5>Sign in to your account.</h5>
		        <input name="username" type="text" class="form-control" placeholder="Username" autofocus="true" autocomplete="off">
		        <i class="bi bi-eye-fill typcn typcn-eye" id="eye"></i>
		        <input name="password" type="password" class="form-control" placeholder="Password" autocomplete="off" id="pwd">
		        <div class="remmember"><label><input type="checkbox" name="remember-me"/> Remember me</label></div>
		        <input type="submit" value="Sign in" class="btn1">
		        <input type="reset" value="Cancel" onclick="window.location.href='/'" class="btn1" id="btn2">
		        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		        <div class="register"><label>No account? <a class="regis" href="/register"> Create one!</a></label></div>
		      </form>
		  </div> 
	</div>
	<script src="<c:url value='/js/login.js'/>"></script>
</body>
</html>
