/**
 * 
 */
	function FrightOrder(src){
		
		document.getElementById("frightOrder").className += " normal_btn_active";
		document.getElementById("payment").className = "";
		document.getElementById("couponRegistration").className = "";
		document.getElementById("expense").className = "";
		document.getElementById("report").className = "";
		document.getElementById("admin").className = "";
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/frightOrder/getFrightOrderTemplate.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function Payment(src){
		
		document.getElementById("frightOrder").className = "";
		document.getElementById("payment").className += " normal_btn_active";
		document.getElementById("couponRegistration").className = "";
		document.getElementById("expense").className = "";
		document.getElementById("report").className = "";
		document.getElementById("admin").className = "";
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/payment/getPaymentTemplate.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function CouponRegistration(src){
		
		document.getElementById("frightOrder").className = "";
		document.getElementById("payment").className = "";
		document.getElementById("couponRegistration").className += " normal_btn_active";
		document.getElementById("expense").className = "";
		document.getElementById("report").className = "";
		document.getElementById("admin").className = "";
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/couponReg/getCouponRegistrationTemplate.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function Expense(src){
		
		document.getElementById("frightOrder").className = "";
		document.getElementById("payment").className = "";
		document.getElementById("couponRegistration").className = "";
		document.getElementById("expense").className += " normal_btn_active";
		document.getElementById("report").className = "";
		document.getElementById("admin").className = "";
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/expense/getExpenseTemplate.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function Report(src){
		
		document.getElementById("frightOrder").className = "";
		document.getElementById("payment").className = "";
		document.getElementById("couponRegistration").className = "";
		document.getElementById("expense").className = "";
		document.getElementById("report").className += " normal_btn_active";
		document.getElementById("admin").className = "";
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "POST",			
			url : "/TMS/report/getReportTemplate.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function Admin(src){
		
		document.getElementById("frightOrder").className = "";
		document.getElementById("payment").className = "";
		document.getElementById("couponRegistration").className = "";
		document.getElementById("expense").className = "";
		document.getElementById("report").className = "";
		document.getElementById("admin").className += " normal_btn_active";
		
		$("#bodycontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
		$.ajax({
			type : "GET",			
			url : "/TMS/admin/getAdminTemplate.html",				
			data : "",
			success : function(response) {
				$('#bodycontent').html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
	
	function changeUsernamePassword(src){
		
		var counter = 0;
		
		var username = $('#username').val();
		var oldpassword = $('#oldpassword').val();
		var password = $('#password').val();
		var repassword = $('#repassword').val();
		
		if(username == ""){
			
			document.getElementById("username").className += " form-textboxerror";
			$('#username').attr('placeholder', 'User name is required');
			counter++;
		}
		if(oldpassword == ""){
			
			document.getElementById("oldpassword").className += " form-textboxerror";
			$('#oldpassword').attr('placeholder', 'Previouse password is required');
			counter++;
		}
		if(password == ""){
			
			document.getElementById("password").className += " form-textboxerror";
			$('#password').attr('placeholder', 'New password is required');
			counter++;
		}
		if(password == "pass*123"){
			
			document.getElementById("repassword").className += " form-textboxerror";
			$('#repassword').attr('placeholder', 'Not valid to use the default password.');
			counter++;
		}
		if(repassword == ""){
			
			document.getElementById("repassword").className += " form-textboxerror";
			$('#repassword').attr('placeholder', 'New password confirmation is required');
			counter++;
		}		
		if(password != repassword){
			
			document.getElementById("errorMessage").innerHTML = "The two passwords are not the same.";
			document.getElementById("password").className += " form-textboxerror";
			document.getElementById("repassword").className += " form-textboxerror";
		}
		
		if(counter == 0){

			$("#fullcontent").html("<div style=\"width: 100%\" align=\"center\"><img align=\"center\" src=\""+ src +"\" width=\"100px\"/></div>");
			$.ajax({
				type : "GET",			
				url : "/TMS/user/changeUserProfile.html",				
				data : "user_name=" + username + "&password=" + password + "&oldPassword=" + oldpassword,
				success : function(response) {
					$('#fullcontent').html(response);
				},
				error : function(e) {
					alert('Error: ' + e);
				}
			});
		}
	}
	
	function checkPasswordStrength(password){
		
		if(password.length == 0){
			password_strength.innerHTML = "";
		}
		
		var regex = new Array();		
		regex.push("[A-Z]");
		regex.push("[a-z]");
		regex.push("[0-9]");
		regex.push("[$@!%*#?&]");
		
		var passed = 0;
		
		for(var i = 0; i < regex.length; i++){
			if(new RegExp(regex[i]).test(password)){
				passed++;
			}
		}
		
		if(passed > 2 && password.length > 8){
			passed++;
		}
		
		var color = "";
		var strength = "";
		
		switch(passed){
		
		case 0:
		case 1: strength = "Weak";
				color = "red";
				break;
		case 2: strength = "Good";
				color = "darkorange";
				break;
		case 3: 
		case 4: strength = "Strong";
				color = "green";
				break;
		case 5: strength = "Very Strong";
				color = "darkgreen";
				break;
		}
		
		password_strength.innerHTML = strength;
		password_strength.style.color = color;
	}
	
	$(function(){
		$("#password").bind("keyup", function(){
			if($(this).val().length == 0){
				$("#password_strength").html("");
			}
			
			var regex = new Array();		
			regex.push("[A-Z]");
			regex.push("[a-z]");
			regex.push("[0-9]");
			regex.push("[$@!%*#?&]");
			
			var passed = 0;
			
			for(var i = 0; i < regex.length; i++){
				if(new RegExp(regex[i]).test($(this).val())){
					passed++;
				}
			}
			
			if(passed > 2 && password.length > 8){
				passed++;
			}
			
			var color = "";
			var strength = "";
			
			switch(passed){
			
			case 0:
			case 1: strength = "Weak";
					color = "red";
					break;
			case 2: strength = "Good";
					color = "darkorange";
					break;
			case 3: 
			case 4: strength = "Strong";
					color = "green";
					break;
			case 5: strength = "Very Strong";
					color = "darkgreen";
					break;
			}
			
			$("#password_strength").html(strength);
			$("#password_strength").css("color", color);
		})
	});