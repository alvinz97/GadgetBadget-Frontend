package sliit.paf.com;

import java.sql.Connection;
import java.sql.PreparedStatement;

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

}
