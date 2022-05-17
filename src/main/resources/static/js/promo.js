$(document).ready(function() {

	// Show brands list when opening page

	searchPromos(1);
	
	findAllUser();

	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchPromos(pagerNumber);
	})

	var $promoInfoForm = $('#promoInfoForm');
	var $promoInfoModal = $('#promoInfoModal');

	// Show add brand modal
	$('#addPromoInfoModal').on('click', function() {
		resetFormModal($promoInfoForm);
		showModalWithCustomizedTitle($promoInfoModal, "Thêm");
		$('#promoId').closest(".form-group").addClass("d-none");
	});

	// Show update brand modal
	$("#promoInfoTable").on('click', '.edit-btn', function() {

		// Get brand info by brand ID
		$.ajax({
			url: "/admin/promo/api/find?id=" + $(this).data("id"),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					var promoInfo = responseData.data;
					resetFormModal($promoInfoForm);
					showModalWithCustomizedTitle($promoInfoModal, "Cập nhật");

					$('#promoId').val(promoInfo.promoId);
					$('#promoName').val(promoInfo.promoName);
					$('#promoCode').val(promoInfo.promoCode);
					$('#discount').val(promoInfo.discount);
					$('#fromDate').val(promoInfo.fromDate);
					$('#toDate').val(promoInfo.toDate);

					$('#promoId').closest(".form-group").removeClass("d-none");
				}
			}
		});
	});

	// Show delete brand confirmation modal
	$("#promoInfoTable").on('click', '.delete-btn', function() {
		$("#deletedPromoName").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete brand
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/promo/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				//findAllBrands(1);
				searchPromos(1);
			}
		});
	});

	// Submit add and update brand
	$('#savePromoBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($promoInfoForm[0]);
		var promoId = formData.get("promoId");
		var isAddAction = promoId == undefined || promoId == "";

		$promoInfoForm.validate({
			ignore: [],
			rules: {
				promoName: {
					required: true,
					maxlength: 100
				},
				promoCode: {
					required: isAddAction
				},
				discount: {
					required: isAddAction
				},
				fromDate: {
					required: isAddAction
				},
				toDate: {
					required: isAddAction
				}
			},
			messages: {
				promoName: {
					required: "Vui lòng nhập chương trình khuyến mãi",
					maxlength: "Chương trình khuyến mãi không quá 100 ký tự",
				},
				promoCode: {
					required: "Vui lòng nhập mã khuyến mãi"
				},
				discount: {
					required: "Vui lòng nhập giảm giá"
				},
				fromDate: {
					required: "Vui lòng chọn ngày bắt đầu"
				},
				toDate: {
					required: "Vui lòng chọn ngày kết thúc"
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($promoInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/promo/api/" + (isAddAction ? "add" : "update"),
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
						$promoInfoModal.modal('hide');
						//findAllBrands(1);
						searchPromos(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($promoInfoForm.find("#promoCode"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	$("#search").on('click', function() {
		searchPromos(1);
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

function searchPromos(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val()
	};
	$.ajax({
		url: "/admin/promo/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderPromosTable(responseData.data.promoList);
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
 * Render HTML for brand table
 * 
 * @param promoList
 */

function renderPromosTable(promoList) {

	var rowHtml = "";
	$("#promoInfoTable tbody").empty();
	$.each(promoList, function(key, value) {
		rowHtml = "<tr>"
			+ "<td class='text-center'>" + value.promoId + "</td>"
			+ "<td>" + value.promoName + "</td>"
			+ "<td align='center'>" + value.promoCode + "</td>"
			+ "<td align='right'>" + formatNumber(value.discount) + "</td>"
			+ "<td align='center'>" + formatDateFull(value.fromDate) + "</td>"
			+ "<td align='center'>" + formatDateFull(value.toDate) + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='edit-btn' data-id='" + value.promoId + "'><i class='fas fa-edit'></i></a> | <a class='delete-btn' data-name='" + value.promoName + "' data-id='" + value.promoId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#promoInfoTable tbody").append(rowHtml);
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