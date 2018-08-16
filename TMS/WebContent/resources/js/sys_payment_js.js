/**
 * 
 */

	function searchFrightOrderFromList(src, value){
		
		var UnProcessFO = document.getElementById("UnProcessFO").className;
		var InProcessFO = document.getElementById("InProcessFO").className;
		
		if(UnProcessFO == "active"){
			
			$("#paymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/getFilteredNotProcessedFrightOrderList.html",				
				data : "search_value=" + value,
				success : function(response) {
					$('#paymentContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		if(InProcessFO == "active"){
			
			$("#paymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/getFilteredInprocessedFrightOrderList.html",				
				data : "search_value=" + value,
				success : function(response) {
					$('#paymentContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

	function getUnprocessFrightOrder(src){
		
		//document.getElementById("searchFoKey").value = "";
		document.getElementById("UnProcessFO").className = "active";
		document.getElementById("InProcessFO").className = "";
		
		$("#paymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/getNotProcessedFrightOrderList.html",				
			data : "",
			success : function(response) {
				$('#paymentContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function showDetailPaymentInformation(src){
		
		$("#notProcessFOContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/showDetailNotProcessedFrightOrderInformation.html",				
			data : "",
			success : function(response) {
				$('#notProcessFOContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getCommissionEditForm(src, fo_id){
		
		$("#notProcessFOContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/getCommissionEditForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#notProcessFOContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editFrightOrderCommission(src, fo_id){
		
		var foCommission = $('#foCommission').val();
		
		if(foCommission == ""){
			
			document.getElementById("foCommission").className += " form-textboxerror";
			$('#foCommission').attr('placeholder', 'Commission value required');
			
		} else {
		
			$("#notProcessFOContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/editFrightOrderCommission.html",				
				data : "fo_id=" + fo_id + "&commission=" + foCommission,
				success : function(response) {
					$('#notProcessFOContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function printPaymentRequestForm(src, fo_id, coupon_status){
		
		$("#notProcessFOContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/printPaymentRequestForm.html",				
			data : "fo_id=" + fo_id + "&coupon_status=" + coupon_status,
			success : function(response) {
				$('#notProcessFOContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	var couponstatus = "";
	
	function deductCouponValue(){
		
		if(couponstatus == ""){
		
			couponstatus = "yes";
		} else {
			
			couponstatus = "";
		}
	}
	
	function printPaymentInformation(src, fo_id){
		
		$("#notProcessFOContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/printPaymentRequestFormForNotProcessedFrightOrder.html",				
			data : "fo_id=" + fo_id + "&coupon_status=" + couponstatus,
			success : function(response) {
				$('#notProcessFOContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		couponstatus = "";
	}
	
	function getInprocessFrightOrder(src){
		
		//document.getElementById("searchFoKey").value = "";
		
		document.getElementById("UnProcessFO").className = "";
		document.getElementById("InProcessFO").className = "active";
		
		$("#paymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/getInProcessFrightOrderList.html",				
			data : "",
			success : function(response) {
				$('#paymentContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function frightOrderPaymentForm(src){
		
		$("#inProcessPaymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/getPaymentForInProcessFrightOrderForm.html",				
			data : "",
			success : function(response) {
				$('#inProcessPaymentContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}	
	
	function getPaymentAppointmentAddForm(src, fo_id){
		
		$("#inProcessPaymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/getPaymentAppointmentAddForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#inProcessPaymentContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function addPaymentAppointmentDate(src, fo_id){
		
		var appointment_date = $('#appointment_date').val();
		
		if(appointment_date == ""){
			
			document.getElementById("appointment_date").className += " form-textboxerror";
			$('#appointment_date').attr('placeholder', 'Appointment date required');
			
		} else {
			
			$("#inProcessPaymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/foDetail/savePaymentAppointmentDate.html",				
				data : "fo_id=" + fo_id + "&payment_appointment_date=" + appointment_date,
				success : function(response) {
					$('#inProcessPaymentContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	var coupon_status = "";
	var addFrightOrderCounter = 0;
	var totalSum = 0;
	var selectedfoid = new Array();
	
	function selectFrightOrder(fo_id, value, total_coupon_amount){
		
		var counter = 0;
		
		for(var i = 0; i < selectedfoid.length; i++){
			
			if(fo_id == selectedfoid[i]){
				value = value * -1;
				total_coupon_amount = total_coupon_amount * -1;
				selectedfoid.splice(i, 1);
				counter++;
			}
		}
		
		if(counter == 0){
			var size = selectedfoid.length;
			selectedfoid[size] = fo_id;
		}
		
		//to adjust the decimal place in javascript --- use num.toFixed(2)
		if(coupon_status == "deduct"){
			totalSum = (Number(totalSum) + Number(value) - Number(total_coupon_amount)).toFixed(2);
		} else {
			totalSum = (Number(totalSum) + Number(value)).toFixed(2);
		}
		
		document.getElementById("fonTotal").innerHTML = "FON Total Sum - " + totalSum;
		
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/addSelectedFrightOrderId.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				//$('#inProcessPaymentContent').html(response);
				addFrightOrderCounter++;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function couponDeductionStatus(value){
		
		if(totalSum == 0){
			coupon_status = value;
		}
	}
	
	var payment_type = "";
	
	function selectPaymentForm(value){
		
		payment_type = value;
	}
		
	function saveFrightOrderPayment(src){
		
		var counter = 0;
		
		var paymentDocRefNo = $('#paymentDocRefNo').val();
		var paymentAmount = $('#paymentAmount').val();
		var paymentDate = $('#payment_date').val();
		
		if(addFrightOrderCounter == 0){
			document.getElementById("foErrorMessage").innerHTML = "Fright order required.";
			counter++;
		}
		if(payment_type == ""){
			document.getElementById("pfErrorMessage").innerHTML = "Payment type required.";
			counter++;
		}		
//		if(paymentDocRefNo == ""){
//			
//			document.getElementById("paymentDocRefNo").className += " form-textboxerror";
//			$('#paymentDocRefNo').attr('placeholder', 'Payment document reference number required');
//			counter++;
//		}
		if(paymentAmount == ""){
			
			document.getElementById("paymentAmount").className += " form-textboxerror";
			$('#paymentAmount').attr('placeholder', 'Payment amount required');
			counter++;
		}
		if(paymentDate == ""){
			
			document.getElementById("payment_date").className += " form-textboxerror";
			$('#payment_date').attr('placeholder', 'Payment date required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#inProcessPaymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/saveFrightOrderPayment.html",				
				data : "payment_type=" + payment_type + "&payment_doc_ref_no=" + paymentDocRefNo + "&payment_amount=" + paymentAmount + "&payment_date=" + paymentDate + "&coupon_status=" + coupon_status,
				success : function(response) {
					$('#inProcessPaymentContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
		
	}
	
	function frightOrderPriceUpdateForm(src, fo_id){

		$("#priceEditForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/frightOrderPriceUpdateForm.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#priceEditForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFrightOrderPrice(src, fo_id){
		
		var counter = 0;
		var price = $('#price').val();
		var commission = $('#commission').val();
		var crv_number = $('#crv_number').val();
		
		if(price == ""){
			
			document.getElementById("price").className += " form-textboxerror";
			$('#price').attr('placeholder', 'Price required');
			counter++;
		}
		if(commission == ""){
			
			document.getElementById("commission").className += " form-textboxerror";
			$('#commission').attr('placeholder', 'Price required');
			counter++;
		}
		if(counter == 0) {
			
			$("#inProcessPaymentContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			
			$.ajax({
				type : "GET",
				async: false,
				url : "/TMS/frightOrder/editFrightOrderCommission.html",				
				data : "fo_id=" + fo_id + "&commission=" + commission,
				success : function(response) {
					//$('#notProcessFOContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
						
			$.ajax({
				type : "GET",
				async: false,
				url : "/TMS/foDetail/updateFrightOrderPrice.html",				
				data : "fo_id=" + fo_id + "&price=" + price + "&crv_number=" + crv_number,
				success : function(response) {
					$('#inProcessPaymentContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	