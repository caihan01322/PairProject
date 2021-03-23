package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Basedao {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getconnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/paper?serverTimezone=UTC&useSSL=false","root","051805124");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static boolean isUser(String sql,String userName,String password) {
		Connection conn = Basedao.getconnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		boolean res = false;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(userName.equals(rs.getString("userName")) && password.equals(rs.getString("password"))) {
					res = true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeAll(ps,conn);
		}
		return res;
	}
	
	public static int insertUser(String sql) {
		int count = 0;
		Connection conn = Basedao.getconnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			count = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeAll(ps,conn);
		}
		return count;
	}
	
	public static void closeAll(PreparedStatement ps,Connection conn) {
			try {
				if(ps != null)
					ps.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
