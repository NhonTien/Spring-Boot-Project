$(document).ready(function() {

	// Show brands list when opening page

	searchCategories(1);
	
	findAllUser();

	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchCategories(pagerNumber);
	})

	var $categoryInfoForm = $('#categoryInfoForm');
	var $categoryInfoModal = $('#categoryInfoModal');

	// Show add brand modal
	$('#addCategoryInfoModal').on('click', function() {
		resetFormModal($categoryInfoForm);
		showModalWithCustomizedTitle($categoryInfoModal, "Thêm");
		$('#categoryId').closest(".form-group").addClass("d-none");
	});

	// Show update brand modal
	$("#categoryInfoTable").on('click', '.edit-btn', function() {

		// Get brand info by brand ID
		$.ajax({
			url: "/admin/category/api/find?id=" + $(this).data("id"),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					var categoryInfo = responseData.data;
					resetFormModal($categoryInfoForm);
					showModalWithCustomizedTitle($categoryInfoModal, "Cập nhật");

					$('#categoryId').val(categoryInfo.categoryId);
					$('#categoryName').val(categoryInfo.categoryName);
					$('#categorySlug').val(categoryInfo.categorySlug);
					$('#description').val(categoryInfo.description);

					$('#categoryId').closest(".form-group").removeClass("d-none");
				}
			}
		});
	});

	// Show delete brand confirmation modal
	$("#categoryInfoTable").on('click', '.delete-btn', function() {
		$("#deletedCategoryName").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete brand
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/category/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				//findAllBrands(1);
				searchCategories(1);
			}
		});
	});

	// Submit add and update brand
	$('#saveCategoryBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($categoryInfoForm[0]);
		var categoryId = formData.get("categoryId");
		var isAddAction = categoryId == undefined || categoryId == "";

		$categoryInfoForm.validate({
			ignore: [],
			rules: {
				categoryName: {
					required: true,
					maxlength: 100
				},
				categorySlug: {
					required: true,
					maxlength: 100
				}
			},
			messages: {
				categoryName: {
					required: "Vui lòng nhập tên danh mục",
					maxlength: "Tên danh mục không quá 100 ký tự",
				},
				categorySlug: {
					required: "Vui lòng nhập Slug",
					maxlength: "Slug không quá 100 ký tự",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($categoryInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/category/api/" + (isAddAction ? "add" : "update"),
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
						$categoryInfoModal.modal('hide');
						//findAllBrands(1);
						searchCategories(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($categoryInfoForm.find("#categoryName"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	$("#search").on('click', function() {
		searchCategories(1);
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
	
	$("#pow").on('click','.sg', function() {
		$('#confirmDeleteModal1').modal('show');
	});
});

/**
 * Find brands list with pager
 * 
 * @param pagerNumber
 */

function searchCategories(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val()
	};
	$.ajax({
		url: "/admin/category/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderCategoriesTable(responseData.data.categoryList);
				renderPagination(responseData.data.paginationInfo);
			}
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
			$('#userId1').val(data.userId);
			var userImage = data.image;
			if (userImage == null || userImage == "") {
				userImage = "/images/image-user.png";
			}
			$("#logoImgs img").attr("src", userImage);
			$("#picture-admin").val(userImage);
			$('#userAdmin').empty();
			$('#userAdmin').append(op);
		}
	});
}

/**
 * Render HTML for brand table
 * 
 * @param categoryList
 */

function renderCategoriesTable(categoryList) {

	var rowHtml = "";
	$("#categoryInfoTable tbody").empty();
	$.each(categoryList, function(key, value) {
		rowHtml = "<tr>"
			+ "<td class='text-center'>" + value.categoryId + "</td>"
			+ "<td>" + value.categoryName + "</td>"
			+ "<td>" + value.description + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='edit-btn' data-id='" + value.categoryId + "'><i class='fas fa-edit'></i></a> | <a class='delete-btn' data-name='" + value.categoryName + "' data-id='" + value.categoryId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#categoryInfoTable tbody").append(rowHtml);
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
		paginationInnerHtml += '<a class="pg">Page ' + paginationInfo.currentPage + ' of ' + paginationInfo.totalPage + '</a>'
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