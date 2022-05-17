$(document).ready(function() {

	// Show products list when opening page
	
	findAllCommentProducts(1);
	
	getAllCategories();
	
	findAllUser();
	// Show products list when clicking pagination button
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		findAllCommentProducts(pagerNumber);
	})
	
	var $commentInfoForm = $('#commentInfoForm');
	var $commentInfoModal = $('#commentInfoModal');

	// Submit add and update product
	$('#saveCommentBtn').on('click', function (event) {

		event.preventDefault();
		var formData = new FormData($commentInfoForm[0]);
		var productId = formData.get("productId");
		var isAddAction = productId == undefined || productId == "";
	
		$commentInfoForm.validate({
			ignore: [],
			rules: {
				content: {
					required: isAddAction
				}
			},
			messages: {
				content: {
					required: "Vui lòng nhập nội dung bình luận",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($commentInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/comment/api/add",
				type: 'POST',
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				cache: false,
				timeout: 10000,
				data: formData,
				success: function(responseData) {

					// Hide modal and show success message when save successfully
					// Else show error message in modal
					if (responseData.responseCode == 100) {
						$commentInfoModal.modal('hide');
						$("#content").val("");
						findAllCommentProducts(1);
						//showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($productInfoForm.find("#content"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
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

/**
 * Find products list with pager
 * 
 * @param pagerNumber
 */

function findAllCommentProducts(pagerNumber) {
	$.ajax({
		url : "/admin/comment/api/findAll/" + $("#productId").val() + "/" + pagerNumber,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		success : function(responseData) {
			if (responseData.responseCode == 100) {
				renderCommentProducts(responseData.data.commentsList);
				renderPagination(responseData.data.paginationInfo);
			}
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

function findAllUser() {
	$.ajax({
		url : "/api/findUser/" + $("#user").val(),
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		success : function(data) {
			var op = "<p>" + data.lastname + " " + data.firstname + "</p>";
			$('#userId').val(data.userId);
			var userImage = data.image;
			if (userImage == null || userImage == "") {
				userImage = "/images/image-user.png";
			}
			$("#logoImgs img").attr("src", userImage);
			$("#userImages").val(userImage);
			$("#picture-admin").val(userImage);
			$('#userAdmin').empty();
			$('#userAdmin').append(op);
		}
	});
}

function formatNumber(num) {
        var n = Number(num);
        return n.toLocaleString("vi");
}
/*
	* "vi"--"."
	* "en"--","
*/

function formatDate(n){
	var curDt = new Date(n);
    var MM = curDt.getMonth() + 1;
    var dd = curDt.getDate();
    var yyyy = curDt.getFullYear();
    var date = dd + '/' + MM + '/' + yyyy;
    return date;
}

function formatDateFull(n){
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
 * @param productsList
 */

function renderCommentProducts(commentsList) {

	var rowHtmls = "";
	$(".commentsLists").empty();
	$.each(commentsList, function(key, value) {
		var userImageCmt = value.userEntity.image;
			if (userImageCmt == null || userImageCmt == "") {
				userImageCmt = "/images/image-user.png";
		}
		moment.locale('vi');
		rowHtmls =  "<div>"
				+		'<div class="comment-list">'
				+ 		'<div id="image-comment"><img src="' + userImageCmt + '" id="userComment"/></div>'
				+		'<div class="info-comment"><p class="info-name">' + value.userEntity.lastname + " " + value.userEntity.firstname + '</p></div>'
				+		'</div>'
				+		"<p>" + value.content + "</p>"
				+		'<p class="date-cmt">' + moment(value.createDate).fromNow() + '</p>'
				+ 		'<hr>'
				+	"</div>";
		$(".commentsLists").append(rowHtmls);
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
	var rowCount = $('.commentsLists').length;
	if (paginationInfo.pageNumberList.length > 0) {
		$("ul.pagination").empty();
		//paginationInnerHtml += '<a>Total Comment ' + rowCount +'</a>'
		//paginationInnerHtml += '<a>Page ' + paginationInfo.currentPage + ' of ' + paginationInfo.totalPage + '</a>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.firstPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="'+ paginationInfo.firstPage + '">First</a></li>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.previousPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="'+ paginationInfo.previousPage + '"> < </a></li>'
		$.each(paginationInfo.pageNumberList, function(key, value) {
			paginationInnerHtml += '<li class="page-item"><a class="page-link '+ (value == paginationInfo.currentPage ? 'active' : '') +'" href="javascript:void(0)" data-index="' + value +'">' + value + '</a></li>';
		});
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.nextPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="'+ paginationInfo.nextPage + '"> > </a></li>'
		paginationInnerHtml += '<li class="page-item"><a class="page-link ' + (paginationInfo.lastPage == 0 ? 'disabled' : '') + '" href="javascript:void(0)" data-index="'+ paginationInfo.lastPage + '">Last</a></li>'
		$("ul.pagination").append(paginationInnerHtml);
	}
}