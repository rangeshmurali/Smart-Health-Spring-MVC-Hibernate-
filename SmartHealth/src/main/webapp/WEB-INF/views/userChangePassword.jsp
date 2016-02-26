<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Smart Health</title>
	<link rel="shortcut icon" href="http://static8.depositphotos.com/1378583/974/v/950/depositphotos_9744506-Healthcare-logo.jpg">
	<link rel="stylesheet" type="text/css" href="resources/css/enterToday.css">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/sb-admin.css" rel="stylesheet">
    <link href="resources/css/plugins/morris.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
		$("#submit").prop("disabled",true);
		$("#password").keyup(function(){
			var currentPassword = $("#password").val();
			
			$.ajax({
				type : "GET",
				url : "checkCurrentPassowrd",
				data : "currentPassword=" + currentPassword,
				success : function(response){
					if(response == "0"){
						$("#notpassword").text("Invalid current password");
						$("#notpassword").css("color", "red");
						$("#submit").prop("disabled", true);
					
						}
					else {
						$("#notpassword").text("Current password mached");
						$("#notpassword").css("color", "green");
						$("#submit").prop("disabled", false);
					
						}
					
					}
				
					
				
			
				})
			
		});
		
		});
    
    function validateForm(){
		var password = document.myForm.newPassword.value;
		var confrimPassword = document.myForm.confrimPassword.value;
		
		if(password != confrimPassword){
			document.getElementById("notmatch").innerHTML = "Re-Enter password! Password dosent match";
			return false;
		}
		
		return true;
	}

	


</script>
</head>
<body>
<div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">SMART HEALTH</a>
            </div>
            <div class="nav navbar-right top-nav">
           		<a href="#" class="navbar-brand" ><i class="fa fa-user"></i>Welcome!! ${smartId.firstName} ${smartId.lastName} </a>
           </div>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="index"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="enterToday"><i class="fa fa-fw fa-bar-chart-o"></i> Enter Today's Data</a>
                    </li>
                    <li>
                        <a href="viewRecord"><i class="fa fa-fw fa-edit"></i>View Daily/Monthly Records</a>
                    </li>
                    <li>
                        <a href="updateMedicalDetails"><i class="fa fa-fw fa-dashboard"></i>Update Medical Details</a>
                    </li>
                    <li>
                        <a href="preferredHospital"><i class="fa fa-fw fa-edit"></i>Preferred Hospital</a>
                    </li>
                    <li>
                        <a href="searchHospital"><i class="fa fa-fw fa-edit"></i>Search Hospital</a>
                    </li>
                    <li>
                        <a href="messageUser"><i class="fa fa-fw fa-desktop"></i>Send Message To Doctor</a>
                    </li>
                    <li>
                        <a href="viewMyReport"><i class="fa fa-fw fa-wrench"></i>View Reports</a>
                    </li>
                    <li>
                        <a href="viewMyInvoice"><i class="fa fa-fw fa-file"></i>View Invoices</a>
                    </li>
                    <li>
                        <a href="changePassword"><i class="fa fa-fw fa-dashboard"></i>Change Password</a>
                    </li>
                    <li>
                        <a href="userLogout"><i class="fa fa-fw fa-dashboard"></i> Logout</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
        <div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Please answer the security question.</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form:form action="passwordUpdate" method="POST" commandName="login" role="form" name="myForm" onsubmit="return(validateForm())">
			    		<div style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;">${error}</div>
			    		<div style="color: green; font-weight:bole; text-align:center; margin-bottom:10px;">${success}</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			                			<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Current Password" required />
			    					</div>
			    				</div>
			    			</div>
			    			<div id="notpassword" style="color: red; font-weight: bold; text-align: center; margin-bottom: 10px;"></div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			                			<form:password path="userPassword" name="newPassword" id="newPassword" class="form-control input-sm" placeholder="New Password" required="true" />
			                			<form:errors path="userPassword" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			                			<input type="password" name="confrimPassword" id="confrimPassword" class="form-control input-sm" placeholder="New Confirm Password" required />
			    					</div>
			    				</div>
			    			</div>
			    			<div id="notmatch" style="color: red; font-weight:bold; text-align:center; margin-bottom:10px;"></div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			    			<input type="submit" value="Submit" class="btn btn-info btn-block" id="submit"> <br />
			    			</div>
			    				</div>
			    			</div>
			    		</form:form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
      </div>
</body>
</html>