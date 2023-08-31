package com.bank.management.system.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class Utility 
{
  
	public static Connection getConnection()
	{
		String DB_URL = "jdbc:mysql://localhost:3306/BankManagementSystem";
		String USER = "root";
	    String PASS = "root";
	    Connection con = null;
	
	try
	{
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(DB_URL,USER,PASS);
			if(con.isClosed())
	        {
	        	System.out.println("Connection not Successfully");
	        }else
	        {
	        	System.out.println("Connection Successfully");
	        }
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return con;
 }
}