$(document).ready(function() {

	// Show products list when opening page
	searchProducts(1);

	getAllBrands();
	
	getAllCategories();
	
	findAllUser();

	// Show products list when clicking pagination button
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchProducts(pagerNumber);
	})

	var $productInfoForm = $('#productInfoForm');
	var $productInfoModal = $('#productInfoModal');

	// Show add product modal
	$('#addProductInfoModal').on('click', function() {
		resetFormModal($productInfoForm);
		showModalWithCustomizedTitle($productInfoModal, "Thêm");
		$('#logoImg img').attr('src', '/images/image-demo.png');
		$('#productId').closest(".form-group").addClass("d-none");
		$("#productImage .required-mask").removeClass("d-none");
		$('#discount').val(0);
	});

	// Show update product modal
	$("#productInfoTableSearch").on('click', '.edit-btn', function() {

		$("#productImage .required-mask").addClass("d-none");

		// Get brand info by product ID
		$.ajax({
			url: "/admin/product/api/find?id=" + $(this).data("id"),
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					var productInfo = responseData.data;
					resetFormModal($productInfoForm);
					showModalWithCustomizedTitle($productInfoModal, "Cập nhật");

					$('#productId').val(productInfo.productId);
					$('#productName').val(productInfo.productName);
					$('#quantity').val(productInfo.quantity);
					$('#price').val(productInfo.price);
					$('#categoryId').val(productInfo.categoryEntity.categoryId);
					$('#brandId').val(productInfo.brandEntity.brandId);
					$('#saleDate').val(productInfo.saleDate);
					$('#discount').val(fomartDiscount(productInfo.discount));
					$('#productSlug').val(productInfo.productSlug);
					$('#status').val(productInfo.status);
					$('#description').val(productInfo.description);

					var productImage = productInfo.image;
					if (productImage == null || productImage == "") {
						productImage = "/images/image-demo.png";
					}
					$("#logoImg img").attr("src", productImage);
					$("#image").val(productImage);
					$('#productId').closest(".form-group").removeClass("d-none");
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
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/product/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				if (responseData.responseCode == 100) {
					$('#confirmDeleteModal').modal('hide');
					showNotification(true, responseData.responseMsg);
					searchProducts(1);
				} else {
					showMsgOnField($(".modal-body").find("#deletedProductName"), responseData.responseMsg);
				}
			}
		});
	});

	// Submit add and update product
	$('#saveProductBtn').on('click', function(event) {

		event.preventDefault();
		var formData = new FormData($productInfoForm[0]);
		var productId = formData.get("productId");
		var isAddAction = productId == undefined || productId == "";

		$productInfoForm.validate({
			ignore: [],
			rules: {
				productName: {
					required: true,
					maxlength: 100
				},
				quantity: {
					required: isAddAction
				},
				price: {
					required: isAddAction
				},
				'categoryEntity.categoryId': {
					required: isAddAction
				},
				'brandEntity.brandId': {
					required: isAddAction
				},
				saleDate: {
					required: isAddAction
				},
				productSlug: {
					required: isAddAction
				},
				status: {
					required: isAddAction
				},
				imageFiles: {
					required: isAddAction,
				}
			},
			messages: {
				productName: {
					required: "Vui lòng nhập tên sản phẩm",
					maxlength: "Tên sản phẩm không quá 100 ký tự",
				},
				quantity: {
					required: "Vui lòng nhập số lượng",
				},
				price: {
					required: "Vui lòng nhập giá",
				},
				"categoryEntity.categoryId": {
					required: "Vui lòng chọn danh mục",
				},
				"brandEntity.brandId": {
					required: "Vui lòng chọn thương hiệu",
				},
				saleDate: {
					required: "Vui lòng chọn ngày mở bán",
				},
				productSlug: {
					required: "Vui lòng nhập Slug",
				},
				status: {
					required: "Vui lòng chọn trạng thái",
				},
				imageFiles: {
					required: "Vui lòng chọn ảnh sản phẩm",
				}
			},
			errorElement: "div",
			errorClass: "error-message-invalid"
		});

		if ($productInfoForm.valid()) {

			// POST data to server-side by AJAX
			$.ajax({
				url: "/admin/product/api/" + (isAddAction ? "add" : "update"),
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
						$productInfoModal.modal('hide');
						searchProducts(1);
						showNotification(true, responseData.responseMsg);
					} else {
						showMsgOnField($productInfoForm.find("#productName"), responseData.responseMsg);
					}
				}
			});
		}
	});

	$("#search").on('click', function() {
		searchProducts(1);
	});

	/*$("#keyword").keyup(function() {
		searchProducts(1);
	})
	$("#priceFrom").keyup(function() {
		searchProducts(1);
	})
	$("#priceTo").keyup(function() {
		searchProducts(1);
	})*/

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
 * Find products list with pager
 * 
 * @param pagerNumber
 */
