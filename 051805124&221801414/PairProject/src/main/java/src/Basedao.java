package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/papershow?serverTimezone=UTC&useSSL=false","root","ljx10086");
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
	
	public static ArrayList<PaperBean> searchPaper(String sql){
		Connection conn = Basedao.getconnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<PaperBean> res = new ArrayList<PaperBean>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				res.add(new PaperBean(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("link"),
						rs.getString("conclude"),
						rs.getString("magazine")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeAll(ps,conn);
		}
		return res;
	}
	
	public static String getKeyWord(String sql){
		Connection conn = Basedao.getconnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		StringBuilder str = new StringBuilder();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				str.append(rs.getString("keyword")+",");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeAll(ps,conn);
		}
		return str.toString();
	}
	
	public static PaperBean showPaper(String sql) {
		PaperBean bean = null;
		Connection conn = Basedao.getconnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				bean = new PaperBean(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getString("link"),
					rs.getString("conclude"),
					rs.getString("magazine")
					);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeAll(ps,conn);
		}
		return bean;
	}
	
	public static int deletePaper(String sql) {
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
	public static ArrayList<String> getKeyWords(String sql){
		Connection conn = Basedao.getconnection();
		ResultSet rs = null;
		PreparedStatement ps = null;
		ArrayList<String> list = new ArrayList<String>(); 
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString("keyword"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Basedao.closeAll(ps,conn);
		}
		return list;
	}
}
