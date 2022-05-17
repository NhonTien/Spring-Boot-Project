$(document).ready(function() {

	// Show brands list when opening page
	searchBrands(1);
	
	findAllUser();

	// Show brands list when clicking pagination button
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchBrands(pagerNumber);
	})

	var $brandInfoForm = $('#brandInfoForm');
	var $brandInfoModal = $('#brandInfoModal');

	// Show add brand modal
	$('#addBrandInfoModal').on('click', function() {
		resetFormModal($brandInfoForm);
		showModalWithCustomizedTitle($brandInfoModal, "Thêm");
		$('#logoImg img').attr('src', '/images/image-demo.png');
		$('#brandId').closest(".form-group").addClass("d-none");
		$("#brandLogo .required-mask").removeClass("d-none");
	});

	// Show update brand modal
	$("#brandInfoTable").on('click', '.edit-btn', function() {

		$("#brandLogo .required-mask").addClass("d-none");

		// Get brand info by brand ID
		$.ajax({
			url: "/admin/brand/api/find?id=" + $(this).data("id"),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					var brandInfo = responseData.data;
					resetFormModal($brandInfoForm);
					showModalWithCustomizedTitle($brandInfoModal, "Cập nhật");

					$('#brandId').val(brandInfo.brandId);
					$('#brandName').val(brandInfo.brandName);
					$('#description').val(brandInfo.description);

					var brandLogo = brandInfo.logo;
					if (brandLogo == null || brandLogo == "") {
						brandLogo = "/images/image-demo.png";
					}
					$("#logoImg img").attr("src", brandLogo);
					$("#logo").val(brandLogo);
					$('#brandId').closest(".form-group").removeClass("d-none");
				}
			}
		});
	});

	// Show delete brand confirmation modal
	$("#brandInfoTable").on('click', '.delete-btn', function() {
		$("#deletedBrandName").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete brand
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/brand/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				searchBrands(1);
			}
		});
	});

	// Submit add and update brand
	$('#saveBrandBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($brandInfoForm[0]);
		var brandId = formData.get("brandId");
		var isAddAction = brandId == undefined || brandId == "";

		$brandInfoForm.validate({
			ignore: [],
			rules: {
				brandName: {
					required: true,
					maxlength: 100
				},
				logoFiles: {
					required: isAddAction,
				}
			},
			messages: {
				brandName: {
					required: "Vui lòng nhập tên thương hiệu",
					maxlength: "Tên thương hiệu không quá 100 ký tự",
				},
				logoFiles: {
					required: "Vui lòng chọn logo",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($brandInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/brand/api/" + (isAddAction ? "add" : "update"),
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
						$brandInfoModal.modal('hide');
						//findAllBrands(1);
						searchBrands(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($brandInfoForm.find("#brandName"), responseData.responseMsg);
					}
				}
			});
		}
	});

	$("#search").on('click', function() {
		searchBrands(1);
	});

	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100)
				$('#goTop').fadeIn();
			else
				$('#goTop').fadeOut();
		});
		$('#goTop').click(function() {
			$('body,html').animate({
				scrollTop: 0
			}, 'slow');
		});
	});

	// Show delete brand confirmation modal
	$("#pow").on('click', '.sg', function() {
		$('#confirmDeleteModal1').modal('show');
	});
});

/**
 * Find brands list with pager
 * 
 * @param pagerNumber
 */

function searchBrands(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val()
	};
	$.ajax({
		url: "/admin/brand/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderBrandsTable(responseData.data.brandList);
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
 * @param brandList
 */

function renderBrandsTable(brandList) {

	var rowHtml = "";
	$("#brandInfoTable tbody").empty();
	$.each(brandList, function(key, value) {
		rowHtml = "<tr>"
			+ "<td class='text-center'>" + value.brandId + "</td>"
			+ "<td>" + value.brandName + "</td>"
			+ "<td class='text-center'><a href='" + value.logo + "' data-toggle='lightbox' data-max-width='1000'><img class='img-fluid' src='" + value.logo + "'></td>"
			+ "<td>" + value.description + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='edit-btn' data-id='" + value.brandId + "'><i class='fas fa-edit'></i></a> | <a class='delete-btn' data-name='" + value.brandName + "' data-id='" + value.brandId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#brandInfoTable tbody").append(rowHtml);
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