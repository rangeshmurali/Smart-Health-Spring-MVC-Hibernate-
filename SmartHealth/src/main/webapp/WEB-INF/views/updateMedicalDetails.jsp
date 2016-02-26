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
    <script>
    function reload(){
		location.reload();
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
			    		<h3 class="panel-title">Medical Data!</h3>
			 			</div>
			 			<div class="panel-body">
			    		<form:form action="updateDetails" method="POST" commandName="smartId" role="form" name="myForm">
			    		<div id="username" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;">${error}</div>
			    		<div id="username" style="color: green; font-weight:bole; text-align:center; margin-bottom:10px;">${success}</div>
			    			<div class="row">
			    				<div class="col-xs-4 col-sm-4 col-md-4">
			    					<div class="form-group">
			    						<form:input path="bloodGroup" name="bloodGroup" class="form-control input-sm" placeholder="Blood Group" required="true" />
			    						<form:errors path="bloodGroup" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-4 col-sm-4 col-md-4">
			    					<div class="form-group">
			    						<form:input path="height" type="number" step="0.01" name="height" class="form-control input-sm" placeholder="Height" required="true" />
			    						<form:errors path="height" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-4 col-sm-4 col-md-4">
			    					<div class="form-group">
			    						<form:input path="weight" type="number" step="0.01" name="weight" class="form-control input-sm" placeholder="Weight" required="true" />
			    						<form:errors path="weight" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                			<form:input path="priscribingMedicine" name="priscribingMedicine" id="priscribingMedicine" class="form-control input-sm" placeholder="Prescribing Medicine" required="true" />
			                			<form:errors path="priscribingMedicine" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="medicalTestTook" name="medicalTestTook" id="medicalTestTook" class="form-control input-sm" placeholder="Medical Test Taken" required="true" />
			    						<form:errors path="medicalTestTook" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-12 col-sm-12 col-md-12">
			    					<div class="form-group">
			                			<form:textarea path="allergies" name="allergies" id="allergies" class="form-control input-sm" placeholder="Allergies" required="true" />
			                			<form:errors path="allergies" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<div class="row">
			    				<div class="col-xs-12 col-sm-12 col-md-12">
			    					<div class="form-group">
			                			<form:textarea path="medicalHistoryDecription" name="medicalHistoryDecription" id="medicalHistoryDecription" class="form-control input-sm" placeholder="Medical History Description" required="true" />
			                			<form:errors path="medicalHistoryDecription" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			<div class="row">
			    				<div class="col-xs-12 col-sm-12 col-md-12">
			    					<div class="form-group">
			                			<form:textarea path="vitalSignInfo" name="vitalSignInfo" id="vitalSignInfo" class="form-control input-sm" placeholder="Vital Sign Information" required="true" />
			                			<form:errors path="vitalSignInfo" cssStyle="color:#ff0000"/>
			    					</div>
			    				</div>
			    			</div>
			    			<input type="submit" value="Submit" class="btn btn-info btn-block"> <br />
			    		
			    		</form:form>
			    		<input type="reset" value="Clear Form" class="btn btn-info btn-block" onclick="reload()">
			    	</div>
	    		</div>
    		</div>
    	</div>
    	</div>
    </div>
</body>
</html>