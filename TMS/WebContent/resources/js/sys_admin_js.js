/**
 * 
 */

	/**
	 * Associations
	 */
	function getAssociationTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/associations/getAssociationInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getAssociationsUpdateForm(src, a_id, association_name, association_status){
		
		$("#associationForms").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/associations/getAssociationUpdateForm.html",				
			data : "a_id=" + a_id + "&association_name=" + association_name + "&association_status=" + association_status,
			success : function(response) {
				$('#associationForms').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateAssociation(src, a_id){
		
		var association_name = $('#association_name').val();
		var association_status = $('#association_status').val();
		
		if(association_name == ""){
			
			document.getElementById("association_name").className += " form-textboxerror";
			$('#association_name').attr('placeholder', 'Association name required');
			
		} else {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/associations/updateAssociationInformation.html",				
				data : "a_id=" + a_id + "&association_name=" + association_name + "&association_status=" + association_status,
				success : function(response) {
					
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function saveAssociation(src){
		
		var association_name = $('#association_name').val();
		
		if(association_name == ""){
			
			document.getElementById("association_name").className += " form-textboxerror";
			$('#association_name').attr('placeholder', 'Association name required');
			
		} else {			
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/associations/saveAssociationInformation.html",				
				data : "association_name=" + association_name,
				success : function(response) {
					
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	
	/**
	 * Drivers
	 * @param src
	 */
	function getDriversTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/drivers/getDriversInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function driversInformationUpdateForm(src, dr_id, fname, gname, mname, driving_license_no, local_phone, abroad_phone, dr_status){
		
		$("#driversForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/drivers/getDriversInformationUpdateForm.html",				
			data : "dr_id=" + dr_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&driving_license_no=" + driving_license_no + "&local_phone=" + local_phone + "&abroad_phone=" + abroad_phone + "&dr_status=" + dr_status,
			success : function(response) {
				$('#driversForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveDriversInformation(src){
		
		var counter = 0;
		
		var fname = $('#fname').val();
		var mname = $('#mname').val();
		var gname = $('#gname').val();
		var driving_license_no = $('#driving_license_no').val();
		var local_phone = $('#local_phone').val();
		var abroad_phone = $('#abroad_phone').val();
		
		if(fname == ""){
			document.getElementById("fname").className += " form-textboxerror";
			$('#fname').attr('placeholder', 'First name required');
			counter++;
		} 
		if(mname == ""){
			document.getElementById("mname").className += " form-textboxerror";
			$('#mname').attr('placeholder', 'Father name required');
			counter++;
		}
		if(gname == ""){
			document.getElementById("gname").className += " form-textboxerror";
			$('#gname').attr('placeholder', 'Grand father name required');
			counter++;
		}
		if(driving_license_no == ""){
			document.getElementById("driving_license_no").className += " form-textboxerror";
			$('#driving_license_no').attr('placeholder', 'Driving license number required');
			counter++;
		}
		if(local_phone == ""){
			document.getElementById("local_phone").className += " form-textboxerror";
			$('#local_phone').attr('placeholder', 'Local phone number required');
			counter++;
		}
		if(counter == 0) {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "POST",			
				url : "/TMS/drivers/saveDriversInformation.html",				
				data : "fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&driving_license_no=" + driving_license_no + "&local_phone=" + local_phone + "&abroad_phone=" + abroad_phone,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
		
	function updateDriversInformation(src, dr_id){
		
		var counter = 0;
		
		var fname = $('#fname').val();
		var mname = $('#mname').val();
		var gname = $('#gname').val();
		var driving_license_no = $('#driving_license_no').val();
		var local_phone = $('#local_phone').val();
		var abroad_phone = $('#abroad_phone').val();
		var dr_status = $('#dr_status').val();
		
		if(fname == ""){
			document.getElementById("fname").className += " form-textboxerror";
			$('#fname').attr('placeholder', 'First name required');
			counter++;
		} 
		if(mname == ""){
			document.getElementById("mname").className += " form-textboxerror";
			$('#mname').attr('placeholder', 'Father name required');
			counter++;
		}
		if(gname == ""){
			document.getElementById("gname").className += " form-textboxerror";
			$('#gname').attr('placeholder', 'Grand father name required');
			counter++;
		}
		if(driving_license_no == ""){
			document.getElementById("driving_license_no").className += " form-textboxerror";
			$('#driving_license_no').attr('placeholder', 'Driving license number required');
			counter++;
		}
		if(local_phone == ""){
			document.getElementById("local_phone").className += " form-textboxerror";
			$('#local_phone').attr('placeholder', 'Local phone number required');
			counter++;
		}
		if(counter == 0) {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "POST",			
				url : "/TMS/drivers/updateDriversInformation.html",				
				data : "dr_id=" + dr_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&driving_license_no=" + driving_license_no + "&local_phone=" + local_phone + "&abroad_phone=" + abroad_phone + "&dr_status=" + dr_status,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}

	/**
	 * Trucks
	 * @param src
	 */
	function getTruckTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/trucks/getTrucksInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function truckInformationUpdateForm(src, tri_id, shanci_no, truck_plate_no, side_no, truck_model, loading_capacity, truck_owner, truck_type, truck_status){
		
		$("#truckForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/trucks/getTrucksInformationUpdateForm.html",				
			data : "tri_id=" + tri_id + "&shanci_no=" + shanci_no + "&truck_plate_no=" + truck_plate_no + "&side_no=" + side_no + "&truck_model=" + truck_model + "&loading_capacity=" + loading_capacity + "&truck_owner=" + truck_owner + "&truck_type=" + truck_type + "&truck_status=" + truck_status,
			success : function(response) {
				$('#truckForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveTruckInformation(src){
		
		var counter = 0;
		
		var shanci_no = $('#shanci_no').val();
		var truck_plate_no = $('#truck_plate_no').val();
		var side_no = $('#side_no').val();
		var truck_model = $('#truck_model').val();
		var loading_capacity = $('#loading_capacity').val();
		var truck_owner = $('#truck_owner').val();
		var truck_type = $('#truck_type').val();
		
		if(shanci_no == ""){
			document.getElementById("shanci_no").className += " form-textboxerror";
			$('#shanci_no').attr('placeholder', 'Shanci number required');
			counter++;
		}
		if(truck_plate_no == ""){
			document.getElementById("truck_plate_no").className += " form-textboxerror";
			$('#truck_plate_no').attr('placeholder', 'Truck plate number required');
			counter++;
		}
//		if(side_no == ""){
//			document.getElementById("side_no").className += " form-textboxerror";
//			$('#side_no').attr('placeholder', 'Truck Side number required');
//			counter++;
//		}
		if(truck_model == ""){
			document.getElementById("truck_model").className += " form-textboxerror";
			$('#truck_model').attr('placeholder', 'Truck model required');
			counter++;
		}
		if(loading_capacity == ""){
			document.getElementById("loading_capacity").className += " form-textboxerror";
			$('#loading_capacity').attr('placeholder', 'Loading capacity required');
			counter++;
		}
		if(truck_owner == ""){
			document.getElementById("truck_owner").className += " form-textboxerror";
			$('#truck_owner').attr('placeholder', 'Truck owner required');
			counter++;
		}
		if(truck_type == "-"){
			document.getElementById("truck_type").className += " form-textboxerror";
			$('#truck_type').attr('placeholder', 'Truck owner required');
			counter++;
		}
		
		if(counter == 0){
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/trucks/saveTruckInformation.html",				
				data : "shanci_no=" + shanci_no + "&truck_plate_no=" + truck_plate_no + "&side_no=" + side_no + "&truck_model=" + truck_model + "&loading_capacity=" + loading_capacity + "&truck_owner=" + truck_owner + "&truck_type=" + truck_type,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function updateTruckInformation(src, tri_id){
		
		var counter = 0;
		
		var shanci_no = $('#shanci_no').val();
		var truck_plate_no = $('#truck_plate_no').val();
		var side_no = $('#side_no').val();
		var truck_model = $('#truck_model').val();
		var loading_capacity = $('#loading_capacity').val();
		var truck_owner = $('#truck_owner').val();
		var truck_type = $('#truck_type').val();
		var truck_status = $('#truck_status').val();
		
		if(shanci_no == ""){
			document.getElementById("shanci_no").className += " form-textboxerror";
			$('#shanci_no').attr('placeholder', 'Shanci number required');
			counter++;
		}
		if(truck_plate_no == ""){
			document.getElementById("truck_plate_no").className += " form-textboxerror";
			$('#truck_plate_no').attr('placeholder', 'Truck plate number required');
			counter++;
		}
//		if(side_no == ""){
//			document.getElementById("side_no").className += " form-textboxerror";
//			$('#side_no').attr('placeholder', 'Truck Side number required');
//			counter++;
//		}
		if(truck_model == ""){
			document.getElementById("truck_model").className += " form-textboxerror";
			$('#truck_model').attr('placeholder', 'Truck model required');
			counter++;
		}
		if(loading_capacity == ""){
			document.getElementById("loading_capacity").className += " form-textboxerror";
			$('#loading_capacity').attr('placeholder', 'Loading capacity required');
			counter++;
		}
		if(truck_owner == ""){
			document.getElementById("truck_owner").className += " form-textboxerror";
			$('#truck_owner').attr('placeholder', 'Truck owner required');
			counter++;
		}
		
		if(counter == 0){
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/trucks/updateTruckInformation.html",				
				data : "tri_id=" + tri_id + "&shanci_no=" + shanci_no + "&truck_plate_no=" + truck_plate_no + "&side_no=" + side_no + "&truck_model=" + truck_model + "&loading_capacity=" + loading_capacity + "&truck_owner=" + truck_owner + "&truck_status=" + truck_status + "&truck_type=" + truck_type,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/**
	 * Trail
	 * @param src
	 */
	function getTrailsTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/trails/getTrailsInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function trailsUpdateForm(src, tli_id, trail_plate_no, loading_capacity, trail_owner, trail_type, trail_status){
		
		$("#trailsForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/trails/getTrailsInformationUpdateForm.html",				
			data : "tli_id=" + tli_id + "&trail_plate_no=" + trail_plate_no + "&loading_capacity=" + loading_capacity + "&trail_owner=" + trail_owner + "&trail_status=" + trail_status + "&trail_type=" + trail_type,
			success : function(response) {
				$('#trailsForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveTrailInformation(src){
		
		var counter = 0;
		
		var trail_plate_no = $('#trail_plate_no').val();
		var loading_capacity = $('#loading_capacity').val();
		var trail_owner = $('#trail_owner').val();
		var trail_type = $('#trail_type').val();
		
		if(trail_plate_no == ""){
			document.getElementById("trail_plate_no").className += " form-textboxerror";
			$('#trail_plate_no').attr('placeholder', 'Trail plate number required');
			counter++;
		}
		if(loading_capacity == ""){
			document.getElementById("loading_capacity").className += " form-textboxerror";
			$('#loading_capacity').attr('placeholder', 'Loading capacity required');
			counter++;
		}
		if(trail_owner == ""){
			document.getElementById("trail_owner").className += " form-textboxerror";
			$('#trail_owner').attr('placeholder', 'Trail owner required');
			counter++;
		}
		if(trail_type == "-"){
			document.getElementById("trail_type").className += " form-textboxerror";
			$('#trail_type').attr('placeholder', 'Trail type required');
			counter++;
		}
		
		if(counter == 0){
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/trails/saveTrailInformation.html",				
				data : "trail_plate_no=" + trail_plate_no + "&loading_capacity=" + loading_capacity + "&trail_owner=" + trail_owner + "&trail_type=" + trail_type,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}		
	}
	
	function updateTrailInformaiton(src, tli_id){
		
		var counter = 0;
		
		var trail_plate_no = $('#trail_plate_no').val();
		var loading_capacity = $('#loading_capacity').val();
		var trail_owner = $('#trail_owner').val();
		var trail_type = $('#trail_type').val();
		var trail_status = $('#trail_status').val();
		
		if(trail_plate_no == ""){
			document.getElementById("trail_plate_no").className += " form-textboxerror";
			$('#trail_plate_no').attr('placeholder', 'Trail plate number required');
			counter++;
		}
		if(loading_capacity == ""){
			document.getElementById("loading_capacity").className += " form-textboxerror";
			$('#loading_capacity').attr('placeholder', 'Loading capacity required');
			counter++;
		}
		if(trail_owner == ""){
			document.getElementById("trail_owner").className += " form-textboxerror";
			$('#trail_owner').attr('placeholder', 'Trail owner required');
			counter++;
		}
		
		if(counter == 0){
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/trails/updateTrailInformation.html",				
				data : "tli_id=" + tli_id + "&trail_plate_no=" + trail_plate_no + "&loading_capacity=" + loading_capacity + "&trail_owner=" + trail_owner + "&trail_status=" + trail_status + "&trail_type=" + trail_type,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}		
	}
	
	
	
	/**
	 * Expense Type
	 * @param src
	 */
	
	function getExpenseTypeTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/expenseType/getExpenseTypeInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveExpenseType(src){
		
		var counter = 0;
		
		var expense_type_name = $('#expense_type_name').val();
		var account_number = $('#account_number').val();
		var expense_type_usage = $('#expense_type_usage').val();
		
		if(expense_type_name == ""){
			
			document.getElementById("expense_type_name").className += " form-textboxerror";
			$('#expense_type_name').attr('placeholder', 'Expense type name required');
			counter++;
		} 
		if(account_number == ""){
			
			document.getElementById("account_number").className += " form-textboxerror";
			$('#account_number').attr('placeholder', 'Account number required');
			counter++;
		}
		
		if(counter == 0){
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expenseType/saveExpenseTypeInformation.html",				
				data : "expense_type_name=" + expense_type_name + "&account_number=" + account_number + "&expense_type_usage=" + expense_type_usage,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getExpenseTypeUpdateForm(src, et_id, expense_type_name, account_number, expense_type_usage, expense_type_status){
		
		$("#expenseTypeForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/expenseType/getExpenseTypeInformationUpdateForm.html",				
			data : "et_id=" + et_id + "&expense_type_name=" + expense_type_name + "&account_number=" + account_number + "&expense_type_usage=" + expense_type_usage  + "&expense_type_status=" + expense_type_status,
			success : function(response) {
				$('#expenseTypeForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateExpenseType(src, et_id){
		
		var counter = 0;
		
		var expense_type_name = $('#expense_type_name').val();
		var account_number = $('#account_number').val();
		var expense_type_status = $('#expense_type_status').val();
		var expense_type_usage = $('#expense_type_usage').val();
		
		if(expense_type_name == ""){
			
			document.getElementById("expense_type_name").className += " form-textboxerror";
			$('#expense_type_name').attr('placeholder', 'Expense type name required');
			counter++;
		} 
		if(account_number == ""){
			
			document.getElementById("account_number").className += " form-textboxerror";
			$('#account_number').attr('placeholder', 'Account number required');
			counter++;
		}
		if(counter == 0){
			
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expenseType/updateExpenseTypeInformation.html",				
				data : "et_id=" + et_id + "&expense_type_name=" + expense_type_name + "&account_number=" + account_number + "&expense_type_status=" + expense_type_status + "&expense_type_usage=" + expense_type_usage,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	/**
	 * Fuel Company
	 * @param src
	 */
	function getFuelCompnayTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/fuelcompany/getFuelCompanyTemplate.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveFuelCompany(src){
		
		var company_name = $('#company_name').val();
		var contact_person_name = $('#contact_person_name').val();
		var contact_person_phone = $('#contact_person_phone').val();
		
		if(company_name == ""){
			
			document.getElementById("company_name").className += " form-textboxerror";
			$('#company_name').attr('placeholder', 'Company name required');
			counter++;
			
		} else {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/fuelcompany/saveFuelCompany.html",				
				data : "company_name=" + company_name + "&contact_person_name=" + contact_person_name + "&contact_person_phone=" + contact_person_phone,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getFuelCompanyUpdateForm(src, fc_id, company_name, contact_person_name, contact_person_phone, fc_status){
		
		$("#fuelCompanyForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/fuelcompany/getFuelCompanyUpdateForm.html",				
			data : "fc_id=" + fc_id + "&company_name=" + company_name + "&contact_person_name=" + contact_person_name + "&contact_person_phone=" + contact_person_phone + "&fc_status=" + fc_status,
			success : function(response) {
				$('#fuelCompanyForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFuelCompany(src, fc_id){
		
		var company_name = $('#company_name').val();
		var contact_person_name = $('#contact_person_name').val();
		var contact_person_phone = $('#contact_person_phone').val();
		var fc_status = $('#fc_status').val();
		
		if(company_name == ""){
			
			document.getElementById("company_name").className += " form-textboxerror";
			$('#company_name').attr('placeholder', 'Company name required');
			counter++;
			
		} else {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/fuelcompany/updateFuelCompany.html",				
				data : "fc_id=" + fc_id + "&company_name=" + company_name + "&contact_person_name=" + contact_person_name + "&contact_person_phone=" + contact_person_phone + "&fc_status=" + fc_status,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
		
	/**
	 * Fuel Card
	 * @param src
	 */
	function getFuelCardTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/fuelcard/getFuelCardInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	/**
	 * Users
	 * @param src
	 */
	function getUsersTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/user/getUsersInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveNewUser(src){
		
		var counter = 0;
		
		var fname = $('#fname').val();
		var mname = $('#mname').val();
		var gname = $('#gname').val();
		var username = $('#username').val();
		var password = $('#password').val();
		var ur_id = $('#ur_id').val();
		
		if(fname == ""){
			
			document.getElementById("fname").className += " form-textboxerror";
			$('#fname').attr('placeholder', 'First name required');
			counter++;
		}
		if(mname == ""){
			
			document.getElementById("mname").className += " form-textboxerror";
			$('#mname').attr('placeholder', 'Middle name required');
			counter++;
		}
		if(gname == ""){
			
			document.getElementById("gname").className += " form-textboxerror";
			$('#gname').attr('placeholder', 'Grand father name required');
			counter++;
		}		
		if(username == ""){
			
			document.getElementById("username").className += " form-textboxerror";
			$('#username').attr('placeholder', 'Association required');
			counter++;
		}
		if(password == ""){
			
			document.getElementById("password").className += " form-textboxerror";
			$('#password').attr('placeholder', 'Association required');
			counter++;
		}
		if(ur_id == "-"){
			
			document.getElementById("ur_id").className += " form-textboxerror";
			$('#ur_id').attr('placeholder', 'Association required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/user/saveUsersInformation.html",				
				data : "fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&user_name=" + username + "&password=" + password + "&ur_id=" + ur_id,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getUserInfoUpdateForm(src, user_id){
		
		$("#usersForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/user/getUsersInformationUpdateForm.html",				
			data : "user_id=" + user_id,
			success : function(response) {
				$('#usersForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateUserInfo(src, user_id){
		
		var counter = 0;
		
		var fname = $('#fname').val();
		var mname = $('#mname').val();
		var gname = $('#gname').val();
		var username = $('#username').val();
		var password = $('#password').val();
		var ur_id = $('#ur_id').val();
		var user_status = $('#user_status').val();
		
		if(fname == ""){
			
			document.getElementById("fname").className += " form-textboxerror";
			$('#fname').attr('placeholder', 'First name required');
			counter++;
		}
		if(mname == ""){
			
			document.getElementById("mname").className += " form-textboxerror";
			$('#mname').attr('placeholder', 'Middle name required');
			counter++;
		}
		if(gname == ""){
			
			document.getElementById("gname").className += " form-textboxerror";
			$('#gname').attr('placeholder', 'Grand father name required');
			counter++;
		}
		if(username == ""){
			
			document.getElementById("username").className += " form-textboxerror";
			$('#username').attr('placeholder', 'Association required');
			counter++;
		}
		if(password == ""){
			
			document.getElementById("password").className += " form-textboxerror";
			$('#password').attr('placeholder', 'Association required');
			counter++;
		}
		if(ur_id == "-"){
			
			document.getElementById("ur_id").className += " form-textboxerror";
			$('#ur_id').attr('placeholder', 'Association required');
			counter++;
		}
		
		if(counter == 0){
			
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/user/updateUsersInformation.html",				
				data : "user_id=" + user_id + "&fname=" + fname + "&mname=" + mname + "&gname=" + gname + "&user_name=" + username + "&password=" + password + "&ur_id=" + ur_id + "&user_status=" + user_status,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	
	/**
	 * Users Role
	 * @param src
	 */
	function getUsersRoleTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersRole/getUsersRoleInformation.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveUsersRole(src){
		
		var userrole_name = $('#userrole_name').val();
		
		if(userrole_name == ""){
			
			document.getElementById("userrole_name").className += " form-textboxerror";
			$('#userrole_name').attr('placeholder', 'Expense type name required');
			
		} else {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/usersRole/saveUsersRoleInformation.html",				
				data : "userrole_name=" + userrole_name,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		
		}
	}
	
	function usersRoleUpdateForm(src, ur_id, userrole_name, userrole_status){
		
		$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersRole/getUsersRoleUpdateForm.html",				
			data : "ur_id=" + ur_id + "&userrole_name=" + userrole_name + "&userrole_status=" + userrole_status,
			success : function(response) {
				$('#usersRoleForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateUsersRole(src, ur_id){
		
		var userrole_name = $('#userrole_name').val();
		var userrole_status = $('#userrole_status').val();
		
		if(userrole_name == ""){
			
			document.getElementById("userrole_name").className += " form-textboxerror";
			$('#userrole_name').attr('placeholder', 'Expense type name required');
			
		} else {
		
			$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/usersRole/updateUsersRoleInformation.html",				
				data : "ur_id=" + ur_id + "&userrole_name=" + userrole_name + "&userrole_status=" + userrole_status,
				success : function(response) {
					$('#adminMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});		
		}
	}
	
	
	
	
	
	function addSystemModuleToRelate(m_id){
		
		//$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/userRoleModuleRel/addSystemModule.html",				
			data : "m_id=" + m_id,
			success : function(response) {				
				//$('#usersRoleForm').html(response);
			},
			error : function(e) {
				//alert('Error: ' + e);
			}
		});
	}
	
	function relateUsersRoleWithModule(src, ur_id, userrole_name){
		
		$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/userRoleModuleRel/relateUsersRoleWithModuleForm.html",				
			data : "ur_id=" + ur_id + "&usersRole.userrole_name=" + userrole_name,
			success : function(response) {
				$('#usersRoleForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveUsersRoleRelationWithModule(src, ur_id, userrole_name){
		
		$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/userRoleModuleRel/saveUsersRoleRelationWithModule.html",				
			data : "ur_id=" + ur_id + "&usersRole.userrole_name=" + userrole_name,
			success : function(response) {
				$('#usersRoleForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function unrelateUsersRoleWithModule(src, ur_id, userrole_name){
		
		$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/userRoleModuleRel/unrelateUsersRoleWithModuleForm.html",				
			data : "ur_id=" + ur_id + "&usersRole.userrole_name=" + userrole_name,
			success : function(response) {
				$('#usersRoleForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function removeSystemModuleFromRelation(m_id){
		
		//$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/userRoleModuleRel/addSystemModule.html",				
			data : "m_id=" + m_id,
			success : function(response) {				
				//$('#usersRoleForm').html(response);
			},
			error : function(e) {
				//alert('Error: ' + e);
			}
		});
	}
	
	function updateUsersRoleRelationWithModule(src, ur_id, userrole_name){
		
		$("#usersRoleForm").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/userRoleModuleRel/updateUsersRoleRelationWithModule.html",				
			data : "ur_id=" + ur_id + "&usersRole.userrole_name=" + userrole_name,
			success : function(response) {
				$('#usersRoleForm').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	
	/**
	 * Users Role
	 * @param src
	 */
	function getRolesAuthenticationTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/getUsersRoleAuthenticationTemplate.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getUsersRoleModuleList(src, value){
		
		$("#moduleList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/getUsersRoleModuleList.html",				
			data : "usersRoleModuleRelation.ur_id=" + value,
			success : function(response) {
				$('#moduleList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getSystemURLListPerModule(src, value){
		
		var ur_id = $('#ur_id').val();
		
		$("#moduleURLList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/getSystemURLListPerModule.html",				
			data : "usersRoleModuleRelation.m_id=" + value + "&usersRoleModuleRelation.ur_id=" + ur_id,
			success : function(response) {
				$('#moduleURLList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getRelatedURL(src, m_id, ur_id){
		
		document.getElementById("relatedURL").className = "active";
		document.getElementById("unrelatedURL").className = "";
		
		$("#roleAuthContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/getRelatedURL.html",				
			data : "usersRoleModuleRelation.m_id=" + m_id + "&usersRoleModuleRelation.ur_id=" + ur_id,
			success : function(response) {
				$('#roleAuthContent').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getUnrelatedURL(src, m_id, ur_id){
		
		document.getElementById("relatedURL").className = "";
		document.getElementById("unrelatedURL").className = "active";
		
		$("#roleAuthContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/getUnrelatedURL.html",				
			data : "usersRoleModuleRelation.m_id=" + m_id + "&usersRoleModuleRelation.ur_id=" + ur_id,
			success : function(response) {
				$('#roleAuthContent').html(response);
				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function relateURL(src, urmr_id, su_id, m_id, ur_id){
		
		document.getElementById("relatedURL").className = "";
		document.getElementById("unrelatedURL").className = "active";
		
		$("#roleAuthContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/relateURL.html",				
			data : "usersRoleModuleRelation.m_id=" + m_id + "&usersRoleModuleRelation.ur_id=" + ur_id + "&UsersRoleModuleRelation.urmr_id=" + urmr_id + "&su_id=" + su_id,
			success : function(response) {
				$('#roleAuthContent').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function unrelateURL(src, urmr_id, su_id, m_id, ur_id){
		
		document.getElementById("relatedURL").className = "active";
		document.getElementById("unrelatedURL").className = "";
		
		$("#roleAuthContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/usersAuth/unrelateURL.html",				
			data : "usersRoleModuleRelation.m_id=" + m_id + "&usersRoleModuleRelation.ur_id=" + ur_id + "&UsersRoleModuleRelation.urmr_id=" + urmr_id + "&su_id=" + su_id,
			success : function(response) {
				$('#roleAuthContent').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	/*
	 *Data base backup 
	 */
	function takeDatabaseBackup(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/admin/takeDatabaseBackup.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);				
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	/*
	 * getting secure data
	 */
	function getSecuredDataFromDBTemplate(src){
		
		$("#adminMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/admin/getSecuredDataFromDBTemplate.html",				
			data : "",
			success : function(response) {
				$('#adminMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getClosedFrightOrderList(src){
		
		document.getElementById("UnProcessFO").className = "active";
		document.getElementById("doclink").className = "";
		
		//document.getElementById("searchFoKey").value = "";
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getClosedFrightOrderList.html",				
			data : "",
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	function searchClosedFrightOrderFromList(src, value){
		
		$("#closedFOSearchRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/searchFromClosedFrightOrderList.html",				
			data : "search_value=" + value,
			success : function(response) {
				$('#closedFOSearchRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	
	function getDocumentDownloadLinkList(src){
		
		document.getElementById("UnProcessFO").className = "";
		document.getElementById("doclink").className = "active";
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/files/getDocumentDownloadLinkList.html",				
			data : "",
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}