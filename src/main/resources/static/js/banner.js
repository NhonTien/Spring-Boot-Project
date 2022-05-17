$(document).ready(function() {

	// Show products list when opening page

	searchBanners(1);
	
	findAllUser();

	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchBanners(pagerNumber);
	})

	var $bannerInfoForm = $('#bannerInfoForm');
	var $bannerInfoModal = $('#bannerInfoModal');

	// Show add product modal
	$('#addBannerInfoModal').on('click', function() {
		resetFormModal($bannerInfoForm);
		showModalWithCustomizedTitle($bannerInfoModal, "Thêm");
		$('#logoImg img').attr('src', '/images/image-demo.png');
		$('#bannerId').closest(".form-group").addClass("d-none");
		$("#bannerImage .required-mask").removeClass("d-none");
	});

	// Show update product modal
	$("#bannerInfoTableSearch").on('click', '.edit-btn', function() {

		$("#bannerImage .required-mask").addClass("d-none");

		// Get brand info by product ID
		$.ajax({
			url: "/admin/banner/api/find?id=" + $(this).data("id"),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					var bannerInfo = responseData.data;
					resetFormModal($bannerInfoForm);
					showModalWithCustomizedTitle($bannerInfoModal, "Cập nhật");

					$('#bannerId').val(bannerInfo.bannerId);
					$('#bannerName').val(bannerInfo.bannerName);
					$('#status').val(bannerInfo.status);
					$('#description').val(bannerInfo.description);

					var bannerImage = bannerInfo.image;
					if (bannerImage == null || bannerImage == "") {
						bannerImage = "/images/image-demo.png";
					}
					$("#logoImg img").attr("src", bannerImage);
					$("#image").val(bannerImage);
					$('#bannerId').closest(".form-group").removeClass("d-none");
				}
			}
		});
	});

	// Show delete product confirmation modal
	$("#bannerInfoTableSearch").on('click', '.delete-btn', function() {
		$("#deletedBannerName").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete product
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/banner/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				/*findAllProducts(1);*/
				searchBanners(1);
			}
		});
	});

	// Submit add and update product
	$('#saveBannerBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($bannerInfoForm[0]);
		var bannerId = formData.get("bannerId");
		var isAddAction = bannerId == undefined || bannerId == "";

		$bannerInfoForm.validate({
			ignore: [],
			rules: {
				bannerName: {
					required: isAddAction
				},
				imageFiles: {
					required: isAddAction,
				},
				status: {
					required: isAddAction
				}
			},
			messages: {
				bannerName: {
					required: "Vui lòng nhập tên Banner",
				},
				imageFiles: {
					required: "Vui lòng chọn ảnh",
				},
				status: {
					required: "Vui lòng chọn trạng thái",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($bannerInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/banner/api/" + (isAddAction ? "add" : "update"),
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
						$bannerInfoModal.modal('hide');
						/*findAllProducts(1);*/
						searchBanners(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($bannerInfoForm.find("#bannerName"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	$("#search").on('click', function() {
		searchBanners(1);
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
 * Find products list with pager
 * 
 * @param pagerNumber
 */
function searchBanners(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val()
	};
	$.ajax({
		url: "/admin/banner/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderBannersTable(responseData.data.bannerList);
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
/*
	* "vi"--"."
	* "en"--","
*/

function formatDate(n) {
	var curDt = new Date(n);
	var MM = curDt.getMonth() + 1;
	var dd = curDt.getDate();
	var yyyy = curDt.getFullYear();
	var date = dd + '/' + MM + '/' + yyyy;
	return date;
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

function fomartStatus(n){
  if (n == true) {
     var a = "Hiển thị";
     return a;
  } else{
     var b = "Ẩn";
     return b;
  }
}

/*	Convert dd/mm/yyyy = yyyy/mm/dd
	* function formatDate(n){
	*	 var d=new Date(n.split("/").reverse().join("-"));
	*	 var dd=d.getDate();
	*	 dd = (dd < 10) ? '0' + dd : dd;
	*	 var mm=d.getMonth()+1;
	*	 mm = (mm < 10) ? '0' + mm : mm;
	*	 var yy=d.getFullYear();
	*	 var datefull = yy + "/" + mm + "/" + dd;
	*	 return datefull;
}
*/

/**
 * Render HTML for product table
 * 
 * @param bannersList
 */

function renderBannersTable(bannerList) {

	var rowHtmls = "";
	$("#bannerInfoTableSearch tbody").empty();
	$.each(bannerList, function(key, value) {
		rowHtmls = "<tr>"
			+ "<td>" + value.bannerId + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.bannerName + "</td>"
			+ "<td class='text-center'><a href='" + value.image + "' data-toggle='lightbox' data-max-width='1000'><img class='img-fluid' src='" + value.image + "'></td>"
			+ "<td>" + fomartStatus(value.status) + "</td>"
			+ "<td>" + value.description + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='edit-btn' data-id='" + value.bannerId + "'><i class='fas fa-edit'></i></a> | <a class='delete-btn' data-name='" + value.bannerName + "' data-id='" + value.bannerId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#bannerInfoTableSearch tbody").append(rowHtmls);
	});
}


/**
 * Render HTML for pagination bar
 * 
 * @param paginationInfo
 */
function renderPagination(paginationInfo) {

	var paginationInnerHtml = "";
	//var rowCount = $('#productInfoTableSearch >tbody >tr').length;
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