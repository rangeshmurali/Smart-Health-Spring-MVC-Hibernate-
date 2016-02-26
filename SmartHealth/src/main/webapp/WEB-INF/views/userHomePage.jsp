<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Smart Health</title>
	<link rel="shortcut icon" href="http://static8.depositphotos.com/1378583/974/v/950/depositphotos_9744506-Healthcare-logo.jpg">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/sb-admin.css" rel="stylesheet">
    <script src="jquery-1.11.2.min.js"></script>
    <link href="resources/css/plugins/morris.css" rel="stylesheet">
    <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="https://www.google.com/jsapi"></script> 
<script>
var dateJs = [];
var walking = []; 
var running = [];
var flights = [];
<c:forEach items="${date}" var="id">
dateJs.push("${id}");
</c:forEach>
<c:forEach items="${walkingDistance}" var="id">
walking.push("${id}");
</c:forEach> 
<c:forEach items="${runningDistance}" var="id">
running.push("${id}");
</c:forEach> 
<c:forEach items="${flightsClimed}" var="id">
flights.push("${id}");
</c:forEach> 

   google.load("visualization", "1", {packages:["corechart"]});
   google.setOnLoadCallback(draw);
   google.setOnLoadCallback(drawrunning);
   google.setOnLoadCallback(drawflights);
   
   function draw() {
	   var data = new google.visualization.DataTable();  
	   data.addColumn('string', 'Date');
	   data.addColumn('number', 'WalkingDistance');
	   var i;
	 for(i=0; i<dateJs.length; i++){
		  data.addRow([dateJs[i], walking[i]*1]);
		}  
		
		
	   var options = {
	      title: 'Walking Distance'
	    }; 
	     // Create and draw the visualization.
	    new google.visualization.LineChart(
	      document.getElementById('my')).draw(data, options);
	  }
    function drawrunning() {
	   var data = new google.visualization.DataTable();  
	   data.addColumn('string', 'Date');
	   data.addColumn('number', 'RunningDistance');
	   var i;
	 for(i=0; i<dateJs.length; i++){
		 data.addRow([dateJs[i], running[i]*1]);
		}  
		
		
	   var options = {
	      title: 'Running Distance'
	    }; 
	     // Create and draw the visualization.
	    new google.visualization.LineChart(
	      document.getElementById('running')).draw(data, options);
	  } 
   function drawflights() {
	   var data = new google.visualization.DataTable();  
	   data.addColumn('string', 'Date');
	   data.addColumn('number', 'FLightsClimed');
	   var i;
	 for(i=0; i<dateJs.length; i++){
		  data.addRow([dateJs[i], flights[i]*1]);
		}  
		
		
	   var options = {
	      title: 'Flights Climed'
	    }; 
	     // Create and draw the visualization.
	    new google.visualization.LineChart(
	      document.getElementById('flights')).draw(data, options);
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
            </nav>
            <div class="container">
       		 <div class="row centered-form">
		<div class="row">
                    <div class="col-lg-12">
            <div id="my" style="width: 1000px; height: 300px" style=""></div>
            </div>
            </div>
            <div class="row">
                    <div class="col-lg-12">
            <div id="running" style="width: 1000px; height: 300px" style=""></div>
            </div>

 
            </div>
            <div class="row">
                    <div class="col-lg-12">
            <div id="flights" style="width: 1000px; height: 300px" style=""></div>
            </div>
            </div>
            </div>
            <!-- /.navbar-collapse -->
            
         
        </div>
        </div>
       

</body>

</html>
