$(document).ready(function() {

	// Show products list when opening page
	
	getAllCategories();

	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) $('#goTop').fadeIn();
			else $('#goTop').fadeOut();
		});
		$('#goTop').click(function() {
			$('body,html').animate({ scrollTop: 0 }, 'slow');
		});
	});

	$("#pow").on('click', '.sg', function() {
		$('#confirmDeleteModal1').modal('show');
	});
	
});

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

