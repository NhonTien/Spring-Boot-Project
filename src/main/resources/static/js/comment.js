$(document).ready(function() {

	// Show products list when opening page

	searchComments(1);
	
	$('.pagination').on('click', '.page-link', function() {
		var pagerNumber = $(this).attr("data-index");
		searchComments(pagerNumber);
	})
	
	// Show delete product confirmation modal
	$("#commentInfoTableSearch").on('click', '.delete-btn', function() {
		$("#deletedComment").text($(this).data("name"));
		$("#deleteSubmitBtn").attr("data-id", $(this).data("id"));
		$('#confirmDeleteModal').modal('show');
	});

	// Submit delete product
	$("#deleteSubmitBtn").on('click', function() {
		$.ajax({
			url: "/admin/comment/api/delete/" + $(this).attr("data-id"),
			type: 'DELETE',
			dataType: 'json',
			contentType: 'application/json',
			success: function(responseData) {
				$('#confirmDeleteModal').modal('hide');
				showNotification(responseData.responseCode == 100, responseData.responseMsg);
				/*findAllProducts(1);*/
				searchComments(1);
			}
		});
	});
	
	$("#search").on('click', function() {
		searchComments(1);
	});
	
});

/**
 * Find products list with pager
 * 
 * @param pagerNumber
 */
function searchComments(pagerNumber) {
	var data = {
		"keyword": $("#keyword").val()
	};
	$.ajax({
		url: "/admin/comment/api/search/" + pagerNumber,
		type: 'POST',
		dataType: 'json',
		contentType: 'application/json',
		data: JSON.stringify(data),
		success: function(responseData) {
			if (responseData.responseCode == 100) {
				renderCommentsTable(responseData.data.commentList);
				renderPagination(responseData.data.paginationInfo);
			}
		}
	});
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
 * Render HTML for product table
 * 
 * @param commentsList
 */

function renderCommentsTable(commentList) {

	var rowHtmls = "";
	$("#commentInfoTableSearch tbody").empty();
	$.each(commentList, function(key, value) {
		rowHtmls = "<tr>"
			+ "<td>" + value.commentId + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.userEntity.lastname + " " + value.userEntity.firstname + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.userEntity.username + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.content + "</td>"
			+ "<td" + " align= " + '"left"' + ">" + value.productEntity.productName + "</td>"
			+ "<td" + " align= " + '"center"' + ">" + formatDateFull(value.createDate) + "</td>"
			+ "<td class='action-btns'>"
			+ "<a class='delete-btn' data-name='" + value.content + "' data-id='" + value.commentId + "'><i class='fas fa-trash-alt'></i></a>"
			+ "</td>"
			+ "</tr>";
		$("#commentInfoTableSearch tbody").append(rowHtmls);
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
		paginationInnerHtml += '<a class="pgComment">Page ' + paginationInfo.currentPage + ' of ' + paginationInfo.totalPage + '</a>'
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