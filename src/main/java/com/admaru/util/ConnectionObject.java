package com.admaru.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionObject {
	private static Connection conn = null;

	public static Connection getConnection() {

		String url = CONFIG.JDBC_URL;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, CONFIG.JDBC_ID, CONFIG.JDBC_PASSWORD);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
}