function searchProducts(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val(),
		"priceFrom": $("#priceFrom").val(),
		"priceTo": $("#priceTo").val()
	};
	$.ajax({
		url: "/admin/product/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderProductsTableSearch(responseData.data.productList);
				renderPagination(responseData.data.paginationInfo);
			}
		}
	});
}

function getAllBrands() {
	$.ajax({
		url: "/admin/brand/api/getAll",
		type: 'GET',
		dataType: 'json',
		contentType: 'application/json',
		success: function(brandList) {
			renderBrandsSelect(brandList);
		}
	});
}

function getAllCategories() {
	$.ajax({
		url : "/admin/category/api/getAll",
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json',
		success : function(categoryList) {
			renderCategoriesSelect(categoryList);
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

function fomartStatus(n){
  if (n == true) {
     var a = "Hiển thị";
     return a;
  } else{
     var b = "Ẩn";
     return b;
  }
}

function fomartDiscount(n){
  if (n == null) {
     var a = 0;
     return a;
  } else{
	return n;
  }
}

/**
 * Render HTML for product table
 * 
 * @param productList
 */

function renderProductsTableSearch(productList) {

	var rowHtmls = "";
	$("#productInfoTableSearch tbody").empty();
	$.each(productList, function(key, value) {
		rowHtmls = "<tr>"
			+ "<td>" + value.productId + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.productName + "</td>"
			+ "<td>" + value.quantity + "</td>"
			//+ "<td" + " align= " + '"right"' + ">" + formatNumber(value.price) + "</td>"
			+ "<td" + " align= " + '"right"' + ">" + formatNumber(value.price) + "</td>"
			+ "<td>" + value.brandEntity.brandName + "</td>"
			+ "<td>" + formatDateFull(value.saleDate) + "</td>"
			+ "<td>" + fomartDiscount(value.discount) + "</td>"
			+ "<td class='text-center'><a href='" + value.image + "' data-toggle='lightbox' data-max-width='1000'><img class='img-fluid' src='" + value.image + "'></td>"
			+ "<td>" + fomartStatus(value.status) + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='edit-btn' data-id='" + value.productId + "'><i class='fas fa-edit'></i></a> | <a class='delete-btn' data-name='" + value.productName + "' data-id='" + value.productId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#productInfoTableSearch tbody").append(rowHtmls);
	});
}

function renderBrandsSelect(brandList) {
	var op = '<option value="" disabled selected>--- Chọn thương hiệu ---</option>'
	var rowHtmlss = "";
	$("#selectbrand select").empty();
	$.each(brandList, function(key, value) {
		rowHtmlss = '<option value="' + value.brandId + '">'
			+ value.brandName
			+ "</option>";
		$("#selectbrand select").append(op + rowHtmlss);
	});
}

function renderCategoriesSelect(categoryList) {
	var op = '<option value="" disabled selected>--- Chọn danh mục ---</option>'
	var rowHtmlss = "";
	$("#selectcategory select").empty();
	$.each(categoryList, function(key, value) {
		rowHtmlss = '<option value="' + value.categoryId + '">'
				+	value.categoryName
				+	"</option>";
		$("#selectcategory select").append(op + rowHtmlss);
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