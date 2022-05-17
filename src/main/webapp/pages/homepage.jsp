<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
  <!-- Basic -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <title>Pilot Project</title>
  
  <link rel="icon" href="<c:url value='/images/favicon.ico'/>" type="image/gif" sizes="16x16">
   
  <!-- slider stylesheet -->
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.1.3/assets/owl.carousel.min.css" />

  <!-- bootstrap core css -->
  <link rel="stylesheet" href="<c:url value='/web/css/bootstrap.css'/>">

  <!-- fonts style -->
  <link href="https://fonts.googleapis.com/css?family=Dosis:400,500|Poppins:400,700&display=swap" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="<c:url value='/web/css/style.css'/>">
  <link rel="stylesheet" href="<c:url value='/css/homepage.css'/>">
  <!-- responsive style -->
  <link rel="stylesheet" href="<c:url value='/web/css/responsive.css'/>">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
  <div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
      <div class="container-fluid navb">
        <nav class="navbar navbar-expand-lg custom_nav-container pt-3">
          <a class="navbar-brand" href=" ">
            <span class="log">
              Pilot Project
            </span>
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="d-flex ml-auto flex-column flex-lg-row align-items-center">
              <ul class="navbar-nav  ">
                <li class="nav-item active">
                  <a class="nav-link hightlight" href=" ">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="product"> Product </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="brand"> Brand </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" target="_blank" href="/"> Website </a>
                </li>
                <li class="nav-item">
					<a class="nav-link ad">${pageContext.request.userPrincipal.name}</a>
				</li>
				<li class="nav-item" id="pow">
					<a class="nav-link sg"><i class="fa fa-power-off" title="Sign Out" aria-hidden="true"></i></a>
				</li>
              </ul>
              <form class="form-inline my-2 my-lg-0 ml-0 ml-lg-4 mb-3 mb-lg-0">
                <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit"></button>
              </form>
            </div>
          </div>
        </nav>
      </div>
    </header>
    <!-- end header section -->
    <!-- slider section -->
    <section class=" slider_section position-relative">
      <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active">
            <div class="slider_item-box">
              <div class="slider_item-container">
                <div class="container">
                  <div class="row">
                    <div class="col-md-5">
                      <div class="slider_img-box">
                        <div>
                          <img src="/web/images/Galaxy-S22-Ultra-Green-600x600.png" alt="" class="" />
                        </div>
                      </div>
                    </div>
                    <div class="col-md-7">
                      <div class="slider_item-detail">
                        <div>
                          <h1 align="center">
                            Samsung Galaxy S22 Ultra
                          </h1>
                          <p align="justify">
                            Meet the Galaxy S22 Ultra - inheriting the quintessential Galaxy Note and breakthrought Galaxy S.
                            Present width a slim overall design, a unique bezel-less camera cluster, all make up a super mobile product
                            width its own imprint.
                          </p>
                          <div class="d-flex">
                            <a href="" class="slider-btn2 mr-3 bt">
                              Read More
                            </a>
                            <a href="" class="slider-btn2">
                              Contact Us
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="slider_item-box">
              <div class="slider_item-container">
                <div class="container">
                  <div class="row">
                    <div class="col-md-5">
                      <div class="slider_img-box">
                        <div>
                          <img src="/web/images/Galaxy-S22-Ultra-Burgundy-600x600.png" alt="" class="" />
                        </div>
                      </div>
                    </div>
                    <div class="col-md-7">
                      <div class="slider_item-detail">
                        <div>
                          <h1 align="center">
                            Samsung Galaxy S22 Ultra
                          </h1>
                          <p align="justify">
                            Meet the Galaxy S22 Ultra - inheriting the quintessential Galaxy Note and breakthrought Galaxy S.
                            Present width a slim overall design, a unique bezel-less camera cluster, all make up a super mobile product
                            width its own imprint.
                          </p>
                          <div class="d-flex">
                            <a href="" class="slider-btn2 mr-3 bt">
                              Read More
                            </a>
                            <a href="" class="slider-btn2">
                              Contact Us
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="slider_item-box">
              <div class="slider_item-container">
                <div class="container">
                  <div class="row">
                    <div class="col-md-5">
                      <div class="slider_img-box">
                        <div>
                          <img src="/web/images/Galaxy-S22-Ultra-Black-600x600.png" alt="" class="" />
                        </div>
                      </div>
                    </div>
                    <div class="col-md-7">
                      <div class="slider_item-detail">
                        <div>
                          <h1 align="center">
                            Samsung Galaxy S22 Ultra
                          </h1>
                          <p align="justify">
                            Meet the Galaxy S22 Ultra - inheriting the quintessential Galaxy Note and breakthrought Galaxy S.
                            Present width a slim overall design, a unique bezel-less camera cluster, all make up a super mobile product
                            width its own imprint.
                          </p>
                          <div class="d-flex">
                            <a href="" class="slider-btn2 mr-3 bt">
                              Read More
                            </a>
                            <a href="" class="slider-btn2">
                              Contact Us
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="carousel-item">
            <div class="slider_item-box">
              <div class="slider_item-container">
                <div class="container">
                  <div class="row">
                    <div class="col-md-5">
                      <div class="slider_img-box">
                        <div>
                          <img src="/web/images/Galaxy-S22-Ultra-White-600x600.png" alt="" class="" />
                        </div>
                      </div>
                    </div>
                    <div class="col-md-7">
                      <div class="slider_item-detail">
                        <div>
                          <h1 align="center">
                            Samsung Galaxy S22 Ultra
                          </h1>
                          <p align="justify">
                            Meet the Galaxy S22 Ultra - inheriting the quintessential Galaxy Note and breakthrought Galaxy S.
                            Present width a slim overall design, a unique bezel-less camera cluster, all make up a super mobile product
                            width its own imprint.
                          </p>
                          <div class="d-flex">
                            <a href="" class="slider-btn2 mr-3 bt">
                              Read More
                            </a>
                            <a href="" class="slider-btn2">
                              Contact Us
                            </a>
                          </div>
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="custom_carousel-control">
          <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
    </section>
    <!-- end slider section -->
  </div>
  <div class="bg">

    <!-- about section -->
    <section class="about_section layout_padding">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-6 offset-md-2">
            <div class="about_detail-box">
              <h3 class="custom_heading ">
                About our Samsung Galaxy S22 Ultra
              </h3>
              <p class="" align="justify">
                It is a long established fact that a reader will be distracted by the readable content of a page when
                looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution
                of
                letters, as opposed to using 'Content here, content here', making it
              </p>
              <div>
                <a href="">
                  Read More
                </a>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="about_img-box">
              <img src="images/about.png" alt="">
            </div>
          </div>
        </div>
      </div>
    </section>


  <!-- end client section -->

  <!-- contact section -->

  <section class="contact_section layout_padding">
    <h2 class="custom_heading text-center">
      NOW CONTACT US
    </h2>
    <div class="container mt-5 pt-5">
      <form action="">
        <div>
          <input type="text" placeholder="NAME">
        </div>
        <div>
          <input type="email" placeholder="EMAIL">
        </div>
        <div>
          <input type="text" placeholder="PHONE NUMBER">
        </div>
        <div>
          <input type="text" class="message-box" placeholder="MESSAGE">
        </div>
        <div class="d-flex justify-content-center mt-5 pt-5">
          <button>
            SEND
          </button>
        </div>
      </form>
    </div>
  </section>

  <!-- end contact section -->

  <!-- info section -->
  <section class="info_section layout_padding">
    <div class="container">
      <div class="info_items">
        <a href="">
          <div class="item ">
            <div class="img-box box-1">
              <img src="" alt="">
            </div>
            <div class="detail-box">
              <p>
                Lorem Ipsum is simply dummy text
              </p>
            </div>
          </div>
        </a>
        <a href="">
          <div class="item ">
            <div class="img-box box-2">
              <img src="" alt="">
            </div>
            <div class="detail-box">
              <p>
                +02 1234567890
              </p>
            </div>
          </div>
        </a>
        <a href="">
          <div class="item ">
            <div class="img-box box-3">
              <img src="" alt="">
            </div>
            <div class="detail-box">
              <p>
                pilotproject@gmail.com
              </p>
            </div>
          </div>
        </a>
      </div>
    </div>
  </section>
 </div>

  <!-- Modal Confirm Deleting Brand -->
	<div class="modal fade" id="confirmDeleteModal" >
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content" align="center">
				<h5 class="modal-title"><b>Sign Out</b></h5>
				<p class="s">Are you sure you want to sign out ?</p>
				<div class="modal-header">
				</div>
				<div class="modal-body" data-dismiss="modal">
					<input type="button" class="btnp btn-secondary1" value="Cancel" data-dismiss="modal">
				</div>
				<div class="modal-footer" onclick="window.location.href='logout'">
				</div>
				<input type="button" class="btnp btn-primary1" value="Sign Out" onclick="window.location.href='/logout'" id="deleteSubmitBtn" >
			</div>
		</div>
	</div>

	<div id="goTop" title="Go to top"><i class="fa fa-angle-double-up"></i></div>
  <!-- end info_section -->

  <!-- footer section -->
  <section class="container-fluid footer_section">
    <p>
      © 2022 All Rights Reserved By
      <a href="https://html.design/">Pilot Project</a>
    </p>
  </section>
  <!-- footer section -->
  
  <script src="<c:url value='/web/js/jquery-3.4.1.min.js'/>"></script>
  <script src="<c:url value='/web/js/bootstrap.js'/>"></script>
<script type="text/javascript">
$('.sub-menu ul').hide();
$(".sub-menu a").click(function () {
	$(this).parent(".sub-menu").children("ul").slideToggle("100");
	$(this).find(".right").toggleClass("fa-caret-up fa-caret-down");
});
</script>

	<script>
		$(function(){
			$(window).scroll(function () {
				if ($(this).scrollTop() > 100) $('#goTop').fadeIn();
				else $('#goTop').fadeOut();
				});
				$('#goTop').click(function () {
					$('body,html').animate({scrollTop: 0}, 'slow');
				});
			});
	</script>
	<script>
	// Show delete brand confirmation modal
	$("#pow").on('click','.sg', function() {
		$('#confirmDeleteModal').modal('show');
	});
	</script>
</body>

</html>