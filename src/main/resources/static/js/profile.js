$(document).ready(function() {

	// Show products list when opening page
	findAllUser();
	
	searchUsers(1);
	
	getAllCategories();
	
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchUsers(pagerNumber);
	})
	
	var $userInfoForm = $('#userInfoForm');
	var $userInfoModal = $('#userInfoModal');
	
	var $userAdminInfoForm = $('#userAdminInfoForm');
	var $userAdminInfoModal = $('#userAdminInfoModal');
	
	// Show add brand modal
	$('#addUserInfoModal').on('click', function() {
		resetFormModal($userAdminInfoForm);
		showModal($userAdminInfoModal, "Thêm");
	});

	// Show update product modal
	$("#editProfileInfoModal").on('click', function() {

		$("#userImage .required-mask").addClass("d-none");

		// Get brand info by product ID
		$.ajax({
			url : "/api/findUser?userId=" + $("#userId1").val(),
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json',
			success : function(responseData) {
				if (responseData.responseCode == 100) {
					var userInfo = responseData.data;
					resetFormModal($userInfoForm);
					showModal($userInfoModal);

					$('#userId').val(userInfo.userId);
					$('#lastname').val(userInfo.lastname);
					$('#firstname').val(userInfo.firstname);
					$('#phoneNumber').val(userInfo.phoneNumber);
					$('#username').val(userInfo.username);
					$('#password').val(userInfo.password);
					$('#createDate').val(userInfo.createDate);
					$('#role').val(userInfo.role);

					var userImage = userInfo.image;
					if (userImage == null || userImage == "") {
						userImage = "/images/image-user.png";
					}
					$("#logoImg img").attr("src", userImage);
					$("#image").val(userImage);
					//$('#userId').closest(".form-group").removeClass("d-none");
				}
			}
		});
	});

	// Submit add and update product
	$('#saveUserBtn').on('click', function (event) {

		event.preventDefault();
		var formData = new FormData($userInfoForm[0]);
		var userId = formData.get("userId");
		var isAddAction = userId == undefined || userId == "";
	
		$userInfoForm.validate({
			ignore: [],
			rules: {
				firstname: {
					required: isAddAction
				},
				lastname: {
					required: isAddAction
				},
				phoneNumber: {
					required: isAddAction
				},
				username: {
					required: isAddAction
				},
				password: {
					required: isAddAction,
					minlength: 8
				},
				passwordConfirm: {
					required: isAddAction
				}
			},
			messages: {
				firstname: {
					required: "Vui lòng nhập tên",
				},
				lastname: {
					required: "Vui lòng nhập họ",
				},
				phoneNumber: {
					required: "Vui lòng nhập số điện thoại",
				},
				username: {
					required: "Vui lòng nhập Username",
				},
				password: {
					required: "Vui lòng nhập mật khẩu",
					minlength: "Mật khẩu phải có 8 ký tự trở lên"
				},
				passwordConfirm: {
					required: "Vui lòng nhập lại mật khẩu",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($userInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/api/add",
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
						//$productInfoModal.modal('hide');
						showNotification(true, responseData.responseMsg);
						window.location = "http://localhost:8881/login";
					} else {
						showMsgOnField($userInfoForm.find("#message"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	// Submit add and update product
	$('#saveUserAdminBtn').on('click', function (event) {

		event.preventDefault();
		var formData = new FormData($userAdminInfoForm[0]);
		var userId = formData.get("userId");
		var isAddAction = userId == undefined || userId == "";
	
		$userAdminInfoForm.validate({
			ignore: [],
			rules: {
				firstname: {
					required: isAddAction
				},
				lastname: {
					required: isAddAction
				},
				phoneNumber: {
					required: isAddAction
				},
				username: {
					required: isAddAction
				},
				role: {
					required: isAddAction
				},
				password: {
					required: isAddAction,
					minlength: 8
				},
				passwordConfirm: {
					required: isAddAction
				}
			},
			messages: {
				firstname: {
					required: "Vui lòng nhập tên",
				},
				lastname: {
					required: "Vui lòng nhập họ",
				},
				phoneNumber: {
					required: "Vui lòng nhập số điện thoại",
				},
				username: {
					required: "Vui lòng nhập Username",
				},
				role: {
					required: "Vui lòng chọn quyền",
				},
				password: {
					required: "Vui lòng nhập mật khẩu",
					minlength: "Mật khẩu phải có 8 ký tự trở lên"
				},
				passwordConfirm: {
					required: "Vui lòng nhập lại mật khẩu",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($userAdminInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/user/api/add",
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
						$userAdminInfoModal.modal('hide');
						searchUsers(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($userAdminInfoForm.find("#message"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	// Submit add and update product
	$('#saveUserProfileBtn').on('click', function (event) {

		event.preventDefault();
		var formData = new FormData($userInfoForm[0]);
		var userId = '';
		var isAddAction = userId == undefined || userId == "";
	
		$userInfoForm.validate({
			ignore: [],
			rules: {
				firstname: {
					required: isAddAction
				},
				lastname: {
					required: isAddAction
				},
				phoneNumber: {
					required: isAddAction
				},
				username: {
					required: isAddAction
				},
				password: {
					required: isAddAction
				},
				role: {
					required: isAddAction
				}
			},
			messages: {
				firstname: {
					required: "Vui lòng nhập tên",
				},
				lastname: {
					required: "Vui lòng nhập họ",
				},
				phoneNumber: {
					required: "Vui lòng nhập số điện thoại",
				},
				username: {
					required: "Vui lòng nhập Username",
				},
				password: {
					required: "Vui lòng nhập mật khẩu",
				},
				role: {
					required: "Vui lòng chọn quyền",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($userInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/api/update",
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
						$userInfoModal.modal('hide');
						findAllUser();
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($userInfoForm.find("#username"), responseData.responseMsg);
					}
				}
			});
		}
	});
	
	// Show delete product confirmation modal
	$("#userInfoTableSearch").on('click', '.delete-btn', function() {
		$("#deletedUsername").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete product
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/user/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				/*findAllProducts(1);*/
				searchUsers(1);
			}
		});
	});
	
	$("#search").on('click', function() {
		searchUsers(1);
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

function searchUsers(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val(),
		"role": $("#role").val()
	};
	$.ajax({
		url: "/admin/user/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderUsersTable(responseData.data.userList);
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
			$('#lastname1').val(data.lastname);
			$('#firstname1').val(data.firstname);
			$('#username1').val(data.username);
			$('#phoneNumber1').val(data.phoneNumber);
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

function renderUsersTable(userList) {

	var rowHtmls = "";
	$("#userInfoTableSearch tbody").empty();
	$.each(userList, function(key, value) {
		rowHtmls = "<tr>"
			+ "<td>" + value.userId + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.lastname + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.firstname + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.username + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.phoneNumber + "</td>"
			+ "<td class='text-center'><a href='" + value.image + "' data-toggle='lightbox' data-max-width='1000'><img class='img-fluid' src='" + value.image + "'></td>"
			+ "<td class='action-btns'>"
			+ "<a class='delete-btn' data-name='" + value.username + "' data-id='" + value.userId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#userInfoTableSearch tbody").append(rowHtmls);
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
	//var rowCount = $('#productInfoTableSearch >tbody >tr').length;
	if (paginationInfo.pageNumberList.length > 0) {
		$("ul.pagination").empty();
		paginationInnerHtml += '<a class="pgUser">Page ' + paginationInfo.currentPage + ' of ' + paginationInfo.totalPage + '</a>'
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
