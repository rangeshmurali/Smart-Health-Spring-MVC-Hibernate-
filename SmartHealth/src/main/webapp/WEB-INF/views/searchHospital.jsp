<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Smart Health</title>
<link rel="shortcut icon"
	href="http://static8.depositphotos.com/1378583/974/v/950/depositphotos_9744506-Healthcare-logo.jpg">
<link rel="stylesheet" type="text/css"
	href="resources/css/enterToday.css">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/sb-admin.css" rel="stylesheet">
<link href="resources/css/plugins/morris.css" rel="stylesheet">
<link href="resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.js"></script>
<script>
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>

 	$(document).ready(function(){
 		if($("#hospitalName").val() == ""){
			$("#info").hide();
 		}
 		if($("#city").val() == ""){
			$("#info1").hide();
 		}
		$("#btn").click(function(){
			$("#info").show();
		});
	});
	
</script>
<style>
body{
   background-color: white;
   }
   </style>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index">SMART HEALTH</a>
		</div>
		<div class="nav navbar-right top-nav">
			<a href="#" class="navbar-brand"><i class="fa fa-user"></i>Welcome!!
				${smartId.firstName} ${smartId.lastName} </a>
		</div>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li class="active"><a href="index"><i
						class="fa fa-fw fa-dashboard"></i> Dashboard</a></li>
				<li><a href="enterToday"><i class="fa fa-fw fa-bar-chart-o"></i>
						Enter Today's Data</a></li>
				<li><a href="viewRecord"><i class="fa fa-fw fa-edit"></i>View
						Daily/Monthly Records</a></li>
				<li><a href="updateMedicalDetails"><i
						class="fa fa-fw fa-dashboard"></i>Update Medical Details</a></li>
				<li><a href="preferredHospital"><i class="fa fa-fw fa-edit"></i>Preferred
						Hospital</a></li>
				<li><a href="searchHospital"><i class="fa fa-fw fa-edit"></i>Search
						Hospital</a></li>
				<li><a href="messageUser"><i
						class="fa fa-fw fa-desktop"></i>Send Message To Doctor</a></li>
				<li><a href="viewMyReport"><i
						class="fa fa-fw fa-wrench"></i>View Reports</a></li>
				<li><a href="viewMyInvoice"><i class="fa fa-fw fa-file"></i>View
						Invoices</a></li>
				<li>
                        <a href="changePassword"><i class="fa fa-fw fa-dashboard"></i>Change Password</a>
                    </li>
				<li><a href="userLogout"><i
						class="fa fa-fw fa-dashboard"></i> Logout</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>
		<div class="container">
			</div> -->
			<div class="row">
				<div class="col-lg-6">
	                <h2>Search Hospital By Name</h2>
	            </div>
            </div>
            <form:form action="search" method="POST" role="form" name="myForm" commandName="hospital">
            <div id="error" class="alert-danger"><c:out value="${error}" /></div><br />
            <div id="error" class="alert-success"><c:out value="${success}" /></div><br />
            <div class="row">
				<div class="col-lg-6">
	                <div class="form-group input-group">
                       <form:input path="hospitalName" type="text" class="form-control" size="30" name="hospitalName" id="hospitalName" /> 
                       <span class="input-group-btn"> 
                       	<input class="btn btn-default" type="Submit" Value="Search" id="btn" />
                       </span>
                    </div>
	            </div>
            </div>
            <c:choose>
            <c:when test="${empty hospitalList}">          	
				<div class="alert alert-danger" id="info">No Results Found</div> 
            </c:when>
            <c:otherwise>
            <div class="row">
                    <div class="col-lg-12" id="id">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped" id="table">
                                <thead>
                                    <tr>
                                        <th>HospitalID</th>
                                        <th>Hospital Name</th>
                                        <th>Street</th>
                                        <th>City</th>
                                        <th>State</th>
                                        <th>ZIP</th>
                                        <th>EmailId</th>
                                        <th>Phone Number</th>
                                        <th>Delete Hospital</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="hospital" items="${hospitalList}">
									<tr>
										<td><c:out value="${hospital.hospitalId}" /></td>
										<td><c:out value="${hospital.hospitalName}" /></td>
										<td><c:out value="${hospital.street}" /></td>
										<td><c:out value="${hospital.city}" /></td>
										<td><c:out value="${hospital.state}" /></td>
										<td><c:out value="${hospital.zip}" /></td>
										<td><c:out value="${hospital.emailId}" /></td>
										<td><c:out value="${hospital.phoneNo}" /></td>
										<td><a href="addHospital?hospitalId=${hospital.hospitalId}" class="btn btn-info btn-block btn-lg addH">Add Hospital</a></td>
									</tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        
                    </div>
                 </div>
                 </c:otherwise>
                 </c:choose>
                 </form:form>
		
		<div class="row">
				<div class="col-lg-6">
	                <h2>Search Hospital By City</h2>
	            </div>
            </div>
            <form:form action="searchByCity" method="POST" role="form" name="myForm" commandName="hospital">
            <div class="row">
				<div class="col-lg-6">
	                <div class="form-group input-group">
                       <form:input path="city" type="text" class="form-control" size="30" name="city" id="city" /> 
                       <span class="input-group-btn"> 
                       	<input class="btn btn-default" type="Submit" Value="Search" id="btn1" />
                       </span>
                    </div>
	            </div>
            </div>
            <c:choose>
            <c:when test="${empty hospitalListCity}">          	
				<div class="alert alert-danger" id="info1">No Results Found</div> 
            </c:when>
            <c:otherwise>
            <div class="row" id="table">
                    <div class="col-lg-12" id="id">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>HospitalID</th>
                                        <th>Hospital Name</th>
                                        <th>Street</th>
                                        <th>City</th>
                                        <th>State</th>
                                        <th>ZIP</th>
                                        <th>EmailId</th>
                                        <th>Phone Number</th>
                                        <th>Delete Hospital</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="hospital" items="${hospitalListCity}">
									<tr>
										<td><c:out value="${hospital.hospitalId}" /></td>
										<td><c:out value="${hospital.hospitalName}" /></td>
										<td><c:out value="${hospital.street}" /></td>
										<td><c:out value="${hospital.city}" /></td>
										<td><c:out value="${hospital.state}" /></td>
										<td><c:out value="${hospital.zip}" /></td>
										<td><c:out value="${hospital.emailId}" /></td>
										<td><c:out value="${hospital.phoneNo}" /></td>
										<td><a href="#" class="btn btn-info btn-block addH">Add Hospital</a></td>
									</tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        
                    </div>
                 </div>
                 </c:otherwise>
                 </c:choose>
                 </form:form>
		</div>
	</div>
	
</body>
</html>