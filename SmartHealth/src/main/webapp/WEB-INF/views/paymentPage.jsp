<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <style type="text/css">
   body{
   background-color: white;
   }
    </style>
    <script>
		function validateForm(){
			var cardNumber = document.myForm.cardNumber.value;
			var cvv = document.myForm.cvv.value;
			var cardname = document.myForm.cardname.value;
			var expirymonth = document.myForm.expirymonth.value;
			var d = new Date();
			
			if (!(/^[A-Za-z]+$/.test(cardname))) {
				document.getElementById("notcardname").innerHTML = "Invlid Card Holder Name!";
				return false;
			}
			
			if(cardNumber.length != 16){
				document.getElementById("notmatch").innerHTML = "Invalid card number! The card number has to be 16 digits!";
				return false;
			}
			
			if(cvv.length != 3){
				document.getElementById("notcvv").innerHTML = "Invalid cvv!";
				return false;
			}
			
			if(expirymonth < d.getMonth()){
				document.getElementById("notmonth").innerHTML = "Expiry date must be greater than current date!";
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
  <form class="form-horizontal" role="form" action="payment" method="POST" name="myForm" onsubmit="return(validateForm())">
  <input type="hidden" name="invoiceId" value="${invoiceId}" /><br />
    <fieldset>
      <legend>Payment</legend>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="card-holder-name">Name on Card</label>
        <div class="col-sm-7">
          <input type="text" class="form-control" name="cardname" id="card-holder-name" placeholder="Card Holder's Name" required>
        </div>
      </div>
      <div id="notcardname" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;"></div>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="card-number">Card Number</label>
        <div class="col-sm-7">
          <input type="number" class="form-control" name="cardNumber" id="card-number" placeholder="Debit/Credit Card Number" required><br />
        </div>
      </div>
      <div id="notmatch" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;"></div>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="expiry-month">Expiration Date</label>
        <div class="col-sm-9">
          <div class="row">
            <div class="col-xs-3">
              <select class="form-control col-sm-2" name="expirymonth" id="expiry-month">
                <option value="0">Janauary</option>
                <option value="1">February</option>
                <option value="2">March</option>
                <option value="3">April</option>
                <option value="4">May</option>
                <option value="5">June</option>
                <option value="6">July</option>
                <option value="7">August</option>
                <option value="8">September</option>
                <option value="9">October</option>
                <option value="10">November</option>
                <option value="11">December</option>
              </select>
            </div>
            <div class="col-xs-3">
              <select class="form-control" name="expiry-year">
                <option value="15">2015</option>
                <option value="16">2016</option>
                <option value="17">2017</option>
                <option value="18">2018</option>
                <option value="19">2019</option>
                <option value="20">2020</option>
                <option value="21">2021</option>
                <option value="22">2022</option>
                <option value="23">2023</option>
              </select>
            </div>
          </div>
        </div>
      </div>
      <div id="notmonth" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;"></div>
      <div class="form-group">
        <label class="col-sm-3 control-label" for="cvv">Card CVV</label>
        <div class="col-sm-3">
          <input type="number" class="form-control" name="cvv" id="cvv" placeholder="Security Code" required>
        </div>
      </div>
      <div id="notcvv" style="color: red; font-weight:bole; text-align:center; margin-bottom:10px;"></div>
      <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
          <input type="submit" class="btn btn-success" value="Pay Now"></input>
        </div>
      </div>
    </fieldset>
  </form>
</div>
        </div>
</body>
</html>