$(document).ready(function() {

	// Show products list when opening page
	var pagerNumbers = 5;
	
	searchProductsWeb(pagerNumbers);
	
	searchProductsWeb1(pagerNumbers);
	
	searchProductsWeb2(pagerNumbers);
	
	searchProductsWeb3(pagerNumbers);
	
	searchProductsWeb4(pagerNumbers);
	
	searchProductsWeb5(pagerNumbers);

	getAllBrands();

	getAllCategories();

	// Show products list when clicking pagination button
	/*$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchProductsWeb(pagerNumber);
	})

	/*$("#search").on('click', function() {
		searchProductsWeb(1);
	});

	/*$("#keyword").keyup(function() {
		searchProductsWeb(1);
	})
	$("#priceFrom").keyup(function() {
		searchProductsWeb(1);
	})
	$("#priceTo").keyup(function() {
		searchProductsWeb(1);
	})*/

	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) $('#goTop').fadeIn();
			else $('#goTop').fadeOut();
		});
		$('#goTop').click(function() {
			$('body,html').animate({ scrollTop: 0 }, 'slow');
		});
	});
	
	$("#pow").on('click','.sg', function() {
		$('#confirmDeleteModal1').modal('show');
	});
});

let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
	showSlides(slideIndex += n);
}

function currentSlide(n) {
	showSlides(slideIndex = n);
}

function showSlides(n) {
	let i;
	let slides = document.getElementsByClassName("mySlides");
	let dots = document.getElementsByClassName("dot");
	if (n > slides.length) { slideIndex = 1 }
	if (n < 1) { slideIndex = slides.length }
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";
	}
	for (i = 0; i < dots.length; i++) {
		dots[i].className = dots[i].className.replace(" hight", "");
	}
	slides[slideIndex - 1].style.display = "block";
	dots[slideIndex - 1].className += " hight";
	/*slideIndex++;
	if (slideIndex > slides.length) 
	{
		slideIndex = 1
	}
	setTimeout(showSlides, 5000);*/

}

