package com.dabai.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DBUtils {
	private final static String url = "jdbc:mysql://localhost/wordpress";
	private final static String user = "dabai";
	private final static String password = "123";

	
	/**
	 * 返回数据库连接
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public static PreparedStatement getPrePareStatement( String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = getConn().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
}
