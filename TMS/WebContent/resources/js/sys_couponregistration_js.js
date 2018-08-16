/**
 * 
 */
	var insertOption = 0;
	function insertOptionSelected(value){
		insertOption = value;
		if(value == 1){
			document.getElementById("coupon_1").style.display = "block";
			document.getElementById("coupon_2").style.display = "none";
			document.getElementById("coupon_3").style.display = "none";
			document.getElementById("coupon_4").style.display = "none";
		} else {
			document.getElementById("coupon_1").style.display = "none";
			document.getElementById("coupon_2").style.display = "block";
			document.getElementById("coupon_3").style.display = "block";
			document.getElementById("coupon_4").style.display = "block";
		}
	}

	
	function registorCoupon(src){
		
		var counter = 0;
		
		var oil_company = $('#oil_company').val();
		var amount = $('#amount').val();
		var url = "";
		
		if(insertOption == 1){
			var c_serial_no = $('#c_serial_no').val();
			url = "oil_company=" + oil_company + "&amount=" + amount + "&c_serial_no=" + c_serial_no + "&form_type=single";
		}
		
		var no_of_coupon = "";
		var c_serial_no_from = "";
		var c_serial_no_to = "";
		
		if(insertOption == 2){
			no_of_coupon = $('#no_of_coupon').val();
			c_serial_no_from = $('#c_serial_no_from').val();
			c_serial_no_to = $('#c_serial_no_to').val();
			url = "oil_company=" + oil_company + "&amount=" + amount + "&no_of_coupon=" + no_of_coupon + "&c_serial_no_from=" + c_serial_no_from + "&c_serial_no_to=" + c_serial_no_to + "&form_type=multiple";
		}
		
		if(oil_company == "-"){
			
			document.getElementById("oil_company").className += " form-textboxerror";
			$('#oil_company').attr('placeholder', 'Oil company name required');
			counter++;
		}
		if(amount == ""){
			
			document.getElementById("amount").className += " form-textboxerror";
			$('#amount').attr('placeholder', 'Coupon amount name required');
			counter++;
		}
		
		if(insertOption == 1 && c_serial_no == ""){
			
			document.getElementById("c_serial_no").className += " form-textboxerror";
			$('#c_serial_no').attr('placeholder', 'Coupon serial number required');
			counter++;
		}
		
		if(insertOption == 2 && no_of_coupon == ""){
			
			document.getElementById("no_of_coupon").className += " form-textboxerror";
			$('#no_of_coupon').attr('placeholder', 'Number of coupon required');
			counter++;
		}
		if(insertOption == 2 && c_serial_no_from == ""){
			
			document.getElementById("c_serial_no_from").className += " form-textboxerror";
			$('#c_serial_no_from').attr('placeholder', 'Coupon serial number from required');
			counter++;
		}
		if(insertOption == 2 && c_serial_no_to == ""){
			
			document.getElementById("c_serial_no_to").className += " form-textboxerror";
			$('#c_serial_no_to').attr('placeholder', 'Coupon serial number to required');
			counter++;
		}
		
		if(insertOption == 2 && c_serial_no_from != "" && c_serial_no_to != "" && Number(c_serial_no_to) - Number(c_serial_no_from) != Number(no_of_coupon) - 1){
			
			document.getElementById("no_of_coupon").className += " form-textboxerror";
			document.getElementById("c_serial_no_from").className += " form-textboxerror";
			document.getElementById("c_serial_no_to").className += " form-textboxerror";
			$('#no_of_coupon').attr('placeholder', 'Insert correct serial number from and to.');
			counter++;
		}
		
		
		if(counter == 0){
		
			$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/couponReg/saveCoupon.html",				
				data : url,
				success : function(response) {
					$('#bodycontent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getCouponUpdateForm(src, c_id, oil_company, amount, c_serial_no){
		
		$("#couponForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/couponReg/getCouponUpdateForm.html",				
			data : "c_id=" + c_id + "&oil_company=" + oil_company + "&amount=" + amount + "&c_serial_no=" + c_serial_no,
			success : function(response) {
				$('#couponForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateCoupon(src, c_id){
		
		var counter = 0;
		
		var oil_company = $('#oil_company').val();
		var amount = $('#amount').val();
		var c_serial_no = $('#c_serial_no').val();
		
		if(oil_company == "-"){
			
			document.getElementById("oil_company").className += " form-textboxerror";
			$('#oil_company').attr('placeholder', 'Oil company name required');
			counter++;
		}
		if(amount == ""){
			
			document.getElementById("amount").className += " form-textboxerror";
			$('#amount').attr('placeholder', 'Coupon amount name required');
			counter++;
		}
		
		if(c_serial_no == ""){
			
			document.getElementById("c_serial_no").className += " form-textboxerror";
			$('#c_serial_no').attr('placeholder', 'Coupon serial number required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/couponReg/updateCoupon.html",				
				data : "c_id=" + c_id + "&oil_company=" + oil_company + "&amount=" + amount + "&c_serial_no=" + c_serial_no,
				success : function(response) {
					$('#bodycontent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}