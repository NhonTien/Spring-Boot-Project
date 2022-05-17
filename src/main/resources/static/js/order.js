$(document).ready(function() {

	// Show products list when opening page
	searchOrders(1);
	
	findAllUser();

	// Show products list when clicking pagination button
	
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchOrders(pagerNumber);
	})
	
	var $orderInfoForm = $('#orderInfoForm');
	var $orderInfoModal = $('#orderInfoModal');

	// Show update product modal
	$("#orderInfoTable").on('click', '.edit-btn', function() {

		$("#productImage .required-mask").addClass("d-none");
		$("tfoot .discount").removeClass("d-none");
		$("tfoot .feeShip").removeClass("d-none");
		$("tfoot .feeship").addClass("d-none");
		// Get brand info by product ID
		$.ajax({
			url : "/admin/order/api/finds?id=" + $(this).data("id"),
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json',
			success : function(responseData) {
				if (responseData.responseCode == 100) {
					var orderInfo = responseData.data;
					resetFormModal($orderInfoForm);
					showModal($orderInfoModal);
					$('#total').val(formatNumber(orderInfo[0].order.total));
					$('#totalQuantity').val(orderInfo[0].order.totalQuantity);
					if(orderInfo[0].order.feeShip != 0 && orderInfo[0].order.feeShip != null){
						$('#feeShip').val(formatNumber(orderInfo[0].order.feeShip));
					} else{
						$("tfoot .feeShip").addClass("d-none");
						$('#feeship').val("Miễn phí");
						$("tfoot .feeship").removeClass("d-none");
					}
					if(orderInfo[0].order.discount != 0 && orderInfo[0].order.discount != null){
						$('#discount').val(formatNumber(orderInfo[0].order.discount));
					} else{
						
						$("tfoot .discount").addClass("d-none");
					}
					$('#totalPrice').val(formatNumber(orderInfo[0].order.total + orderInfo[0].order.feeShip - orderInfo[0].order.discount));
					$('#names').val("Họ và tên:			" + orderInfo[0].order.userEntity.lastname + " " + orderInfo[0].order.userEntity.firstname);
					$('#phoneNumber').val("Số điện thoại:			" + orderInfo[0].order.userEntity.phoneNumber);
					$('#addresss').val("Địa chỉ:				" + orderInfo[0].order.aptSuit + ", " + orderInfo[0].order.ward.name + ", " + orderInfo[0].order.district.name + ", " + orderInfo[0].order.province.name);
					$('#status').val("Trạng thái đơn hàng:	" + fomart(orderInfo[0].order.confirm));
					$('#confirm').val(orderInfo[0].order.confirm);
					$('#id').val(orderInfo[0].order.id);
					renderOrderDetailsTable(orderInfo);
				}
			}
		});
	});

	// Show delete product confirmation modal
	$("#productInfoTableSearch").on('click', '.delete-btn', function() {
		$("#deletedProductName").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete product
	$("#deleteSubmitBtn").on('click' , function() {
		$.ajax({
			url : "/admin/product/api/delete/" + $(this).attr("data-id"),
			type : 'DELETE',
			dataType : 'json',
			contentType : 'application/json',
			success : function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				/*findAllProducts(1);*/
				searchProducts(1);
			}
		});
	});

	// Submit add and update product
	$('#saveOrderBtn').on('click', function () {

		var formData = new FormData($orderInfoForm[0]);
		// POST data to server-side by AJAX
		$.ajax({
			url: "/admin/order/api/update",
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
					$orderInfoModal.modal('hide');
					searchOrders(1);
					showNotification(true, responseData.responseMsg);
				} else {
					//showMsgOnField($orderInfoForm.find("#productName"), responseData.responseMsg);
				}
			}
		});
	});
	
	// Submit add and update product
	$('#saveOrderCancelBtn').on('click', function() {

		var formData = new FormData($orderInfoForm[0]);
		// POST data to server-side by AJAX
		$.ajax({
			url: "/admin/order/api/updateOrder",
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
					$orderInfoModal.modal('hide');
					/*findAllProducts(1);*/
					searchOrders(1);
					showNotification(true, responseData.responseMsg);
				} else {
					//showMsgOnField($orderInfoForm.find("#productName"), responseData.responseMsg);
				}
			}
		});
	});
	
	$("#search").on('click' , function() {
		searchOrders(1);
	});
	
	$(function(){
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) $('#goTop').fadeIn();
			else $('#goTop').fadeOut();
		});
		$('#goTop').click(function () {
			$('body,html').animate({scrollTop: 0}, 'slow');
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
function searchOrders(pagerNumber) {
	var data= {
        "name": $("#name").val(),
		"address": $("#address").val(),
		"priceFrom": $("#orderDate").val()
    };
	$.ajax({
		url : "/admin/order/api/search/" + pagerNumber,
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json',
		data: JSON.stringify(data),
		success : function(responseData) {
			if (responseData.responseCode == 100) {
				renderOrdersTable(responseData.data.orderList);
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

function fomart(n){
  if (n == true) {
     var a = "Đã xác nhận";
     return a;
  } else{
     var b = "Đang chờ xác nhận";
     return b;
  }
}

function fomartAvailable(n){
  if (n == true) {
     var a = "Đã đặt hàng";
     return a;
  } else{
     var b = "Đã hủy";
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
 * @param orderList
 */
function renderOrdersTable(orderList) {

	var rowHtml = "";
	$("#orderInfoTable tbody").empty();
	$.each(orderList, function(key, value) {
		rowHtml = "<tr>"
				+		"<td>" + value.id + "</td>"
				+		"<td" + " align= " + '"left"' + ">" + value.userEntity.lastname + " " + value.userEntity.firstname + "</td>"
				+		"<td" + " align= " + '"right"' + ">" + value.userEntity.phoneNumber + "</td>"
				+		"<td>" + value.aptSuit + ", " + value.ward.name + ", " + value.district.name + ", " + value.province.name + "</td>"
				+		"<td>" + formatDateFull(value.orderDate) + "</td>"
				+		"<td" + " align= " + '"right"' + ">" + formatNumber(value.total + value.feeShip - value.discount) + "</td>"
				+		"<td>" + fomartAvailable(value.available) + "</td>"
				+		"<td>" + fomart(value.confirm) + "</td>"
				+		"<td class='action-btns'>"
				+			"<a class='edit-btn' data-id='" + value.id + "'><i class='fas fa-info-circle'></i></a>"
				+		"</td>"
				+	"</tr>";
		$("#orderInfoTable tbody").append(rowHtml);
	});
}

function renderOrderDetailsTable(orderDetails) {

	var rowHtml = "";
	$("#orderDetailsTable tbody").empty();
	$.each(orderDetails, function(key, value) {
		rowHtml = "<tr>"
				+		"<td>" + value.id + "</td>"
				+		"<td" + " align= " + '"left"' + ">" + value.productEntity.productName + "</td>"
				+		"<td>" + '<img class="card-img-top" src="' + value.productEntity.image + '"' + "</td>"
				+		"<td" + " align= " + '"right"' + ">" + formatNumber(value.price) + "</td>"
				+		"<td>" +  value.quantity + "</td>"
				+		"<td" + " align= " + '"right"' + ">" + formatNumber(value.amount) + "</td>"
				+	"</tr>";
		$("#orderDetailsTable tbody").append(rowHtml);
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