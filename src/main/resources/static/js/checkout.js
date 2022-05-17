$(document).ready(function() {

	// Show products list when opening page

	getAllCategories();
	
	findAllUser();
	
	var op = "<p>" + formatNumber($("#total").val()) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
				$("#totalPrice").empty();
				$("#totalPrice").append(op);
	$("#ttpr").val("");
	$("#ttpr").val($("#total").val());

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
	
	$("#promoCodeCheckBtn").on('click', function() {
		$.ajax({
			url: "/admin/promo/api/find1?promoCode=" + $("#promoCode").val(),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100){
					var promoCodeInfo = responseData.data;
					var op = "<p>" + formatNumber(promoCodeInfo.discount) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
					$("#promoCodePrice").val("");
					$("#promoC").val(promoCodeInfo.promoCode);
					$("#promoCodePrice").val(promoCodeInfo.discount);
					//$('#promoCodePrice').removeClass("d-none");
					$("#promoCodePriceGift").empty();
					$("#divPromo").addClass("d-flex");
					$("#promoCodePriceGift").append(op);
					//$("#ttpri").val("");
					//$("#ttpri").val(parseFloat($("#ttpr").val()) + parseFloat($("#promoCodePrice").val()));
					let total = parseFloat($("#ttpr").val()) - parseFloat($("#promoCodePrice").val());
					$("#ttpr").val("");
					$("#ttpr").val(total);
					var op = "<p>" + formatNumber($("#ttpr").val()) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
					$("#totalPrice").empty();
					$("#totalPrice").append(op);
					$("#promoCode").val("")
					showNotification(true, responseData.responseMsg);
					//findAllBrands(1);
					//searchPromos(1);
				} else{
					$("#promoCodePrice").val("");
					$("#promoCodePrice").val(0);
					let total = parseFloat($("#total").val()) + parseFloat($("#feeShip").val()) - parseFloat($("#promoCodePrice").val());
					$("#ttpr").val("");
					$("#ttpr").val(total);
					var op = "<p>" + formatNumber($("#ttpr").val()) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
					$("#totalPrice").empty();
					$("#totalPrice").append(op);
					//$('#promoCodePrice').addClass("d-none");
					$("#divPromo").removeClass("d-flex");
					showNotification(false, responseData.responseMsg);
				}
			}
		});
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

function checkStuff() {
	var name = document.saveOrder.name;
	var phoneNumber = document.saveOrder.phoneNumber;
	var address = document.saveOrder.address;
	var msgName = document.getElementById('msgName');
	var msgPhoneNumber = document.getElementById('msgPhoneNumber');
	var msgAddress = document.getElementById('msgAddress');

	if (name.value == phoneNumber.value == address.value == "") {
		msgName.style.display = 'block';
		msgPhoneNumber.style.display = 'block';
		msgAddress.style.display = 'block';
		name.focus();
	}

	if (name.value == phoneNumber.value == "") {
		msgName.style.display = 'block';
		msgPhoneNumber.style.display = 'block';
		name.focus();
	}

	if (phoneNumber.value == address.value == "") {
		msgPhoneNumber.style.display = 'block';
		msgAddress.style.display = 'block';
		phoneNumber.focus();
	}

	if (name.value == address.value == "") {
		msgName.style.display = 'block';
		msgAddress.style.display = 'block';
		name.focus();
	}

	if (name.value == "") {
		msgName.style.display = 'block';
		name.focus();
		return false;
	} else {
		msgName.style.display = 'none';
	}

	if (phoneNumber.value == "") {
		msgPhoneNumber.style.display = 'block';
		phoneNumber.focus();
		return false;
	} else {
		msgPhoneNumber.style.display = 'none';
	}

	if (address.value == "") {
		msgAddress.style.display = 'block';
		address.focus();
		return false;
	} else {
		msgAddress.style.display = 'none';
	}
}

function formatNumber(num) {
	var n = Number(num);
	return n.toLocaleString("vi");
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
			$("#ward").on("change", function(e) { selectFeeShip(); });
		}
	});
}

function selectFeeShip() {
	var id = $("#ward").val();
	$.ajax({
		url: "/admin/feeship/api/find1?id=" + id,
		type: "GET",
		dataType: 'json',
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				$("#feeShip").empty();
				$("#fee").empty();
				var info = responseData.data;
				var op = "<p>" + formatNumber(info.feeShip) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
				//$("#feeShip").removeClass("d-none");
				$("#divfee").addClass("d-flex");
				$("#feeShip").val(info.feeShip);
				$("#fee").append(op);
				var op = "<p>" + formatNumber(parseFloat($("#total").val()) + parseFloat(info.feeShip)) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
				$("#totalPrice").empty();
				$("#totalPrice").append(op);
				$("#ttpr").val(parseFloat($("#total").val()) + parseFloat(info.feeShip));
				let totalPrice = parseFloat($("#total").val()) + parseFloat($("#feeShip").val());
				let ttpr = parseFloat(totalPrice) - parseFloat($("#promoCodePrice").val());
				$("#ttpr").val("");
				$("#ttpr").val(ttpr);
				var op = "<p>" + formatNumber($("#ttpr").val()) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
				$("#totalPrice").empty();
				$("#totalPrice").append(op);
				//$("#ttpri").val("");
				//$("#ttpri").val(parseFloat($("#ttpr").val()) + parseFloat($("#promoCodePrice").val()))
			} else{
				$("#feeShip").empty();
				$("#fee").empty();
				var op = "<p>Miễn phí</p>";
				//$("#feeShip").removeClass("d-none");
				$("#divfee").addClass("d-flex");
				$("#feeShip").val(0);
				$("#fee").append(op);
				//$("#ttpr").val($("#total").val() + $("#feeShip").val());
				let totalPrice = parseFloat($("#total").val()) + parseFloat($("#feeShip").val());
				let ttpr = parseFloat(totalPrice) - parseFloat($("#promoCodePrice").val());
				$("#ttpr").val("");
				$("#ttpr").val(ttpr);
				var op = "<p>" + formatNumber($("#ttpr").val()) + '<span class="cart-total"><sup><u>đ</u></sup></span>' + "</p>";
				$("#totalPrice").empty();
				$("#totalPrice").append(op);
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
			var os = data.lastname + " " + data.firstname;
			$('#userId').val(data.userId);
			$('#username').val(data.fullname);
			$('#phoneNumber').val(data.phoneNumber);
			$('#username').empty();
			$('#username').val(os);
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