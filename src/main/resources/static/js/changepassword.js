$(document).ready(function() {

	// Show products list when opening page
	
	var $changePasswordInfoForm = $('#changePasswordInfoForm');
	var $changePasswordInfoModal = $('#changePasswordInfoModal');
	
	// Show add brand modal
	$('#userChangePasswordInfoModal').on('click', function() {
		resetFormModal($changePasswordInfoForm);
		showModal($changePasswordInfoModal, "Đổi mật khẩu");
	});
	
	// Submit add and update product
	$('#changePasswordProfileBtn').on('click', function (event) {

		event.preventDefault();
		var formData = new FormData($changePasswordInfoForm[0]);
		var isAddAction = 0;
	
		$changePasswordInfoForm.validate({
			ignore: [],
			rules: {
				currentPassword: {
					required: isAddAction
				},
				newPassword: {
					required: isAddAction
				},
				confirmPassword: {
					required: isAddAction
				}
			},
			messages: {
				currentPassword: {
					required: "Vui lòng nhập mật khẩu hiện tại",
				},
				newPassword: {
					required: "Vui lòng nhập mật khẩu mới",
				},
				confirmPassword: {
					required: "Vui lòng nhập lại mật khẩu mới",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($changePasswordInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/user/api/changePassword",
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
						$changePasswordInfoModal.modal('hide');
						//findAllUser();
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($changePasswordInfoForm.find("#checkPassword"), responseData.responseMsg);
					}
				}
			});
		}
	});
	

});

