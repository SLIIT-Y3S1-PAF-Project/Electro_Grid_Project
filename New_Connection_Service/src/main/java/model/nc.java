package model;

import java.sql.*;


public class nc 
{
	//A common method to connect to the DB
		private Connection connect() 
		 { 
		 Connection con = null; 
		 try
		 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", ""); 
		 } 
		 catch (Exception e) 
		 {e.printStackTrace();} 
		 return con; 
		 } 
		
		
		//read items
		public String readItems() 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>NC ID</th>"
		 		+ "<th>NC Name</th>" +
		 "<th>NC Email</th>" + 
		 "<th>NC Address</th>" +
		 "<th>NC City</th>" +
		 "<th>NC Mobile</th>" +
		 "<th>NC Type</th>" +
		 "<th>NC RegDate</th>" +
		 "<th>NC Status</th>" +
		 "<th>Update</th><th>Remove</th></tr>"; 
		 
		 String query = "select * from newconnection"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String ncID = Integer.toString(rs.getInt("ncID")); 
		 String ncName = rs.getString("ncName"); 
		 String ncEmail = rs.getString("ncEmail"); 
		 String ncAddress = rs.getString("ncAddress"); 
		 String ncCity = rs.getString("ncCity"); 
		 String ncMobile = rs.getString("ncMobile");
		 String ncType = rs.getString("ncType");
		 String ncRegDate = rs.getString("ncRegDate");
		 String ncStatus = rs.getString("ncStatus");
		 
		 // Add into the html table
		 output += "<tr><td>" + ncID + "</td>"; 
		 output += "<td>" + ncName + "</td>"; 
		 output += "<td>" + ncEmail + "</td>"; 
		 output += "<td>" + ncAddress + "</td>";
		 output += "<td>" + ncCity + "</td>"; 
		 output += "<td>" + ncMobile + "</td>"; 
		 output += "<td>" + ncType + "</td>"; 
		 output += "<td>" + ncRegDate + "</td>";
		 output += "<td>" + ncStatus + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"
		 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
		 + "<input name='itemID' type='hidden' value='" + ncID 
		 + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		//insert items
		public String insertItem(String ID, String name, String Email, String Address, String City, String Mobile, String Type, String RegDate, String Status) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for inserting."; } 
		 // create a prepared statement
		 String query = " insert into newconnection (`ncID`,`ncName`,`ncEmail`,`ncAddress`,`ncCity`,`ncMobile`,`ncType`,`ncRegDate`,`ncStatus`)"
		 + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name); 
		 preparedStmt.setString(3, Email); 
		 preparedStmt.setString(4, Address); 
		 preparedStmt.setString(5, City); 
		 preparedStmt.setString(6, Mobile); 
		 preparedStmt.setString(7, Type); 
		 preparedStmt.setString(8, RegDate);
		 preparedStmt.setString(9, Status); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Inserted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while inserting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
		
		//update items
		public String updateItem(String ID, String name, String email, String address, String city, String Mobile, String Type, String RegDate, String Status) 
		{ 
			 String output = ""; 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 // create a prepared statement
			 String query = "UPDATE newconnection SET ncName=?,ncEmail=?,ncAddress=?,ncCity=?,ncMobile=?,ncType=?,ncRegDate=?,ncStatus=? WHERE ncID=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, name); 
			 preparedStmt.setString(2, email); 
			 preparedStmt.setString(3, address); 
			 preparedStmt.setString(4, city);
			 preparedStmt.setString(5, Mobile); 
			 preparedStmt.setString(6, Type); 
			 preparedStmt.setString(7, RegDate); 
			 preparedStmt.setString(8, Status);
			 preparedStmt.setInt(9, Integer.parseInt(ID)); 
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "Error while updating the item."; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		}
		
		//delete items
		public String deleteItem(String ncID) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 // create a prepared statement
		 String query = "delete from newconnection where ncID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(ncID)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the item."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
	
	
	
	
}
