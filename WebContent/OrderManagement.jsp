<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="sliit.paf.com.OrderManagement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GadgetBadget REST Jersey</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/order.js"></script>
</head>
<body>


	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="OrderManagement.jsp">GadgetBadget</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class=""><a href="OrderManagement.jsp">Order Management</a></li>   
	    </ul>
	  </div>
	</nav>

	<div class="container"><div class="row"><div class="col-6"> 
		<h1 id="h1_header">Order Management</h1>
		<form id="formOrder" name="formOrder">
			 	Order code: 
			 <input id="orderCode" name="orderCode" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Customer ID: 
			 <input id="customerID" name="customerID" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Customer Email: 
			 <input id="customerEmail" name="customerEmail" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Customer Name: 
			 <input id="customerName" name="customerName" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Total Amount: 
			 <input id="orderTotalAmount" name="orderTotalAmount" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Card Number: 
			 <input id="cardNo" name="cardNo" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> CVV: 
			 <input id="cvvNo" name="cvvNo" type="text" 
			 class="form-control form-control-sm">
			 
			 <br>
			 <input id="btnSave" name="btnSave" type="button" value="Save" 
			 class="btn btn-primary">
			 
			 <input type="hidden" id="hidItemIDSave" 
			 name="hidItemIDSave" value="">
		</form>
		
		<br>
		<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<h2 id="h2_header">All The Orders' Records</h2><br>
				<div id="divItemsGrid">
				 <%
				 	 OrderManagement orderManagement = new OrderManagement(); 
					 out.print(orderManagement.readOrders());
					 
				 %>
				</div>
			</div> 
		</div> 
	</div>


</body>
</html>