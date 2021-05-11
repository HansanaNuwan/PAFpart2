package com;
import java.sql.*;


public class item

{ //A common method to connect to the DB
	private Connection connect()
		 {
			 Connection con = null;
			 try
			 {
				 Class.forName("com.mysql.jdbc.Driver");
				
				 //DBServer/DBName, username, password
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget", "root", "hansana");
			 }
			 catch (Exception e)
			 {
				 e.printStackTrace();
			 }
			 return con;
		 }
	
	
public String readItems()
	{
	 String output = "";
	try
	 {
		Connection con = connect();
	 if (con == null)
	 {
		 return "Error while connecting to the database for reading.";
	 }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Product ID</th><th>ProductName</th>" +
	 "<th>ProductDesc</th>" +
	 "<th>ProductReg</th>" +"<th>ProductPrice</th>" +"<th>InventorID</th>" +
	 "<th>Update</th><th>Remove</th></tr>";
	 
	 String query = "select * from products";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
		 String ProductID = Integer.toString(rs.getInt("ProductID"));
		 String ProductName = rs.getString("ProductName");
		 String ProductDesc = rs.getString("ProductDesc");
		 String ProductReg = rs.getString("ProductReg");
		 String ProductPrice = rs.getString("ProductPrice");
		 String InventorID = rs.getString("InventorID");
		 
		 // Add into the html table
		 //output+="<tr><td><input id= 'hidItemIDUpdate' name='hidItemIDUpdate' type ='hideen' value='" + ProductID + "'>" + ProductID + "</td>";
		 output += "<tr><td>" + ProductID + "</td>";
		 output += "<td>" + ProductName + "</td>";
		 output += "<td>" + ProductDesc + "</td>";
		 output += "<td>" + ProductReg + "</td>";
		 output += "<td>" + ProductPrice + "</td>";
		 output += "<td>" + InventorID + "</td>";
		 
	// buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
		 		+ "class='btnUpdate btn btn-secondary' data-itemid='"+ ProductID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ ProductID +"'></td></tr>";
								 
	 }
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while Reading the Procuts.\"}";
		 System.err.println(e.getMessage());
	 }
	return output;	
	
	}

	
public String insertItem(String ProductName, String ProductDesc, String ProductReg , String ProductPrice , String InventorID)
		 {
			String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting."; 
				 
			 }
			 
			 // create a prepared statement
			 String query = " insert into products(`ProductName`,`ProductDesc`,`ProductReg`,`ProductPrice`,InventorID)"+ " values (?, ?, ?, ?, ?)";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 //preparedStmt.setInt(1,0);
			 preparedStmt.setString(1,ProductName);
			 preparedStmt.setString(2, ProductDesc);
			 preparedStmt.setString(3, ProductReg);
			 preparedStmt.setString(4, ProductPrice);
			 preparedStmt.setString(5, InventorID);
			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 String newItems = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
					 newItems + "\"}"; 
		 }
		 catch (Exception e)
		 	{
			 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";
					 System.err.println(e.getMessage());
		 	}
		 	return output;
		 }


	
public String updateItem( String ProductID, String ProductName, String ProductDesc, String ProductReg, String ProductPrice, String InventorID)

	 {
	 String output = "";
	 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
			 return "Error while connecting to the database for updating."; 
			 
		 }
		 
		 // create a prepared statement
		 String query = "UPDATE products SET ProductID=?,ProductName=?,ProductDesc=?,ProductReg=?,ProductPrice=?,InventorID=? WHERE ProductID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setString(1, ProductName);
		 preparedStmt.setString(2, ProductDesc);
		 preparedStmt.setString(3, ProductReg);
		 preparedStmt.setString(4, ProductPrice);
		 preparedStmt.setString(5, InventorID);
		 preparedStmt.setInt(6, Integer.parseInt(ProductID));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newItems = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newItems + "\"}"; 
		 
		 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while Updating the Products.\"}";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 
}


public String deleteItem(String ProductID)
	 {
	 String output = "";
	 try
	 {
		 Connection con = connect();
		 
		 if (con == null)
		 {
			 return "Error while connecting to the database for deleting."; 
			 
		 }
		 
		 // create a prepared statement
		 String query = "delete from products where ProductID=?";
		 
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ProductID));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 String newItems = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" +
				 newItems + "\"}"; 
		 
		 }
	 catch (Exception e)
	 {
		 output = "{\"status\":\"error\", \"data\": \"Error while Deleting the Products.\"}";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 