$(document).ready(function() {

	// Show brands list when opening page

	searchFees(1);
	
	findAllUser();
	
	$.ajax({
		url: "/admin/address/api/getAll",
		type: "GET",
		dataType: 'json',
		success: function(data) {
			var rowHtml = "";
			$.each(data, function(key, value) {
				rowHtml = "<option value = '" + value.id + "' provinceName='" + value.name + "'>"
					+ value.name
					+ "</option>";
				$("#province").append(rowHtml);
			});
			$("#province").on("change", function(e) { selectDistrict(); });
		}
	});

	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchFees(pagerNumber);
	})

	var $feeShipInfoForm = $('#feeShipInfoForm');
	var $feeShipInfoModal = $('#feeShipInfoModal');

	// Show add brand modal
	$('#addFeeShipInfoModal').on('click', function() {
		resetFormModal($feeShipInfoForm);
		showModalWithCustomizedTitle($feeShipInfoModal, "Thêm");
		$('#feeShipId').closest(".form-group").addClass("d-none");
	});

	// Show update brand modal
	$("#feeShipInfoTable").on('click', '.edit-btn', function() {
		$('#district').empty();
		$('#ward').empty();
		// Get brand info by brand ID
		$.ajax({
			url: "/admin/feeship/api/find?id=" + $(this).data("id"),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					var feeShipInfo = responseData.data;
					resetFormModal($feeShipInfoForm);
					
					showModalWithCustomizedTitle($feeShipInfoModal, "Cập nhật");
					var d = '<option value="' + feeShipInfo.district.id + '" selected >' + feeShipInfo.district.name + '</option>';
					var w = '<option value="' + feeShipInfo.ward.id + '" selected >' + feeShipInfo.ward.name + '</option>';
					$('#feeShipId').val(feeShipInfo.feeShipId);
					$('#province').val(feeShipInfo.province.id);
					$('#district').append(d);
					$('#ward').append(w);
					$('#feeShip').val(feeShipInfo.feeShip);

					$('#feeShipId').closest(".form-group").removeClass("d-none");
				}
			}
		});
	});

	// Show delete brand confirmation modal
	$("#feeShipInfoTable").on('click', '.delete-btn', function() {
		$("#deletedFeeShip").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete brand
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/feeship/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				//findAllBrands(1);
				searchFees(1);
			}
		});
	});

	// Submit add and update brand
	$('#saveFeeShipBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($feeShipInfoForm[0]);
		var feeShipId = formData.get("feeShipId");
		var isAddAction = feeShipId == undefined || feeShipId == "";

		$feeShipInfoForm.validate({
			ignore: [],
			rules: {
				'province.id': {
					required: isAddAction
				},
				'district.id': {
					required: isAddAction
				},
				'ward.id': {
					required: isAddAction
				},
				feeShip: {
					required: isAddAction
				},
				fromDate: {
					required: isAddAction
				}
			},
			messages: {
				'province.id': {
					required: "Vui lòng chọn Tỉnh/Thành phố"
				},
				'district.id': {
					required: "Vui lòng chọn Quận/Huyện"
				},
				'ward.id': {
					required: "Vui lòng chọn Phường/Xã"
				},
				feeShip: {
					required: "Vui lòng nhập phí giao hàng"
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($feeShipInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/feeship/api/" + (isAddAction ? "add" : "update"),
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
						$feeShipInfoModal.modal('hide');
						//findAllBrands(1);
						searchFees(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($feeShipInfoForm.find("#ward"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	$("#search").on('click', function() {
		searchFees(1);
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

function searchFees(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val()
	};
	$.ajax({
		url: "/admin/feeship/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderFeesTable(responseData.data.feeShipList);
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

function selectDistrict() {
	var id = $("#province").val();
	$.ajax({
		url: "/admin/address/api/findss?id=" + id,
		type: "GET",
		dataType: 'json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				var info = responseData.data;
				var op = '<option value="0" disabled selected>Chọn Quận/Huyện</option>';
				$("#district").empty().append(op);
				var rowHtmls = "";
				$.each(info, function(key, value) {
					rowHtmls = "<option value = '" + value.id + "' districtName='" + value.name + "'>"
						+ value.name
						+ "</option>";
					$("#district").append(rowHtmls);
				});
			}
			$("#district").on("change", function(e) { selectWard(); });
		}
	});
}

function selectWard() {
	var id = $("#district").val();
	$.ajax({
		url: "/admin/address/api/findss1?id=" + id,
		type: "GET",
		dataType: 'json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				var info = responseData.data;
				var op = '<option value="0" disabled selected>Chọn Phường/Xã</option>';
				$("#ward").empty().append(op);
				var rowHtmls = "";
				$.each(info, function(key, value) {
					rowHtmls = "<option value = '" + value.id + "'wardName='" + value.name + "'>"
						+ value.name
						+ "</option>";
					$("#ward").append(rowHtmls);
				});
			}
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
 * @param feeShipList
 */

function renderFeesTable(feeShipList) {

	var rowHtml = "";
	$("#feeShipInfoTable tbody").empty();
	$.each(feeShipList, function(key, value) {
		rowHtml = "<tr>"
			+ "<td class='text-center'>" + value.feeShipId + "</td>"
			+ "<td>" + value.province.name + "</td>"
			+ "<td align='center'>" + value.district.name + "</td>"
			+ "<td align='center'>" + value.ward.name + "</td>"
			+ "<td align='center'>" + formatNumber(value.feeShip) + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='edit-btn' data-id='" + value.feeShipId + "'><i class='fas fa-edit'></i></a> | <a class='delete-btn' data-name='" + "Phí giao hàng " + value.ward.name + ", " + value.district.name + ", " + value.province.name + "' data-id='" + value.feeShipId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#feeShipInfoTable tbody").append(rowHtml);
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