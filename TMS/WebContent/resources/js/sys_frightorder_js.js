/**
 * 
 */

	function frightOrderAddForm(src){
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderAddForm.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function searchFromList(src, value){
		
		$("#frightOrderForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/searchFromFrightOrderList.html",				
			data : "search_value=" + value,
			success : function(response) {
				$('#frightOrderForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function checkRelatedInformation(tri_id, src){
		
		$("#filteredTrailList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/frightOrder/checkRelatedInformation.html",				
			data : "tri_id=" + tri_id,
			success : function(response) {
				$('#filteredTrailList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function insertNewFrightOrder(src){
		
		var counter = 0;
		
		var a_id = $('#a_id').val();
		var fon = $('#fon').val();
		var tri_id = $('#tri_id').val();
		var tli_id = $('#tli_id').val();
		var dr_id = $('#dr_id').val();
		var trip = $('#trip').val();
		var total_coupon_amount = $('#total_coupon_amount').val();
		var commission = $('#commission').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(a_id == "-"){
			
			document.getElementById("a_id").className += " form-textboxerror";
			$('#a_id').attr('placeholder', 'Association required');
			counter++;
		}
		if(fon == ""){
			
			document.getElementById("fon").className += " form-textboxerror";
			$('#fon').attr('placeholder', 'Fright Order Number is required');
			counter++;
		}
		if(tri_id == "-"){
			
			document.getElementById("tri_id").className += " form-textboxerror";
			$('#tri_id').attr('placeholder', 'Truck plate number required');
			counter++;
		}
		if(tli_id == "-"){
			
			document.getElementById("tli_id").className += " form-textboxerror";
			$('#tli_id').attr('placeholder', 'Trail plate number required');
			counter++;
		}
		if(dr_id == "-"){
			
			document.getElementById("dr_id").className += " form-textboxerror";
			$('#dr_id').attr('placeholder', 'Driver required');
			counter++;
		}
		if(total_coupon_amount == ""){
			
			document.getElementById("total_coupon_amount").className += " form-textboxerror";
			$('#total_coupon_amount').attr('placeholder', 'Total coupon amount required');
			counter++;
		}
		if(commission == ""){
			
			document.getElementById("commission").className += " form-textboxerror";
			$('#commission').attr('placeholder', 'Commission required');
			counter++;
		}
		if(date_from == ""){
			
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_from == ""){
			
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		
		if(counter == 0){
			$("#addFormProcess").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "POST",			
				url : "/TMS/frightOrder/insertNewFrightOrder.html",				
				data : "a_id=" + a_id + "&fon=" + fon + "&tri_id=" + tri_id + "&tli_id=" + tli_id + "&dr_id=" + dr_id + "&trip=" + trip + "&total_coupon_amount=" + total_coupon_amount + "&commission=" + commission + "&date_from=" + date_from + "&date_to=" + date_to,
				success : function(response) {
					$('#addFormProcess').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function updateFrightOrder(src, fo_id, old_tri_id, old_tli_id){
		
		var counter = 0;
		
		var a_id = $('#a_id').val();
		var fon = $('#fon').val();
		var tri_id = $('#tri_id').val();
		var tli_id = $('#tli_id').val();
		var dr_id = $('#dr_id').val();
		var trip = $('#trip').val();
		var total_coupon_amount = $('#total_coupon_amount').val();
		var commission = $('#commission').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		var fo_status = $('#fo_status').val();
		
		if(a_id == "-"){
			
			document.getElementById("a_id").className += " form-textboxerror";
			$('#a_id').attr('placeholder', 'Association required');
			counter++;
		}
		if(fon == ""){
			
			document.getElementById("fon").className += " form-textboxerror";
			$('#fon').attr('placeholder', 'Fright Order Number is required');
			counter++;
		}
		if(tri_id == "-"){
			
			document.getElementById("tri_id").className += " form-textboxerror";
			$('#tri_id').attr('placeholder', 'Truck plate number required');
			counter++;
		}
		if(tli_id == "-"){
			
			document.getElementById("tli_id").className += " form-textboxerror";
			$('#tli_id').attr('placeholder', 'Trail plate number required');
			counter++;
		}
		if(dr_id == "-"){
			
			document.getElementById("dr_id").className += " form-textboxerror";
			$('#dr_id').attr('placeholder', 'Driver required');
			counter++;
		}
		if(total_coupon_amount == ""){
			
			document.getElementById("total_coupon_amount").className += " form-textboxerror";
			$('#total_coupon_amount').attr('placeholder', 'Total coupon amount required');
			counter++;
		}
		if(commission == ""){
			
			document.getElementById("commission").className += " form-textboxerror";
			$('#commission').attr('placeholder', 'Commission required');
			counter++;
		}
		if(date_from == ""){
			
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_from == ""){
			
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#addFormProcess").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "POST",			
				url : "/TMS/frightOrder/updateNewFrightOrder.html",				
				data : "fo_id=" + fo_id + "&a_id=" + a_id + "&fon=" + fon + "&tri_id=" + tri_id + "&tli_id=" + tli_id + "&dr_id=" + dr_id + "&trip=" + trip + "&total_coupon_amount=" + total_coupon_amount + "&commission=" + commission + "&date_from=" + date_from + "&date_to=" + date_to + "&fo_status=" + fo_status + "&old_tri_id=" + old_tri_id + "&old_tli_id=" + old_tli_id,
				success : function(response) {
					$('#addFormProcess').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function closeFrightOrderForm(fo_id, src){
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/frightOrderCloseForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function closeFrightOrder(fo_id, src){
		
		var counter = 0;
		
		var dispatchDocRefNum = $('#dispatchDocRefNum').val();
		var deliveryDocRefNum = $('#deliveryDocRefNum').val();
		var deliveredQuantity = $('#deliveredQuantity').val();
		var deliveryDate = $('#deliveryDate').val();
		var remark = $('#remark').val();
		
		if(dispatchDocRefNum == ""){
			document.getElementById("dispatchDocRefNum").className += " form-textboxerror";
			$('#dispatchDocRefNum').attr('placeholder', 'Dispatch document reference number required');
			counter++;
		}
		if(deliveryDocRefNum == ""){
			document.getElementById("deliveryDocRefNum").className += " form-textboxerror";
			$('#deliveryDocRefNum').attr('placeholder', 'Delivery document reference number required');
			counter++;
		}
		if(deliveredQuantity == ""){
			document.getElementById("deliveredQuantity").className += " form-textboxerror";
			$('#deliveredQuantity').attr('placeholder', 'Delivered quantity required');
			counter++;
		}
		if(deliveryDate == ""){
			document.getElementById("deliveryDate").className += " form-textboxerror";
			$('#deliveryDate').attr('placeholder', 'Delivery date required');
			counter++;
		}
		
		if(counter == 0){
			$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/closeFrightOrder.html",				
				data : "fo_id=" + fo_id + "&dispatch_doc_ref_no=" + dispatchDocRefNum + "&delivery_doc_ref_no=" + deliveryDocRefNum + "&delivered_quantity=" + deliveredQuantity + "&delivery_date=" + deliveryDate + "&fright_note=" + remark,
				success : function(response) {
					$('#bodycontent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function showFrightOrderDetail(fo_id, src){
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/frightOrderDetailInformation.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getAdditionalAdvancePaymentForm(src, fo_id){
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/frightOrderAdditionAdvancePaymentForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveAdditionalAdvancePayment(src, fo_id){
		
		var counter = 0;
		
		var advance_payment_amount = $('#advance_payment_amount').val();
		var send_reference_number = $('#send_reference_number').val();
		var send_date = $('#send_date').val();
		
		if(advance_payment_amount == ""){
			
			document.getElementById("advance_payment_amount").className += " form-textboxerror";
			$('#advance_payment_amount').attr('placeholder', 'Advance payment amount required');
			counter++;
		}
		if(send_reference_number == ""){
			
			document.getElementById("send_reference_number").className += " form-textboxerror";
			$('#send_reference_number').attr('placeholder', 'Reference number required');
			counter++;
		}
		if(send_date == ""){
			
			document.getElementById("send_date").className += " form-textboxerror";
			$('#send_date').attr('placeholder', 'Send date required');
			counter++;
		}
		if(counter == 0){
			alert("fo_id=" + fo_id + "&advance_payment_amount=" + advance_payment_amount + "&send_reference_number=" + send_reference_number + "&send_date=" + send_date);
			$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/advancePayment/saveAdditionalAdvancePayment.html",				
				data : "fo_id=" + fo_id + "&advance_payment_amount=" + advance_payment_amount + "&send_reference_number=" + send_reference_number + "&send_date=" + send_date,
				success : function(response) {
					$('#bodycontent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getIncompleteFrightOrderList(src){
		
		$("#frightOrderForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getIncompleteFrightOrderList.html",				
			data : "",
			success : function(response) {
				$('#frightOrderForm').html(response);
				document.getElementById("incompFObtn").className += " normal_btn_active";
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addFrightOrderDetailInformation(src, fo_id){
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/addFrightOrderDetailInformation.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function insertFrightOrderDetailInformation(src, fo_id){
		
		var counter = 0;
		
		var clientOrganization = $('#clientOrganization').val();
		var loadingType = $('#loadingType').val();
		var originPlace = $('#originPlace').val();
		var destinationPlace = $('#destinationPlace').val();
		var initialKm = $('#initialKm').val();
		var loadingQuantity = $('#loadingQuantity').val();
		var distance = $('#distance').val();
		var price = $('#price').val();
		
		if(clientOrganization == ""){
			document.getElementById("clientOrganization").className += " form-textboxerror";
			$('#clientOrganization').attr('placeholder', 'Client organization required');
			counter++;
		}
		if(loadingType == ""){
			document.getElementById("loadingType").className += " form-textboxerror";
			$('#loadingType').attr('placeholder', 'Loading type required');
			counter++;			
		}
		if(originPlace == ""){
			document.getElementById("originPlace").className += " form-textboxerror";
			$('#originPlace').attr('placeholder', 'Place of origin required');
			counter++;			
		}
		if(destinationPlace == ""){
			document.getElementById("destinationPlace").className += " form-textboxerror";
			$('#destinationPlace').attr('placeholder', 'Place of destination required');
			counter++;			
		}
		if(loadingQuantity == ""){
			document.getElementById("loadingQuantity").className += " form-textboxerror";
			$('#loadingQuantity').attr('placeholder', 'Loading quantity required');
			counter++;			
		}
//		if(distance == ""){
//			document.getElementById("distance").className += " form-textboxerror";
//			$('#distance').attr('placeholder', 'Distance required');
//			counter++;			
//		}
//		if(price == ""){
//			document.getElementById("price").className += " form-textboxerror";
//			$('#price').attr('placeholder', 'Price required');
//			counter++;
//		}
		
		if(counter == 0){
			
			$("#foDetailInfo").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/foDetail/saveFrightOrderDetailInformation.html",				
				data : "fo_id=" + fo_id + "&client_organization=" + clientOrganization + "&loading_type=" + loadingType + "&origin_place=" + originPlace + "&destination_place=" + destinationPlace + "&loading_quantity=" + loadingQuantity + "&distance=" + distance + "&price=" + price + "&initial_km=" + initialKm,
				success : function(response) {
					$('#foDetailInfo').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		
	}
	
	function updateFrightOrderDetailInformation(src, fotd_id){
		
		var counter = 0;
		
		var clientOrganization = $('#clientOrganization').val();
		var loadingType = $('#loadingType').val();
		var originPlace = $('#originPlace').val();
		var destinationPlace = $('#destinationPlace').val();
		var initialKm = $('#initialKm').val();
		var loadingQuantity = $('#loadingQuantity').val();
		var distance = $('#distance').val();
		var price = $('#price').val();
		
		if(clientOrganization == ""){
			document.getElementById("clientOrganization").className += " form-textboxerror";
			$('#clientOrganization').attr('placeholder', 'Client organization required');
			counter++;
		}
		if(loadingType == ""){
			document.getElementById("loadingType").className += " form-textboxerror";
			$('#loadingType').attr('placeholder', 'Loading type required');
			counter++;			
		}
		if(originPlace == ""){
			document.getElementById("originPlace").className += " form-textboxerror";
			$('#originPlace').attr('placeholder', 'Place of origin required');
			counter++;			
		}
		if(destinationPlace == ""){
			document.getElementById("destinationPlace").className += " form-textboxerror";
			$('#destinationPlace').attr('placeholder', 'Place of destination required');
			counter++;			
		}
		if(loadingQuantity == ""){
			document.getElementById("loadingQuantity").className += " form-textboxerror";
			$('#loadingQuantity').attr('placeholder', 'Loading quantity required');
			counter++;			
		}
//		if(distance == ""){
//			document.getElementById("distance").className += " form-textboxerror";
//			$('#distance').attr('placeholder', 'Distance required');
//			counter++;			
//		}
//		if(price == ""){
//			document.getElementById("price").className += " form-textboxerror";
//			$('#price').attr('placeholder', 'Price required');
//			counter++;
//		}
		
		if(counter == 0){
			
			$("#frightOrderTripDetailInfoUpdate").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/foDetail/updateFrightOrderDetailInformation.html",				
				data : "fotd_id=" + fotd_id + "&client_organization=" + clientOrganization + "&loading_type=" + loadingType + "&origin_place=" + originPlace + "&destination_place=" + destinationPlace + "&initial_km=" + initialKm + "&loading_quantity=" + loadingQuantity + "&distance=" + distance + "&price=" + price,
				success : function(response) {
					$('#frightOrderTripDetailInfoUpdate').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
		
	function insertFrightOrderAdvancePayment(src, fo_id){
		
		var advPay = $('#advPay').val();
		
		if(advPay == ""){
			
			document.getElementById("advPay").className += " form-textboxerror";
			$('#advPay').attr('placeholder', 'Advance payment amount required');
			
		} else {
			
			$("#foAdvancePayment").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/advancePayment/insertFrightOrderAdvancePayment.html",				
				data : "advance_payment_amount=" + advPay + "&fo_id=" + fo_id,
				success : function(response) {
					$('#foAdvancePayment').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function updateFrightOrderAdvancePayment(src, ap_id, fo_id){
		
		var advPay = $('#advPay').val();
		
		if(advPay == ""){
			
			document.getElementById("advPay").className += " form-textboxerror";
			$('#advPay').attr('placeholder', 'Advance payment amount required');
			
		} else {
			
			$("#advancePaymentInfoUpdate").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/advancePayment/updateFrightOrderAdvancePayment.html",
				data : "advance_payment_amount=" + advPay + "&ap_id=" + ap_id + "&fo_id=" + fo_id,
				success : function(response) {
					$('#advancePaymentInfoUpdate').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getAddCouponForm(src, fo_id, total_coupon_amount){
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getAddCouponForm.html",				
			data : "fo_id=" + fo_id + "&total_coupon_amount=" + total_coupon_amount,
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addRequiredCoupon(src){
		
		var counter = 0;

		var coupon_type = $('#coupon_type').val();
		var coupon_quantity = $('#coupon_quantity').val();
		
		if(coupon_type == ""){
			document.getElementById("coupon_type").className += " form-textboxerror";
			$('#coupon_type').attr('placeholder', 'Coupon type required');
			counter++;
		}
		if(coupon_quantity == ""){
			document.getElementById("coupon_quantity").className += " form-textboxerror";
			$('#coupon_quantity').attr('placeholder', 'Coupon quantity required');
			counter++;
		}
		if(counter == 0){
			
			$("#foCouponListForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/couponReg/getCouponTypeList.html",				
				data : "amount=" + coupon_quantity + "&no_of_coupon=" + coupon_quantity,
				success : function(response) {
					$('#foCouponListForm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

	function getFrightOrderInformationUpdateForm(src, fo_id){
		
		$("#frightOrderInfoUpdate").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderInformationUpdateForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#frightOrderInfoUpdate').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getFrightOrderTripDetailInfoUpdateForm(src, fotd_id){
		
		$("#frightOrderTripDetailInfoUpdate").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderTripDetailInfoUpdateForm.html",				
			data : "frightOrderTripDetail.fotd_id=" + fotd_id,
			success : function(response) {
				$('#frightOrderTripDetailInfoUpdate').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getFrightOrderAdvacnePaymentInfoUpdateForm(src, fo_id){
		
		$("#advancePaymentInfoUpdate").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderAdvacnePaymentInfoUpdateForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#advancePaymentInfoUpdate').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getFOAdvacnePaymentInfoUpdateForm(src, fo_id, ap_id){
		
		$("#advancePaymentInfoUpdate").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/getFOAdvacnePaymentInfoUpdateForm.html",				
			data : "fo_id=" + fo_id + "&ap_id=" + ap_id,
			success : function(response) {
				$('#advancePaymentInfoUpdate').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	
	function assignCoupon(src, fo_id){
		
		var coupon_status = $('#').val();
		
		$("#foCouponListForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/assignCoupon.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#foCouponListForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	var coupon_placement_status = "";
	
	function selectedCouponPlacementStatus(value){
		
		coupon_placement_status = value;
	}
	
	function assignNotGivenCoupon(src, fo_id, total_coupon_amount){
		
		if(coupon_placement_status == ""){
			
			document.getElementById("couponStatus").className += " form-textboxerror";
			//$('#couponStatus').attr('placeholder', 'Dispatch document reference number required');
			
		} else {
			
			document.getElementById("couponStatus").className = "form-group";
			if(coupon_placement_status == "-"){
				coupon_placement_status = "";
			}
		
			$("#foCouponListForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/assignNotGivenCoupon.html",				
				data : "fo_id=" + fo_id + "&couponDissemination.c_onhand=" + coupon_placement_status + "&total_coupon_amount=" + total_coupon_amount,
				success : function(response) {
					$('#foCouponListForm').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function editFrightOrderInfo(src, fo_id){
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderInformationUpdateForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editFrightOrderDetailInfo(src, fo_id){
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderTripDetailSecureUpdateForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editFrightOrderClosingInfo(src, fo_id){
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/foDetail/getFrightOrderClosingSecureUpdateForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFrightOrderCloseInfo(fo_id, src){
		
		var counter = 0;
		
		var dispatchDocRefNum = $('#dispatchDocRefNum').val();
		var deliveryDocRefNum = $('#deliveryDocRefNum').val();
		var deliveredQuantity = $('#deliveredQuantity').val();
		var deliveryDate = $('#deliveryDate').val();
		var remark = $('#remark').val();
		
		if(dispatchDocRefNum == ""){
			document.getElementById("dispatchDocRefNum").className += " form-textboxerror";
			$('#dispatchDocRefNum').attr('placeholder', 'Dispatch document reference number required');
			counter++;
		}
		if(deliveryDocRefNum == ""){
			document.getElementById("deliveryDocRefNum").className += " form-textboxerror";
			$('#deliveryDocRefNum').attr('placeholder', 'Delivery document reference number required');
			counter++;
		}
		if(deliveredQuantity == ""){
			document.getElementById("deliveredQuantity").className += " form-textboxerror";
			$('#deliveredQuantity').attr('placeholder', 'Delivered quantity required');
			counter++;
		}
		if(deliveryDate == ""){
			document.getElementById("deliveryDate").className += " form-textboxerror";
			$('#deliveryDate').attr('placeholder', 'Delivery date required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/updateClosedFrightOrder.html",				
				data : "fo_id=" + fo_id + "&dispatch_doc_ref_no=" + dispatchDocRefNum + "&delivery_doc_ref_no=" + deliveryDocRefNum + "&delivered_quantity=" + deliveredQuantity + "&delivery_date=" + deliveryDate + "&fright_note=" + remark,
				success : function(response) {
					$('#securedDataContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}	

	function getCouponDissiminationPrintForm(src, fo_id){
		
		$("#couponinfocontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getCouponDissiminationPrintForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#couponinfocontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editFrightOrderAdvancePaymentInfo(src, fo_id){
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/editFrightOrderAdvancePaymentInfo.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFrightOrderAdvancePaymentSecurePage(src, ap_id, fo_id){
		
		var advPay = $('#advPay').val();
		
		if(advPay == ""){
			
			document.getElementById("advPay").className += " form-textboxerror";
			$('#advPay').attr('placeholder', 'Advance payment amount required');
			
		} else {
			
			$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/advancePayment/updateFrightOrderAdvancePaymentSecurePage.html",
				data : "advance_payment_amount=" + advPay + "&ap_id=" + ap_id + "&fo_id=" + fo_id,
				success : function(response) {
					$('#securedDataContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	