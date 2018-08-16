/**
 * 
 */

	function getRevenueExpensePerTruck(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/report/reportFilterFormPerTruck.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	var truck_report_type = "";
	
	function selectExpenseTypeUsagePerTruck(value){
		
		var extype = $('#extype').val();
		
		if(extype == "0"){
			document.getElementById('extype').value = "1";
		} else {
			document.getElementById('extype').value = "0";
		}
		
		var val = $('#extype').val();
		
		if(val == "1"){
			truck_report_type = value;
		} else {
			truck_report_type = "0";
		}		
	}
	
	function generateRevenueExpensePerTruckReport(src){
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/rpt_generateRevenueExpensePerTruckReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&report_type=" + truck_report_type + "&frightOrder.truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function rptPDF_generateRevenueExpensePerTruck(src){
		
		if(document.getElementById('extype').value == 1){
			truck_report_type = "External";
		}
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/rptPDF_generateRevenueExpensePerTruckReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&report_type=" + truck_report_type + "&frightOrder.truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function excel_generateRevenueExpensePerTruck(src){
		
		if(document.getElementById('extype').value == 1){
			truck_report_type = "External";
		}
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		var directory = $('#directory').val();
		
		if(directory == ""){			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');
			counter++;
		}
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/excel_generateRevenueExpensePerTruck.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&report_type=" + truck_report_type + "&directory_path=" + directory + "&frightOrder.truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	
	function getRevenueExpensePerFrightOrder(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/report/reportFilterFormPerFon.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	var report_type = "";

	function selectExpenseTypeUsagePerFon(value){
		
		var extype = $('#extype').val();
		
		if(extype == "0"){
			document.getElementById('extype').value = "1";
		} else {
			document.getElementById('extype').value = "0";
		}
		
		var val = $('#extype').val();
		
		if(val == "1"){
			report_type = value;
		} else {
			report_type = "0";
		}		
	}
	
	function generateRevenueExpensePerFonReport(src){
		
		var counter = 0;
		var truck_type = $('#truck_type').val()
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
	
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/rpt_generateRevenueExpensePerFonReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&report_type=" + report_type + "&frightOrder.truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function rptPDF_generateRevenueExpensePerFon(src){
		
		if(document.getElementById('extype').value == 1){
			report_type = "External";
		}
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
	
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/rptPDF_generateRevenueExpensePerFonReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&report_type=" + report_type + "&frightOrder.truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function excel_generateRevenueExpensePerFon(src){
		
		if(document.getElementById('extype').value == 1){
			report_type = "External";
		}
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
	
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/excel_generateRevenueExpensePerFonReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&report_type=" + report_type + "&frightOrder.truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getUncollectedPaymentAfterPaymentRequest(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/rpt_generatePaymnetNotCollectedAfterPaymentRequest.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterPaymentNotCollectedAfterRequest(value, src){
		
		$("#filteredPaymentReportContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/filterPaymentNotCollectedAfterRequest",				
			data : "truckInformation.truck_type=" + value,
			success : function(response) {
				$('#filteredPaymentReportContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function rptPDF_generatePaymentNotCollectedAfterRequest(src){

		var directory = $('#directory').val();
		var truck_type = $('#truck_type').val();
		
		if(directory == ""){
			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');
			
		} else {
			
			$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/rptPDF_generatePaymnetNotCollectedAfterPaymentRequest.html",				
				data : "payment.directory_path=" + directory + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function excel_generatePaymentNotCollectedAfterRequest(src){
		
		var truck_type = $('#truck_type').val();
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/excel_generatePaymnetNotCollectedAfterPaymentRequest.html",				
			data : "truckInformation.truck_type=" + truck_type,
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});	
	}
	
	function getUncollectedPaymentBeforePaymentRequest(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/rpt_generatePaymnetNotCollectedBeforePaymentRequest.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterPaymentNotCollectedBeforeRequest(truck_type, src){
		
		$("#paymentReportContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/filterPaymentNotCollectedBeforeRequest.html",				
			data : "truckInformation.truck_type=" + truck_type,
			success : function(response) {
				$('#paymentReportContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function rptPDF_generatePaymentNotCollectedBeforeRequest(src){
		
		var truck_type = $('#truck_type').val();
						
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/rptPDF_generatePaymnetNotCollectedBeforePaymentRequest.html",				
			data : "truckInformation.truck_type=" + truck_type,
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	function excel_generatePaymentNotCollectedBeforeRequest(src){
		
		var directory = $('#directory').val();
		var truck_type = $('#truck_type').val();
		
		if(directory == ""){
			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');
			
		} else {
		
			$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/excel_generatePaymnetNotCollectedBeforePaymentRequest.html",				
				data : "directory_path=" + directory + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getCollectedPaymentFilterForm(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/payment/getCollectedPaymentFilterForm.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function generateCollectedPaymentReport(src){
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/getCollectedPaymentReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function rptPDF_generateCollectedPaymentReport(src){
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/rptPDF_getCollectedPaymentReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function excel_generateCollectedPaymentReport(src){
		
		var counter = 0;
		var truck_type = $('#truck_type').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		var directory = $('#directory').val();
		
		if(directory == ""){			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');			
		}
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/payment/excel_getCollectedPaymentReport.html",				
				data : "date_from=" + date_from + "&date_to=" + date_to + "&directory_path=" + directory + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getNotClosedFrightOrder(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_getNotClosedFrightOrder.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterNotClosedFrightOrder(value, src){
		
		$("#filteredReportContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_filterNotClosedFrightOrder.html",				
			data : "truckInformation.truck_type=" + value,
			success : function(response) {
				$('#filteredReportContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function rptPDF_generateNotClosedFrightOrder(src){
		
		var truck_type = $('#truck_type').val();
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rptPDF_generateNotClosedFrightOrder.html",				
			data : "truckInformation.truck_type=" + truck_type,
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
	}
	
	function excel_generateNotClosedFrightOrder(src){
		
		var directory = $('#directory').val();

		var truck_type = $('#truck_type').val();
		
		if(directory == ""){
			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');
			
		} else {
		
			$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/excel_generateNotClosedFrightOrder.html",				
				data : "directory_path=" + directory + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getDailyActivitiesPerTruck(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_getTrucksDailyActivities.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterDailyActivities(value, src){
		
		$("#filteredReportContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_filterDailyActivities.html",				
			data : "truckInformation.truck_type=" + value,
			success : function(response) {
				$('#filteredReportContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	function rptPDF_generateTruckDailyActivity(src){

		var truck_type = $('#truck_type').val();
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rptPDF_getTrucksDailyActivities.html",				
			data : "truckInformation.truck_type=" + truck_type,
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	function excel_generateTruckDailyActivity(src){
		
		var directory = $('#directory').val();
		var truck_type = $('#truck_type').val();
		
		if(directory == ""){
			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');
			
		} else {
		
			$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/excel_getTrucksDailyActivities.html",				
				data : "directory_path=" + directory + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportMenuRslt').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getTransactionListPerTruckTemplate(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_getTransactionListPerTruckTemplate.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function filterTruckByTruckType(value, src){
		
		$("#filteredTruckList").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_filterTruckByTruckType.html",				
			data : "truckInformation.truck_type=" + value,
			success : function(response) {
				$('#filteredTruckList').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getTransactionHistoryPerTruck(src){
		
		var counter = 0;
		
		var tri_id = $('#tri_id').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(tri_id == "-"){			
			document.getElementById("tri_id").className += " form-textboxerror";
			counter++;			
		}
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/rpt_getTransactionListPerTruck.html",				
				data : "tri_id=" + tri_id + "&date_from=" + date_from + "&date_to=" + date_to,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function rptPDF_generateTransactionHistoryPerTruck(src){
		
		var truck_type = $('#truck_type').val();
		
		$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rptPDF_getTransactionHistoryPerTruck.html",				
			data : "truckInformation.truck_type=" + truck_type,
			success : function(response) {
				$('#reportSearchResult').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});		
	}
	
	function excel_generateTransactionHistoryPerTruck(src){
		
		var directory = $('#directory').val();
		var truck_type = $('#truck_type').val();
		
		if(directory == ""){
			
			document.getElementById("directory").className += " form-textboxerror";
			$('#directory').attr('placeholder', 'Directory required');
			
		} else {
		
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/excel_getTransactionHistoryPerTruck.html",				
				data : "directory_path=" + directory + "&truckInformation.truck_type=" + truck_type,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getCouponUsage(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/couponReg/getCouponUsageFilterForm.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function generateCouponConsumptionReport(src){
		
		var counter = 0;
		
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		var coup_cat = $('#coup_cat').val();
		
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(coup_cat == "-"){			
			document.getElementById("coup_cat").className += " form-textboxerror";
			counter++;			
		}
		if(counter == 0){
			
			var create_date = coup_cat.substring(0,coup_cat.indexOf('*'));
			var total_amount = coup_cat.substring(coup_cat.indexOf('*')+1);
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/couponReg/rpt_generateCouponConsumptionReport.html",				
				data : "create_date=" + create_date + "&date_from=" + date_from + "&date_to=" + date_to + "&amount=" + total_amount,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getLoadingUnloadingDifferenceTemplate(src){
		
		$("#reportMenuRslt").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/rpt_getLoadingUnloadingDifferenceTemplate.html",				
			data : "",
			success : function(response) {
				$('#reportMenuRslt').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getLoadingUnloadingDifferencePerTruck(src){
		
		var counter = 0;
		
		var tri_id = $('#tri_id').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(tri_id == "-"){			
			document.getElementById("tri_id").className += " form-textboxerror";
			counter++;			
		}
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/rpt_getLoadingUnloadingDifferencePerTruck.html",				
				data : "tri_id=" + tri_id + "&date_from=" + date_from + "&date_to=" + date_to,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function rptPDF_generateLoadingUnloadingDifferencePerTruck(src){
		
		var counter = 0;
		
		var tri_id = $('#tri_id').val();
		var date_from = $('#date_from').val();
		var date_to = $('#date_to').val();
		
		if(tri_id == "-"){			
			document.getElementById("tri_id").className += " form-textboxerror";
			counter++;			
		}
		if(date_from == ""){
			document.getElementById("date_from").className += " form-textboxerror";
			$('#date_from').attr('placeholder', 'Date from required');
			counter++;
		}
		if(date_to == ""){
			document.getElementById("date_to").className += " form-textboxerror";
			$('#date_to').attr('placeholder', 'Date to required');
			counter++;
		}
		if(counter == 0){
			
			$("#reportSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/rptPDF_generateLoadingUnloadingDifferencePerTruck.html",				
				data : "tri_id=" + tri_id + "&date_from=" + date_from + "&date_to=" + date_to,
				success : function(response) {
					$('#reportSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	