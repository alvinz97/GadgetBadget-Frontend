package sliit.paf.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection dBConnection;

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			dBConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_rest_jersey", "root", "issa123"); 
		} catch (Exception e)  {e.printStackTrace();} 
		return dBConnection; 
	}
}
