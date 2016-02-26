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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script>
		function validateForm(){
			var password = document.myForm.userPassword.value;
			var confrimPassword = document.myForm.confirmPassword.value;
			var email = document.myForm.email.value;
			alert(password);
			
			if(password != confrimPassword){
				document.getElementById("notmatch").innerHTML = "Re-Enter password dosent match";
				return false;
			}
			
			if(!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))){
				document.getElementById("notemail").innerHTML = "Invalid email! Re-Enter Email Id";
				return false;
			}
			
			return true;
		}
		
		function reload(){
			location.reload();
		}
	</script>
</head>
<body>
<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 class="panel-title">Please sign up for SmartID <small>It's free!</small></h3>
			 			</div>
			 			<div class="panel-body">
			    		<form:form action="userRegistration" method="POST" commandName="login" role="form" name="myForm" onsubmit="return(validateForm())">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                			<form:input path="smartId.firstName" name="firstname" id="firstname" class="form-control input-sm" placeholder="First Name" required="true"  />
			                			<form:errors path="smartId.firstName" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="smartId.lastName" type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name" required="true"  />
			    						<form:errors path="smartId.lastName" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="form-group">
			    				<form:input path="userName" type="text" name="username" class="form-control input-sm" placeholder="User Name" />
			    				<form:errors path="userName" cssStyle="color:#ff0000"/>
			    			</div>
			    			<div id="username" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;">${error}</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:password path="userPassword" name="userPassword" id="userPassword" class="form-control input-sm" placeholder="Password" required="true"  />
			    						<form:errors path="userPassword" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<input type="password" name="confirmPassword"  id="confirmPassword" class="form-control input-sm" placeholder="Confirm Password" required="true" />
			    					</div>
			    				</div>
			    				<div id="notmatch" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;"></div>
			    			</div>

			    			<div class="form-group">
			    				<form:input path="smartId.street" name="street" class="form-control input-sm" placeholder="Street Address" required="true"  />
			    				<form:errors path="smartId.street" cssStyle="color:#ff0000"/>
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-5 col-sm-5 col-md-5">
			    					<div class="form-group">
			    						<form:input path="smartId.city" name="city" class="form-control input-sm" placeholder="City" required="true"  />
			    						<form:errors path="smartId.city" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
			    						<form:input path="smartId.state" name="state" class="form-control input-sm" placeholder="State" required="true"  />
			    						<form:errors path="smartId.state" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-5 col-sm-5 col-md-5">
			    					<div class="form-group">
			    						<form:input path="smartId.zip" name="zip" class="form-control input-sm" placeholder="ZIP" required="true"  />
			    						<form:errors path="smartId.zip" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-8 col-sm-8 col-md-8">
			    					<div class="form-group">
			    						<form:input path="smartId.dateOfBirth" name="dob" class="form-control input-sm" placeholder="Date of Birth (yyyy-mm-dd)" required="true"  />
			    						<form:errors path="smartId.dateOfBirth" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
			    						<form:input type="number" path="smartId.age" name="age" class="form-control input-sm" placeholder="Age" required="true"  />
			    						<form:errors path="smartId.age" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-2 col-sm-2 col-md-2">
			    					<div class="form-group">
			    						<form:input path="smartId.gender" name="gender" class="form-control input-sm" placeholder="Gender" required="true"  />
			    						<form:errors path="smartId.gender" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                			<form:input path="smartId.email" name="email" class="form-control input-sm" placeholder="Email Address" required="true"  />
			                			<form:errors path="smartId.email" cssStyle="color:#ff0000"/>
			    					</div>
			    					<div id="notemail" style="color: red; font-weight:bole; text-align:left; margin-bottom:10px;"></div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="smartId.phoneNo" name="phoneno" class="form-control input-sm" placeholder="Phone Number" required="true"  />
			    						<form:errors path="smartId.phoneNo" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                			<form:input path="smartId.emergencyContactName" name="emName" class="form-control input-sm" placeholder="Emergency Contact Person Name" required="true"  />
			                			<form:errors path="smartId.emergencyContactName" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="smartId.emergencyContactPhoneNo" name="emPhone" class="form-control input-sm" placeholder="Emergency Contact Person Phone Number" required="true"  />
			    						<form:errors path="smartId.emergencyContactPhoneNo" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                			<form:input path="securityQuestion" name="securityQuestion" class="form-control input-sm" placeholder="Security Question" />
			                			<form:errors path="securityQuestion" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="securityAnswer" name="securityAnswer" class="form-control input-sm" placeholder="Security Answer" />
			    						<form:errors path="securityAnswer" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<input type="submit" value="Register" class="btn btn-info btn-block"> <br />
			    		
			    		</form:form>
			    		<input type="submit" value="Clear Form" class="btn btn-info btn-block" onclick="reload()">
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>

</body>
</html>