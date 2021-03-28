package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ArticleDao {
	
	public static ArrayList<Paper> selectAll(int cpage,int count) {
		
		ArrayList<Paper> list = new ArrayList<Paper>();
		
		ResultSet rs = null;
		
		Connection connection = Base.getConn();
		
		PreparedStatement ps = null;
		String sqlString = "select * from academics limit ?,?";
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setInt(1, (cpage-1)*count);
			ps.setInt(2, count);
			rs = ps.executeQuery();
			while(rs.next()) {
				Paper paper = new Paper();
				paper.setAcademicNum(rs.getInt("academicNum"));
				paper.setAbstractString(rs.getString("abstract"));
				paper.setLink(rs.getString("link"));
				paper.setYear(rs.getString("year"));
				paper.setTitle(rs.getString("title"));
				ArrayList<String> keywordStrings = new ArrayList<String>();
				sqlString = "select * from keywords where academicNum= '"+rs.getInt("academicNum")+"'";
				PreparedStatement ps1 = connection.prepareStatement(sqlString);
				ResultSet rs1 = ps1.executeQuery();
				while(rs1.next()) {
					keywordStrings.add(rs1.getString("keyword"));
				}
				paper.setKeywords(keywordStrings);
				list.add(paper);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
public static ArrayList<Paper> selectSpecial(String conditionString) {
		
		ArrayList<Paper> list = new ArrayList<Paper>();
		
		ArrayList<Integer> numArrayList = new ArrayList<Integer>(); //判断是否重复包含论文编号
		
		ResultSet rs = null;
		
		Connection connection = Base.getConn();
		
		PreparedStatement ps = null;
		String sqlString =  "select * from academics where (title like '%"+conditionString+"%')";
		
		try {
			ps = connection.prepareStatement(sqlString);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				if (!numArrayList.contains(rs.getInt("academicNum"))) {
					numArrayList.add(rs.getInt("academicNum"));
					Paper paper = new Paper();
					paper.setAcademicNum(rs.getInt("academicNum"));
					paper.setAbstractString(rs.getString("abstract"));
					paper.setLink(rs.getString("link"));
					paper.setYear(rs.getString("year"));
					paper.setTitle(rs.getString("title"));
					
					ArrayList<String> keywordStrings = new ArrayList<String>();
					sqlString = "select * from keywords where academicNum= '"+rs.getInt("academicNum")+"'";
					PreparedStatement ps1 = connection.prepareStatement(sqlString);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()) {
						keywordStrings.add(rs1.getString("keyword"));
					}
					paper.setKeywords(keywordStrings);
					list.add(paper);
				}
			}
			
			sqlString =  "select * from keywords where (keyword like '%"+conditionString+"%') ";
			PreparedStatement ps1 = connection.prepareStatement(sqlString);
			ResultSet rs1 = ps1.executeQuery();
			while(rs1.next())
			{
				if (!numArrayList.contains(rs1.getInt("academicNum"))) {
					numArrayList.add(rs1.getInt("academicNum"));
					Paper paper = new Paper();
					sqlString = "select * from academics where academicNum='"+rs1.getInt("academicNum")+"'";
					PreparedStatement ps2 = connection.prepareStatement(sqlString);
					ResultSet rs2 = ps2.executeQuery();
					rs2.next();
					paper.setAcademicNum(rs2.getInt("academicNum"));
					paper.setAbstractString(rs2.getString("abstract"));
					paper.setLink(rs2.getString("link"));
					paper.setYear(rs2.getString("year"));
					paper.setTitle(rs2.getString("title"));
					ArrayList<String> keywordStrings = new ArrayList<String>();
					sqlString = "select * from keywords where academicNum= '"+rs2.getInt("academicNum")+"'";
					PreparedStatement ps3 = connection.prepareStatement(sqlString);
					ResultSet rs3 = ps3.executeQuery();
					while(rs3.next()) {
						keywordStrings.add(rs3.getString("keyword"));
					}
					paper.setKeywords(keywordStrings);
					list.add(paper);
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

public static int total() {
	int num=0;
	ResultSet rs = null;
	Connection connection = Base.getConn();
	PreparedStatement ps = null;
	String sqlString =  "select * from academics";
	try {
		ps = connection.prepareStatement(sqlString);
		rs = ps.executeQuery();
		while(rs.next()) {
			num++;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return num;
}

public static void deletePaper(int academicNum) {
		Connection connection = Base.getConn();
		String sqlString = "delete from academics where academicNum= " + academicNum;
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlString);
			
			sqlString = "delete from keywords where academicNum= " + academicNum;
			statement.executeUpdate(sqlString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
