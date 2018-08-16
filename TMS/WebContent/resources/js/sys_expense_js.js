/**
 * 
 */

	function searchFrightOrder(src){
		
		var fon = $('#fon').val();
		
		if(fon == ""){
			
			document.getElementById("fon").className += " form-textboxerror";
			$('#fon').attr('placeholder', 'Fright order number required');
			
		} else {
		
			$("#expenseSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/frightOrder/foSearchResultInformation.html",				
				data : "fon=" + fon,
				success : function(response) {
					$('#expenseSearchResult').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	var expSelectedList = new Array();
	var expSelectedListId = new Array();
	var expSelectedListSize = 0;
	
	function selectExpenseType(src, expTypeId, value){
		
		var size = expSelectedList.length;
		var count = 0;
		
		for(var i = 0; i < expSelectedListId.length; i++){
			if(expTypeId == expSelectedListId[i]){				
				count++;
				expSelectedListId.splice(i, 1);
				expSelectedList.splice(i, 1);				
			}
		}
		
		if(count == 0){
			expSelectedList[size] = value;
			expSelectedListId[size] = expTypeId;
		}
		
	}
	
	function getSelectedExpenseType(src, fo_id){
		
		////**** to check whether the fright order is closed or not ****////
		var return_and_addition_amount = 0;
		
		$.ajax({
			type : "GET",
			async: false,
			url : "/TMS/advancePayment/getAdvancePaymentByFoId_ForExp.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {				
				//$('#expenseSearchResult').html(response);
				return_and_addition_amount = response;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		var index = return_and_addition_amount.indexOf(',');
		
		var return_amount = return_and_addition_amount.substring(0, index);
		
		var additional_amount = return_and_addition_amount.substring(index + 1, return_and_addition_amount.length);
		////**** --- ****////
		
		var header_notice = "<div class=\"alert alert-info alert-dismissable\" style=\"width: 100%;\">" +
							"<b>WARNING:</b> Before you add an expense related to this freight order, please check any other balance that must be TRANSFER to this freight order." +
							"</div>";
		
		var addForm_header = "<div class=\"panel panel-default\" style=\"width: 100%\">" +
								"<div class=\"panel-heading\">" +
								"<h4 class=\"panel-title\"> Expense Add From </h4>" +
								"</div><div class=\"panel-body\">";

		var addForm_footer = "</div></div>";
		
		if(return_amount > 0.0 || additional_amount > 0.0){
			
			document.getElementById("expenseAddForm").innerHTML = addForm_header + "<div style=\"width: 100%; text-align: center\">Sorry, This fright Order is closed. <br/> It is not possible to add expense.</div>" + addForm_footer;
			
		} else {
		
			var counter = "";					
			var addForm_bi = "<div class=\"form-group\"><label for=\"fon\">";
			var addForm_bm = "";
			var addForm_be = "</label><input id=\"fon\" type=\"text\" class=\"form-control\"></div>";
			
			for(var i = 0; i < expSelectedList.length; i++){
				counter = i+1;
				addForm_bm = addForm_bm + addForm_bi + expSelectedList[i] + "</label><table><tr><td><input id=\"input_" + i + "\" type=\"text\" class=\"form-control\"></td><td><input id=\"id_" + i + "\" type=\"text\" value=\"" + expSelectedListId[i] + "\" style=\"width:15px; display:none\"></td></tr></table></div>";
			}
			
			addForm_bm = addForm_bm + "<table><tr><td><button type=\"submit\" class=\"btn btn-default\" onclick=\"checkBalance('" + src + "', '" + fo_id + "')\">Check Balance</button></td>" +
					"<td style=\"padding-left: 5px;\"><button type=\"submit\" class=\"btn btn-default\" onclick=\"saveFrightOrderExpense('" + src + "', '" + fo_id + "')\">Save</button></td>" +
					"<td style=\"padding-left: 5px;\"><input id=\"inputcounter\" type=\"text\" style=\"display:none; width: 25px;\" value=\"" + counter + "\"></td></tr></table>"
			
			if(addForm_bm.length > 418){
				
				document.getElementById("expenseAddForm").innerHTML = header_notice + addForm_header + addForm_bm + addForm_footer;
				expSelectedListSize = expSelectedList.length;
				
			}
		}
	}
	
	function checkBalance(src, fo_id){
		
		var reg_exp = 0;
		
		$.ajax({
			type : "GET",
			async: false,
			url : "/TMS/expense/getExpenseByFoId.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {				
				//$('#expenseSearchResult').html(response);
				reg_exp = response;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		var inputCounter = $('#inputcounter').val();
		
		var totalSum = 0;
		
		for(var i = 0; i < inputCounter; i++){
			
			var val = $('#input_'+i).val();
			totalSum = Number(totalSum) + Number(val);
		}
		
		var totalAdvancePayment = $('#grandTotal').val();
		
		var netAdvancePayment = Number(totalAdvancePayment) - Number(reg_exp);
		
		var difference = Number(netAdvancePayment) - Number(totalSum);
		
		var addForm_header = "<div class=\"panel panel-default\" style=\"width: 100%\">" +
							"<div class=\"panel-heading\">" +
								"<h4 class=\"panel-title\"> Advance Payment vs Expense </h4>" +
							"</div><div class=\"panel-body\">";
		
		var addForm_bm = "<table class=\"table\"><th> Advance Payment </th><th> Reg. Expense Sum </th><th> Balance </th><th> New Expense Total </th><th> Difference </th>" +
				"<tr><td>" + totalAdvancePayment + "</td><td> <div onclick=\"viewRegisteredExpense('" + src + "', '" + fo_id + "')\" style=\"color: #5885c9; cursor: pointer;\">" + reg_exp + "</div> </td><td>" + netAdvancePayment + "</td><td>" + totalSum + "</td><td style=\"padding-left: 5px;\">" + difference + "</td></tr></table>"

		var addForm_footer = "</div></div><div id=\"expenselist\"></div>";
		
		document.getElementById("checkBalance").innerHTML = addForm_header + addForm_bm + addForm_footer;
		
	}
	
	function saveFrightOrderExpense(src, fo_id){
		
		expSelectedList = new Array();
		expSelectedListId = new Array();
		
		var counter = 0
		
		for(var i = 0; i < expSelectedListSize; i++){
			
			var etid = $('#id_'+i).val();
			var expenseamount = $('#input_'+i).val();
			
			if(expenseamount == ""){
				
				document.getElementById('input_'+i).className += " form-textboxerror";
				$('#input_'+i).attr('placeholder', 'Expense amount required');
				counter++;
			}
		}
		
		if(counter == 0){
			
			//$("#expenseSearchResult").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			
			for(var i = 0; i < expSelectedListSize; i++){
				
				var et_id = $('#id_'+i).val();
				var expense_amount = $('#input_'+i).val();
				
				$.ajax({
					type : "GET",			
					url : "/TMS/expense/saveFrightOrderExpenseAmount.html",				
					data : "fo_id=" + fo_id + "&et_id=" + et_id + "&expense_amount=" + expense_amount,
					success : function(response) {
						$('#bodycontent').html(response);
					},
					error : function(e) {
						alert('Error: ' + e);
					}
				});
			}
		}
		
		counter = 0;
	}
	
	function viewRegisteredExpense(src, fo_id){
		
		$("#expenselist").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/expense/getExpenseListByFoId.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#expenselist').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function viewRegisteredExpenseList(src, fo_id){
		
		var reg_exp = 0;
		
		$.ajax({
			type : "GET",
			async: false,
			url : "/TMS/expense/getExpenseByFoId.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {				
				//$('#expenseSearchResult').html(response);
				reg_exp = response;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		var return_and_addition_amount = 0;
		
		$.ajax({
			type : "GET",
			async: false,
			url : "/TMS/advancePayment/getAdvancePaymentByFoId_ForExp.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {				
				//$('#expenseSearchResult').html(response);
				return_and_addition_amount = response;
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
		
		var index = return_and_addition_amount.indexOf(',');
		
		var return_amount = return_and_addition_amount.substring(0, index);
		
		var additional_amount = return_and_addition_amount.substring(index + 1, return_and_addition_amount.length);
		
		var totalAdvancePayment = $('#grandTotal').val();
		
		var netAdvancePayment = Number(totalAdvancePayment) - Number(reg_exp);	
				
		var difference = Number(netAdvancePayment) - Number(totalSum);
		
		var balance = 0;
		
		if(netAdvancePayment > 0){
		
			balance = Number(netAdvancePayment) - Number(return_amount) - Number(additional_amount);
		} else {
			
			balance = Number(netAdvancePayment) - Number(return_amount) + Number(additional_amount);
		}
		
		var netDiffStatus = "";
		var netDiffHeader = "";
		
		if(return_amount == 0.0 && additional_amount == 0.0 && netAdvancePayment != 0){
			
			if(netAdvancePayment > 0){
				
				netDiffHeader = "<th> &nbsp; </th><th> &nbsp; </th>";
				netDiffStatus = "<td><div onclick=\"getRemainingBalanceReturnForm('" + src + "', '" + fo_id + "', '" + netAdvancePayment + "')\" style=\"color: #5885c9; cursor: pointer;\"> return </div> </td><td style=\"padding-left: 5px;\"> <div onclick=\"getRemainingBalanceTransferForm('" + src + "', '" + fo_id + "', '" + netAdvancePayment + "')\" style=\"color: #5885c9; cursor: pointer;\"> Transfer </div> </td>";
				
			} else {
				
				netDiffHeader = "<th> &nbsp; </th>";
				netDiffStatus = "<td> <div onclick=\"getAdditionalExpensePaymentForm('" + src + "', '" + fo_id + "', '" + netAdvancePayment + "')\" style=\"color: #5885c9; cursor: pointer;\"> Pay additional Exp </div> </td>";			
			}
		}
		
		var addForm_header = "<div class=\"panel panel-default\" style=\"width: 100%\">" +
							"<div class=\"panel-heading\">" +
								"<h4 class=\"panel-title\"> Advance Payment vs Expense </h4>" +
							"</div><div class=\"panel-body\">";
		
		var addForm_bm = "<table class=\"table\"><th> Advance Payment </th><th> Reg. Expense Sum </th><th> Remaining Balance </th><th> Return Amount </th><th> Additional Amount </th><th> Balance </th>" + netDiffHeader +
				"<tr><td>" + totalAdvancePayment + "</td><td>" + reg_exp + "</td><td>" + netAdvancePayment.toFixed(2) + "</td><td>" + return_amount + "</td><td>" + additional_amount + "</td><td>" + balance.toFixed(2) + "</td>" + netDiffStatus + "</tr></table>"

		var addForm_footer = "</div></div><div id=\"expenselist\"></div>";
		
		document.getElementById("checkBalance").innerHTML = addForm_header + addForm_bm + addForm_footer;
		
		
		///*** to get the list of already registered expense detail list
		$.ajax({
			type : "GET",
			async: false,
			url : "/TMS/expense/getExpenseListByFoId.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#expenselist').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getRemainingBalanceReturnForm(src, fo_id, return_amount){
		
		$("#checkBalance").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/getRemainingBalanceReturnForm.html",				
			data : "fo_id=" + fo_id + "&return_amount=" + return_amount,
			success : function(response) {
				$('#checkBalance').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveRemainingBalanceReturn(src, ap_id, fo_id){
		
		var return_amount = $('#return_amount').val();
		
		$("#checkBalance").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/saveRemainingBalanceReturn.html",				
			data : "ap_id=" + ap_id + "&return_amount=" + return_amount + "&fo_id=" + fo_id,
			success : function(response) {
				$('#checkBalance').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function getRemainingBalanceTransferForm(src, fo_id, transfer_amount){
		
		$("#checkBalance").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/getRemainingBalanceTransferForm.html",				
			data : "fo_id=" + fo_id + "&return_amount=" + transfer_amount,
			success : function(response) {
				$('#checkBalance').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveRemainingBalanceTransfer(src, ap_id){
		
		var return_amount = $('#return_amount').val();
		var coupon_transfer_amount = $('#coupon_transfer_amount').val();
		var fo_id = $('#fo_id').val();
		
		if(fo_id == "-"){
			
			document.getElementById('fo_id').className += " form-textboxerror";
			$('#fo_id').attr('placeholder', 'Fright order required');
			
		} else {
		
			$("#checkBalance").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/advancePayment/saveRemainingBalanceTransfer.html",				
				data : "ap_id=" + ap_id + "&return_amount=" + return_amount + "&fo_id=" + fo_id + "&coupon_transfer_amount=" + coupon_transfer_amount,
				success : function(response) {
					$('#checkBalance').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function getAdditionalExpensePaymentForm(src, fo_id, additional_amount){
		
		$("#checkBalance").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/getAdditionalExpensePaymentForm.html",				
			data : "fo_id=" + fo_id + "&additional_amount=" + additional_amount * -1,
			success : function(response) {
				$('#checkBalance').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function saveAdditionalExpenseAmount(src, ap_id, fo_id){
		
		var additional_amount = $('#additional_amount').val();
		
		$("#checkBalance").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/advancePayment/saveAdditionalExpenseAmount.html",				
			data : "ap_id=" + ap_id + "&additional_amount=" + additional_amount + "&fo_id=" + fo_id,
			success : function(response) {
				$('#checkBalance').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
		
	function attachmentFrightOrderRegisteredExpensList(src, fo_id){
		
		//var check = document.getElementById('isCouponOnCredit').checked;
		var isCouponOnCredit = $("#isCouponOnCredit").is(":checked");
		
		$("#expenselist").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/expense/attachmentPDF_FrightOrderRegisteredExpensList.html",				
			data : "fo_id=" + fo_id + "&is_coupon_on_credit=" + isCouponOnCredit,
			success : function(response) {
				$('#expenselist').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function editFrightOrderRegisteredExpenseInfo(src, fo_id){
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/expense/getFORegExpenseListByFoId.html",				
			data : "fo_id=" + fo_id,
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFORegistoredExpenseAmount(fo_id, src, expense_amount, e_id, expense_type_name, account_number){
		
		$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/expense/getFORegExpenseUpdateForm.html",				
			data : "fo_id=" + fo_id + "&e_id=" + e_id + "&expense_amount=" + expense_amount + "&expenseType.expense_type_name=" + expense_type_name + "&expenseType.account_number=" + account_number,
			success : function(response) {
				$('#securedDataContent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function updateFOExpenseAmount(src, fo_id, e_id, expense_type_name, account_number){
		
		var expense_amount = $('#expenseAmount').val();
		
		if(expense_amount == ""){
			
			document.getElementById('expenseAmount').className += " form-textboxerror";
			$('#expenseAmount').attr('placeholder', 'Expense amount is required.');
			
		} else {
		
			$("#securedDataContent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/expense/updateFOExpenseAmount.html",				
				data : "fo_id=" + fo_id + "&e_id=" + e_id + "&expense_amount=" + expense_amount + "&expenseType.expense_type_name=" + expense_type_name + "&expenseType.account_number=" + account_number,
				success : function(response) {
					$('#securedDataContent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	