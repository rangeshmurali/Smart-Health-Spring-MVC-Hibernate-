<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="analyzePatient">SMART HEALTH</a>
            </div>
            <div class="nav navbar-right top-nav">
           		<a href="#" class="navbar-brand" ><i class="fa fa-user"></i>Welcome!! ${doctor.employeeName} </a>
           </div>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="analyzePatient"><i class="fa fa-fw fa-bar-chart-o"></i>Analyze Patient Data</a>
                    </li>
                    <li>
                        <a href="viewReport"><i class="fa fa-fw fa-edit"></i>View Generated Report</a>
                    </li>
                    <li>
                        <a href="doctorMessages"><i class="fa fa-fw fa-dashboard"></i>Messages</a>
                    </li>
                    <li>
                        <a href="changeDoctorPassword"><i class="fa fa-fw fa-edit"></i>Change Password</a>
                    </li>
                    <li>
                        <a href="editDoctorProfile"><i class="fa fa-fw fa-edit"></i>Update Profile</a>
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
		<div class="row">
                    <div class="col-lg-12">
                        <h2>View Patients Records!</h2>
                        <div id="username" style="color: green; font-weight:bole; text-align:center; margin-bottom:10px;">${success}</div>
                        <div id="username" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;">${error}</div>
                        <form:form action="deleteDoctor" method="POST" commandName="message" role="form" name="myForm">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>MessageID</th>
                                        <th>From User</th>
                                        <th>Message</th>
                                        <th>Date</th>
                                        <th>Reply to Doctor</th>
                                        <th>Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="message" items="${messageList}">
									<tr>
										<td><c:out value="${message.messageId}" /></td>
										<td><c:out value="${message.fromUser}" /></td>
										<td><c:out value="${message.message}" /></td>
										<td><c:out value="${message.messageDate}" /></td>
										<td><a href="replyDoctor?userUserName=${message.fromUser}" class="btn btn-info btn-block">Reply</a></td>
										<td><input type="checkbox" name="delete" value="${message.messageId}" />Delete Message</td>
									</tr>
									</c:forEach>
                                </tbody>
                            </table>
                            <div class="col-lg-4 col-lg-offset-4">
                            <input type="submit" value="Delete Messages" class="btn btn-info btn-block"> <br />
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