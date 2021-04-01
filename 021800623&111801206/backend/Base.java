package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Base {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  static Connection getConn() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/article?useSSL=false&serverTimezone=UTC","root","root");
		} catch (SQLException e) {
			System.out.println("failure");
			e.printStackTrace();
		}
		System.out.println("success");

		return conn;
		
	}
	
	public static int executeUID(String sql,Object[] params) {
		int count = 0;
		
		Connection connection = Base.getConn();
		
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i+1, params[i]);
			}
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Base.closeAll(null, ps, connection);
		}
		return count;
	}
	
	public static void closeAll(ResultSet rs,PreparedStatement ps,Connection conn) {
		
		
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}

