package sliit.paf.com;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

public class OrderManagement {

	private static Connection connection; 
	
	public OrderManagement() {
		// TODO Auto-generated constructor stub
	}

	public String updateOrder(String string, String string2, String string3, String string4, String string5,
			String string6, String string7, String string8) {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteOrder(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public String insertOrder(String orderCode, String customerID, String customerEmail, String customerName, String orderTotalAmount, String cardNo, String cvvNo) 
	{ 
		String output = ""; 
		try { 
			connection = DBConnection.getDBConnection();
			
			if (connection == null) { 
				return "Error while connecting to the database."; 
			} 
			String query = " insert into order_tab(`orderCode`,`customerID`,`customerEmail`,`customerName`,`orderorderTotalAmount`,`cardNo`,`cvvNo`)" 
			+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query); 

			preparedStmt.setString(1, orderCode); 
			preparedStmt.setString(2, customerID); 
			preparedStmt.setString(3, customerEmail); 
			preparedStmt.setString(4, customerName); 
			preparedStmt.setString(5, orderTotalAmount); 
			preparedStmt.setString(6, cardNo); 
			preparedStmt.setString(7, cvvNo); 

			preparedStmt.execute(); 
			connection.close(); 
			String newItems = readOrders(); 
			output = "{\"status\":\"success\", \"data\": \"" + 
			newItems + "\"}"; 
		} 
		catch (Exception e)  { 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the order's details.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	public String readOrders() 
	{ 
		String output = ""; 
		try { 
		
			connection = DBConnection.getDBConnection();
			if (connection == null)  { 
				return "Error while connecting to the database."; 
			} 

			output = "<head><link rel='stylesheet' href='Views/main_styles.css'></head><table border='1'><tr><th>Order Code</th><th>Customer ID</th><th>Customer Email</th><th>Customer Name</th><th>Total Amount</th><th>Card Number</th>" + "<th>CVV</th><th>Update</th><th>Remove</th></tr>"; 
			String query = "select * from order_tab"; 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			while (rs.next())  { 
				String orderID = Integer.toString(rs.getInt("orderID")); 
				String orderCode = rs.getString("orderCode");
				String customerID = rs.getString("customerID");
				String customerEmail = rs.getString("customerEmail"); 
				String customerName = rs.getString("customerName"); 
				String orderTotalAmount = rs.getString("orderTotalAmount"); 
				String cardNo = rs.getString("cardNo"); 
				String cvvNo = rs.getString("cvvNo"); 

				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + orderID + "'>" + orderCode + "</td>"; 
				output += "<td>" + customerID + "</td>"; 
				output += "<td>" + customerEmail + "</td>"; 
				output += "<td>" + customerName + "</td>"; 
				output += "<td>" + orderTotalAmount + "</td>"; 
				output += "<td>" + cardNo + "</td>"; 
				output += "<td>" + cvvNo + "</td>"; 

				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-orderId='" + orderID + "'>" + "</td></tr>"; 
			
			} 
			connection.close(); 

			output += "</table>"; 
		}  catch (Exception e) { 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 

}
