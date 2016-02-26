<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Smart Health</title>
	<link rel="shortcut icon" href="http://static8.depositphotos.com/1378583/974/v/950/depositphotos_9744506-Healthcare-logo.jpg">
	<!-- <link rel="stylesheet" type="text/css" href="resources/css/enterToday.css"> -->
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/sb-admin.css" rel="stylesheet">
    <link href="resources/css/plugins/morris.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script>
    function reload(){
		location.reload();
	}
    </script>
    <style type="text/css">
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
          </nav>
          <div class="container">
        <div class="row centered-form">
		<div class="row">
                    <div class="col-lg-12">
                        <h2>Reports Generated!</h2>
                        <div id="username" style="color: green; font-weight:bole; text-align:center; margin-bottom:10px;">${success}</div>
                        <div id="username" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;">${error}</div>
                        <form:form action="doctorLogin" method="POST" commandName="monthlyRecord" role="form" name="myForm">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>ReportID</th>
                                        <th>DoctorID</th>
                                        <th>Generated Date</th>
                                        <th>Description</th>
                                        <th>Serverity</th>
                                        <th>Required Medical Test</th>
                                        <th>Prescribed Medicine</th>
                                        <th>Generate PDF</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="report" items="${reportList}">
									<tr>
										<td><c:out value="${report.reportId}" /></td>
										<td><c:out value="${report.smartId.smartId}" /></td>
										<td><c:out value="${report.generatedDate}" /></td>
										<td><c:out value="${report.description}" /></td>
										<td><c:out value="${report.severity}" /></td>
										<td><c:out value="${report.requiredMedicalTest}" /></td>
										<td><c:out value="${report.prescribedMedicine}" /></td>
										<td><a href="reportPdf?reportId=${report.reportId}">Click here to view as pdf</a></td>
									</tr>
									</c:forEach>
                                </tbody>
                            </table>
                        </div>
                        </form:form>
                    </div>
                </div>
            </div>
         </div>
          </div>

</body>
</html>