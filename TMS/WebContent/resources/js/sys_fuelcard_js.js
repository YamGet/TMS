/**
 * 
 */

	function saveFuelCard(src){
		
		var counter = 0;
		
		var fc_no = $('#fc_no').val();
		var fc_company = $('#fc_company').val();
		
		if(fc_no == ""){
			
			document.getElementById("fc_no").className += " form-textboxerror";
			$('#fc_no').attr('placeholder', 'Fuel card number required');
			counter++;
		}
		if(fc_company == ""){
			
			document.getElementById("fc_company").className += " form-textboxerror";
			$('#fc_company').attr('placeholder', 'Oil company name required');
			counter++;
		}
		if(counter == 0){
			
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/fuelcard/saveFuelCard.html",				
				data : "fc_no=" + fc_no + "&fc_company=" + fc_company,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getFuelCardUpdateForm(src, fc_id, fc_no, fc_company, fc_status){
		
		$("#fuelCardForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/fuelcard/getFuelCardUpdateForm.html",				
			data : "fc_id=" + fc_id + "&fc_no=" + fc_no + "&fc_company=" + fc_company + "&fc_status=" + fc_status,
			success : function(response) {
				$('#fuelCardForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFuelCard(src, fc_id){
		
		var counter = 0;
		
		var fc_no = $('#fc_no').val();
		var fc_company = $('#fc_company').val();
		var fc_status = $('#fc_status').val();
		
		if(fc_no == ""){
			
			document.getElementById("fc_no").className += " form-textboxerror";
			$('#fc_no').attr('placeholder', 'Fuel card number required');
			counter++;
		}
		if(fc_company == ""){
			
			document.getElementById("fc_company").className += " form-textboxerror";
			$('#fc_company').attr('placeholder', 'Oil company name required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/fuelcard/updateFuelCard.html",				
				data : "fc_id=" + fc_id + "&fc_no=" + fc_no + "&fc_company=" + fc_company + "&fc_status=" + fc_status,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}