function searchProductsWeb(pagerNumbers) {
	
	$.ajax({
		url: "/admin/stats/hotProducts/1/" + pagerNumbers,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		success: function(productListWeb) {
			if (productListWeb != 0) {
				renderProducts(productListWeb);
				//renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}

function searchProductsWeb1(pagerNumbers) {
	
	$.ajax({
		url: "/admin/stats/hotProducts/2/" + pagerNumbers,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		success: function(productListWeb) {
			if (productListWeb != 0) {
				renderProducts1(productListWeb);
				//renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}

function searchProductsWeb2(pagerNumbers) {
	
	$.ajax({
		url: "/admin/stats/hotProducts/3/" + pagerNumbers,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		success: function(productListWeb) {
			if (productListWeb != 0) {
				renderProducts2(productListWeb);
				//renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}

function searchProductsWeb3(pagerNumbers) {
	
	$.ajax({
		url: "/admin/stats/hotProducts/4/" + pagerNumbers,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		success: function(productListWeb) {
			if (productListWeb != 0) {
				renderProducts3(productListWeb);
				//renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}

function searchProductsWeb4(pagerNumbers) {
	
	$.ajax({
		url: "/admin/stats/hotProducts/5/" + pagerNumbers,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		success: function(productListWeb) {
			if (productListWeb != 0) {
				renderProducts4(productListWeb);
				//renderPagination(responseData.data.paginationInfo);
			} else {
				var rowHtmlss = "<a>Updating...</a>";
					$(".row4").append(rowHtmlss);
			}
		}
	});
}

function searchProductsWeb5(pagerNumbers) {
	
	$.ajax({
		url: "/admin/stats/hotProducts/6/" + pagerNumbers,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		success: function(productListWeb) {
			if (productListWeb != 0) {
				renderProducts5(productListWeb);
				//renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}

function getAllBrands() {
	$.ajax({
		url: "/admin/brand/api/getAll",
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		success: function(brandList) {
			renderBrandsSelect(brandList);
		}
	});
}

function getAllCategories() {
	$.ajax({
		url: "/admin/category/api/getAllCategory",
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		success: function(categoryList) {
			renderCategories(categoryList);
		}
	});
}

function formatNumber(num) {
	var n = Number(num);
	return n.toLocaleString("vi");
}

function formatDateFull(n) {
	var curDt = new Date(n);
	var MM = curDt.getMonth() + 1;
	MM = (MM < 10) ? '0' + MM : MM;
	var dd = curDt.getDate();
	dd = (dd < 10) ? '0' + dd : dd;
	var yyyy = curDt.getFullYear();
	var date = dd + '/' + MM + '/' + yyyy;
	return date;
}

/**
 * Render HTML for product table
 * 
 * @param productListWeb
 */
function renderProducts(productListWeb) {
	var rowHtmlss = "";
	$(".row").empty();
	$.each(productListWeb, function(key, value) {
		/*var qty = value.quantity;
		if (qty == 0) {
				var x = "Đã hết hàng";
				$("#tt").addClass("d-none");
		} else{
			x = "Còn hàng";
		}*/
		rowHtmlss = '<div class="card" id="myTable">'
			+ '<div class="item-label" align="left">'
			//+ '<span class="lb-tragop">Trả góp 0%</span>'
			+ '</div>'
			+ '<a class="cart-title" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">'
			+ '<div class="c" align="center">'
			+ '<img class="card-img-top" id="v" src="' + value[3] + '" alt="Card image cap">'
			+ '</a>'
			+ '<h5 class="card-title">' + value[4] + '</h5>'
			+ '</div>' 
			+ '<div class="card-body">'
			+ '<p class="card-text">Giá: ' + formatNumber(value[5]*(1 - value[6]/100)) + '<sup><u>đ</u></sup></p>'
			/*+ '<p class="cardtext">Quà ' + formatNumber(450000) + '<sup><u>đ</u></sup></p>'
			+ '<div class="star">'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star-half-full checked"></span>'
			+ '<span class="dg">750</span>'
			+ '</div>'*/
			+ '<a class="btn btn-primary" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">Xem chi tiết</a>'
			+ '<a id="btn-cart" class="btn btn-primary cart" data-id="' + value[0] + '" href="/cart/add/' + value[0] + '"><i class="fas fa-shopping-cart"></i></a>'
			+ '</div>';
		$(".row").append(rowHtmlss);
	});
}

function renderProducts1(productListWeb) {
	var rowHtmlss = "";
	$(".row1").empty();
	$.each(productListWeb, function(key, value) {
		/*var qty = value.quantity;
		if (qty == 0) {
				var x = "Đã hết hàng";
				$("#tt").addClass("d-none");
		} else{
			x = "Còn hàng";
		}*/
		rowHtmlss = '<div class="card" id="myTable" align="center">'
			+ '<div class="item-label" align="left">'
			//+ '<span class="lb-tragop">Trả góp 0%</span>'
			+ '</div>'
			+ '<a class="cart-title" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">'
			+ '<div class="c" align="center">'
			+ '<img class="card-img-top" id="v" src="' + value[3] + '" alt="Card image cap">'
			+ '</a>'
			+ '<h5 class="card-title">' + value[4] + '</h5>'
			+ '</div>' 
			+ '<div class="card-body">'
			+ '<p class="card-text">Giá: ' + formatNumber(value[5]*(1 - value[6]/100)) + '<sup><u>đ</u></sup></p>'
			/*+ '<p class="cardtext">Quà ' + formatNumber(450000) + '<sup><u>đ</u></sup></p>'
			+ '<div class="star">'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star-half-full checked"></span>'
			+ '<span class="dg">750</span>'
			+ '</div>'*/
			+ '<a class="btn btn-primary" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">Xem chi tiết</a>'
			+ '<a id="btn-cart" class="btn btn-primary cart" data-id="' + value[0] + '" href="/cart/add/' + value[0] + '"><i class="fas fa-shopping-cart"></i></a>'
			+ '</div>';
		$(".row1").append(rowHtmlss);
	});
}

function renderProducts2(productListWeb) {
	var rowHtmlss = "";
	$(".row2").empty();
	$.each(productListWeb, function(key, value) {
		/*var qty = value.quantity;
		if (qty == 0) {
				var x = "Đã hết hàng";
				$("#tt").addClass("d-none");
		} else{
			x = "Còn hàng";
		}*/
		rowHtmlss = '<div class="card" id="myTable" align="center">'
			+ '<div class="item-label" align="left">'
			//+ '<span class="lb-tragop">Trả góp 0%</span>'
			+ '</div>'
			+ '<a class="cart-title" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">'
			+ '<div class="c" align="center">'
			+ '<img class="card-img-top" id="v" src="' + value[3] + '" alt="Card image cap">'
			+ '</a>'
			+ '<h5 class="card-title">' + value[4] + '</h5>'
			+ '</div>' 
			+ '<div class="card-body">'
			+ '<p class="card-text">Giá: ' + formatNumber(value[5]*(1 - value[6]/100)) + '<sup><u>đ</u></sup></p>'
			/*+ '<p class="cardtext">Quà ' + formatNumber(450000) + '<sup><u>đ</u></sup></p>'
			+ '<div class="star">'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star-half-full checked"></span>'
			+ '<span class="dg">750</span>'
			+ '</div>'*/
			+ '<a class="btn btn-primary" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">Xem chi tiết</a>'
			+ '<a id="btn-cart" class="btn btn-primary cart" data-id="' + value[0] + '" href="/cart/add/' + value[0] + '"><i class="fas fa-shopping-cart"></i></a>'
			+ '</div>';
		$(".row2").append(rowHtmlss);
	});
}

function renderProducts3(productListWeb) {
	var rowHtmlss = "";
	$(".row3").empty();
	$.each(productListWeb, function(key, value) {
		/*var qty = value.quantity;
		if (qty == 0) {
				var x = "Đã hết hàng";
				$("#tt").addClass("d-none");
		} else{
			x = "Còn hàng";
		}*/
		rowHtmlss = '<div class="card" id="myTable" align="center">'
			+ '<div class="item-label" align="left">'
			//+ '<span class="lb-tragop">Trả góp 0%</span>'
			+ '</div>'
			+ '<a class="cart-title" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">'
			+ '<div class="c" align="center">'
			+ '<img class="card-img-top" id="v" src="' + value[3] + '" alt="Card image cap">'
			+ '</a>'
			+ '<h5 class="card-title">' + value[4] + '</h5>'
			+ '</div>' 
			+ '<div class="card-body">'
			+ '<p class="card-text">Giá: ' + formatNumber(value[5]*(1 - value[6]/100)) + '<sup><u>đ</u></sup></p>'
			/*+ '<p class="cardtext">Quà ' + formatNumber(450000) + '<sup><u>đ</u></sup></p>'
			+ '<div class="star">'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star-half-full checked"></span>'
			+ '<span class="dg">750</span>'
			+ '</div>'*/
			+ '<a class="btn btn-primary" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">Xem chi tiết</a>'
			+ '<a id="btn-cart" class="btn btn-primary cart" data-id="' + value[0] + '" href="/cart/add/' + value[0] + '"><i class="fas fa-shopping-cart"></i></a>'
			+ '</div>';
		$(".row3").append(rowHtmlss);
	});
}

function renderProducts4(productListWeb) {
	var rowHtmlss = "";
	$(".row4").empty();
	$.each(productListWeb, function(key, value) {
		/*var qty = value.quantity;
		if (qty == 0) {
				var x = "Đã hết hàng";
				$("#tt").addClass("d-none");
		} else{
			x = "Còn hàng";
		}*/
		rowHtmlss = '<div class="card" id="myTable" align="center">'
			+ '<div class="item-label" align="left">'
			//+ '<span class="lb-tragop">Trả góp 0%</span>'
			+ '</div>'
			+ '<a class="cart-title" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">'
			+ '<div class="c" align="center">'
			+ '<img class="card-img-top" id="v" src="' + value[3] + '" alt="Card image cap">'
			+ '</a>'
			+ '<h5 class="card-title">' + value[4] + '</h5>'
			+ '</div>' 
			+ '<div class="card-body">'
			+ '<p class="card-text">Giá: ' + formatNumber(value[5]*(1 - value[6]/100)) + '<sup><u>đ</u></sup></p>'
			/*+ '<p class="cardtext">Quà ' + formatNumber(450000) + '<sup><u>đ</u></sup></p>'
			+ '<div class="star">'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star-half-full checked"></span>'
			+ '<span class="dg">750</span>'
			+ '</div>'*/
			+ '<a class="btn btn-primary" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">Xem chi tiết</a>'
			+ '<a id="btn-cart" class="btn btn-primary cart" data-id="' + value[0] + '" href="/cart/add/' + value[0] + '"><i class="fas fa-shopping-cart"></i></a>'
			+ '</div>';
		$(".row4").append(rowHtmlss);
	});
}

function renderProducts5(productListWeb) {
	var rowHtmlss = "";
	$(".row5").empty();
	$.each(productListWeb, function(key, value) {
		/*var qty = value.quantity;
		if (qty == 0) {
				var x = "Đã hết hàng";
				$("#tt").addClass("d-none");
		} else{
			x = "Còn hàng";
		}*/
		rowHtmlss = '<div class="card" id="myTable" align="center">'
			+ '<div class="item-label" align="left">'
			//+ '<span class="lb-tragop">Trả góp 0%</span>'
			+ '</div>'
			+ '<a class="cart-title" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">'
			+ '<div class="c" align="center">'
			+ '<img class="card-img-top" id="v" src="' + value[3] + '" alt="Card image cap">'
			+ '</a>'
			+ '<h5 class="card-title">' + value[4] + '</h5>'
			+ '</div>' 
			+ '<div class="card-body">'
			+ '<p class="card-text">Giá: ' + formatNumber(value[5]*(1 - value[6]/100)) + '<sup><u>đ</u></sup></p>'
			/*+ '<p class="cardtext">Quà ' + formatNumber(450000) + '<sup><u>đ</u></sup></p>'
			+ '<div class="star">'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star checked"></span>'
			+ '<span class="fa fa-star-half-full checked"></span>'
			+ '<span class="dg">750</span>'
			+ '</div>'*/
			+ '<a class="btn btn-primary" data-id="' + value[0] + '" href="/productdetail/' + value[1] +  '/' + value[2] + '">Xem chi tiết</a>'
			+ '<a id="btn-cart" class="btn btn-primary cart" data-id="' + value[0] + '" href="/cart/add/' + value[0] + '"><i class="fas fa-shopping-cart"></i></a>'
			+ '</div>';
		$(".row5").append(rowHtmlss);
	});
}

function renderBrandsSelect(brandList) {
	var rowHtmlss = "";
	$("#keyword");
	$.each(brandList, function(key, value) {
		rowHtmlss = '<option>'
			+ value.brandName
			+ "</option>";
		$("#keyword").append(rowHtmlss);
	});
}

function renderCategories(categoryList) {
	var rowHtmlss = "";
	$(".navbar__menu");
	$.each(categoryList, function(key, value) {
		rowHtmlss = '<li class="navbar__item">'
			+ '<a href="/' + value.categorySlug + '" class="navbar__links">' + value.categoryName + '</a>'
			+ '</li>';
		$(".navbar__menu").append(rowHtmlss);
	});
}

/**
 * Render HTML for pagination bar
 * 
 * @param paginationInfo
 */
function renderPagination(paginationInfo) {

	var paginationInnerHtml = "";
	if (paginationInfo.pageNumberList.length > 0) {
		$("ul.pagination").empty();
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.firstPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.firstPage + '">First</a></li>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.previousPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.previousPage + '"> < </a></li>'
		$.each(paginationInfo.pageNumberList, function(key, value) {
			paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (value == paginationInfo.currentPage ? 'active' : '') + '" href="javascript:void(0)" data-index="' + value + '">' + value + '</a></li>';
		});
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.nextPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.nextPage + '"> > </a></li>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.lastPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="' + paginationInfo.lastPage + '">Last</a></li>'
		$("ul.pagination").append(paginationInnerHtml);
	}
}