package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ArticleDao {
	
	public static ArrayList<Paper> selectAll() {
		
		ArrayList<Paper> list = new ArrayList<Paper>();
		
		ResultSet rs = null;
		
		Connection connection = Base.getConn();
		
		PreparedStatement ps = null;
		String sqlString = "select * from academics";
		
		try {
			ps = connection.prepareStatement(sqlString);
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
	
