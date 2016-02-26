<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Smart Health Login</title>
	<link rel="shortcut icon" href="http://static8.depositphotos.com/1378583/974/v/950/depositphotos_9744506-Healthcare-logo.jpg">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="resources/css/login.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="margin-top:40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>Welcome to Smart Health!!</strong>
					</div>
					<div class="panel-body">
						<form:form method="POST" commandName="login">
							<fieldset>
								<div class="row">
									<div class="center-block">
										<img id="profile-img" src="resources/images/logo.jpg" alt="">
									</div>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-user"></i>
												</span> 
												<form:input path="userName" class="form-control" placeholder="Username" name="loginname" autofocus='' />
												<form:errors path="userName" cssStyle="color:#ff0000"/>
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon">
													<i class="glyphicon glyphicon-lock"></i>
												</span>
												<form:password path="userPassword" class="form-control" placeholder="Password" name="password" value="" />
												<form:errors path="userPassword" cssStyle="color:#ff0000"/>
											</div>
										</div>
										<div class="form-group">
											<input type="checkbox" name="remember" value="Remember" checked /><strong> Remember me for one week.</strong>
										</div>
										<div style="font-weight: bold; color:#ff0000;">${error}</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
										</div>
									</div>
								</div>
							</fieldset>
						</form:form>
					</div>
					<div class="panel-footer ">
						Forgot Password? <a href="forgotPassword" onClick=""> Click here </a><br />
						New Users Please! <a href="registration" onClick=""> Sign Up Here </a>
					</div>
                </div>
			</div>
		</div>
	</div>
</body>
</html>
