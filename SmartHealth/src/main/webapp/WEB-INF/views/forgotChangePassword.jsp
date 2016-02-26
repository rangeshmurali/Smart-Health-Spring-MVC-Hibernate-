<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Smart Health Login</title>
	<link rel="shortcut icon" href="resources/images/icon.jpg">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="resources/css/registration.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script>
		function validateForm(){
			var password = document.myForm.password.value;
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
<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Please answer the security question.</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form:form action="forgotChange" method="POST" commandName="login" role="form" name="myForm" onsubmit="return(validateForm())">
			    		<div style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;">${error}</div>
			    		<div style="color: green; font-weight:bole; text-align:center; margin-bottom:10px;">${success}</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			                			<input type="password" name="password" id="password" class="form-control input-sm" placeholder="New Password" required />
			    					</div>
			    				</div>
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			                			<input type="password" name="confrimPassword" id="confrimPassword" class="form-control input-sm" placeholder="Confirm Password" required />
			    					</div>
			    				</div>
			    			</div>
			    			<div id="notmatch" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;"></div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6 col-md-offset-3">
			    					<div class="form-group">
			    			<input type="submit" value="Submit" class="btn btn-info btn-block"> <br />
			    			</div>
			    				</div>
			    			</div>
			    		</form:form>
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
</body>
</html>