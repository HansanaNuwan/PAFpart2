<%@page import="com.item"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products Management</title>

	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/items.js"></script>
	
</head>
<body>
<div class="container">
		<div class="row">
				<div class="col-6">

	<h1>Product Management</h1> <br>
	
<form id="formItem" name="formItem">

<%--
	 Product ID:
	 <input id="ProductID" name="ProductID" type="text"
	 class="form-control form-control-sm"> 
 --%>
	 
	 <br> Product Name:
	 <input id="ProductName" name="ProductName" type="text"
	 class="form-control form-control-sm">
	 
	 <br> Product Description:
	 <input id="ProductDesc" name="ProductDesc" type="text"
	 class="form-control form-control-sm">
	 
	 <br> Product Registration :
	 <input id="ProductReg" name="ProductReg" type="text"
	 class="form-control form-control-sm">
	 
	 <br> Product Price:
	 <input id="ProductPrice" name="ProductPrice" type="text"
	 class="form-control form-control-sm">
	 
	  <br> Inventor ID:
	 <input id="InventorID" name="InventorID" type="text"
	 class="form-control form-control-sm">
	 
	 
	 <br>
	 <input id="btnSave" name="btnSave" type="button" value="Save"
	 class="btn btn-primary">
	 <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	 
</form>


	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
<br>
<div id="divItemsGrid">
	 <%
	 item itemObj = new item();
	 out.print(itemObj.readItems());
	 %>
</div>
</div> </div> </div>
</body>
</html>