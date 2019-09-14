package com.cg.healthcaresystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.healthcaresystem.exception.UserDefinedException;

public class ConnectionUtil {
	private static Connection connection;
	public static Connection getConnection() throws UserDefinedException  {
		String url="jdbc:mysql://localhost:3306/HealthCareSystem";
		String user="root@localhost";
		String password="password";
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			throw new UserDefinedException ("Error while obatining Connection ");
		}
		return connection;
	}
	public static void main(String[] args) throws UserDefinedException  {
		connection=ConnectionUtil.getConnection();
		if(connection!=null) System.out.println("Connection Obtained!!");
		else System.out.println("Connection NOT Obtained!!");
	}



}
