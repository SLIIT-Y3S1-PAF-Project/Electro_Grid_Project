package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test {
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
				 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electro_grid", "root", ""); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		return con; 
	} 
}
