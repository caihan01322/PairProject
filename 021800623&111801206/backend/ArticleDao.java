package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
public static ArrayList<Paper> selectSpecial(String conditionString,int cpage,int count) {
		
		ArrayList<Paper> list = new ArrayList<Paper>();
		
		ArrayList<Integer> numArrayList = new ArrayList<Integer>(); //判断是否重复包含论文编号
		
		ResultSet rs = null;
		
		Connection connection = Base.getConn();
		
		PreparedStatement ps = null;
		String sqlString =  "select * from academics where title like '%"+conditionString+"%' "
				+ "or academicNum in (select academicNum from keywords where keyword like '%"+conditionString+"%') limit ?,?";
		
		try {
			ps = connection.prepareStatement(sqlString);
			ps.setInt(1, (cpage-1)*count);
			ps.setInt(2, count);
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
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

public static int searchTotal(String conditionString) {
	int num=0;
	ResultSet rs = null;
	Connection connection = Base.getConn();
	PreparedStatement ps = null;
	String sqlString ="select count(*) from academics where title like '%"+conditionString+"%' "
			+ "or academicNum in (select academicNum from keywords where keyword like '%"+conditionString+"%')";
	try {
		ps = connection.prepareStatement(sqlString);
		rs = ps.executeQuery();
		while(rs.next()) {
			num = rs.getInt(1);
		}
		connection.close();
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return num;
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
		connection.close();
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
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

public static HotWord selectYearData(String year,String meeting) {
	
	int max = 0;
	int min = 0;
	
	HotWord hotWord = new HotWord();
	hotWord.setMeetingName(meeting);
	if (meeting.equals("CVPR")) {
		min = 1;
		max = 1920;
	}
	else if (meeting.equals("ICCV")) {
		min = 1921;
		max = 2919;
	}
	else {
		min = 2920;
		max = 5951;
	}
	
	String sqlString = "select keyword from keywords where academicNum in "
			+ "	(select academicNum from academics where year ='"+year+"' and academicNum <= "+max+" and academicNum>="+min+")";
	ResultSet rs = null;
	ArrayList<String> keywordList = new ArrayList<String>();

	Connection connection = Base.getConn();
	
	PreparedStatement ps = null;
	
	try {
		ps = connection.prepareStatement(sqlString);
		rs = ps.executeQuery();
		while(rs.next()) {
			String keywordString = rs.getString("keyword");
			keywordList.add(keywordString.toLowerCase());

		}
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<Map.Entry<String, Integer>> wordList;
	Map<String,Integer> wordMap = new HashMap<String, Integer>();
	for (int i = 0; i < keywordList.size(); i++) {
		String keywordString = keywordList.get(i);
		Integer countInteger = wordMap.get(keywordString);
		if (countInteger == null) {
			countInteger = 1;
		}
		else {
			countInteger++;
		}
		wordMap.put(keywordString, countInteger);
	}
	hotWord.setWordMap(wordMap);
	wordList = new ArrayList<Map.Entry<String,Integer>>(wordMap.entrySet());
	Collections.sort(wordList, new Comparator<Map.Entry<String,Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o2.getValue()==o1.getValue()){
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }
    });
	ArrayList<String> wordStrings = new ArrayList<String>();
	ArrayList<Integer> numList = new ArrayList<Integer>();
	int i = 1;
	for (Map.Entry<String,Integer> entry:wordList)
	{
		if (i>10) {
			break;
		}
		wordStrings.add(entry.getKey());
		numList.add(entry.getValue());
		i++;
	}
	hotWord.setNumList(numList);
	hotWord.setWordList(wordStrings);
	return hotWord;
	
}

public static ArrayList<HotWord> getMeetingList(String year) {
	ArrayList<HotWord> meetingHotWord = new ArrayList<HotWord>();
	HotWord hotWord1 = selectYearData(year, "CVPR");
	HotWord hotWord2 = selectYearData(year, "ICCV");
	HotWord hotWord3 = selectYearData(year, "ECCV");
	
	
	meetingHotWord.add(hotWord1);
	meetingHotWord.add(hotWord2);
	meetingHotWord.add(hotWord3);
	
	return meetingHotWord;
	
}

public static ArrayList<String> selectKeyWords() {
	ArrayList<String> keywordList = new ArrayList<String>();
	ResultSet rs = null;
	
	Connection connection = Base.getConn();
	
	PreparedStatement ps = null;
	String sqlString = "select * from keywords";
	try {
		ps = connection.prepareStatement(sqlString);
		rs = ps.executeQuery();
		while(rs.next()) {
			String keywordString = rs.getString("keyword");
			keywordList.add(keywordString.toLowerCase());
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<Map.Entry<String, Integer>> wordList;
	Map<String,Integer> wordMap = new HashMap<String, Integer>();
	for (int i = 0; i < keywordList.size(); i++) {
		String keywordString = keywordList.get(i);
		Integer countInteger = wordMap.get(keywordString);
		if (countInteger == null) {
			countInteger = 1;
		}
		else {
			countInteger++;
		}
		wordMap.put(keywordString, countInteger);
	}
	wordList = new ArrayList<Map.Entry<String,Integer>>(wordMap.entrySet());
	Collections.sort(wordList, new Comparator<Map.Entry<String,Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o2.getValue()==o1.getValue()){
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }
    });
	ArrayList<String> top10 = new ArrayList<String>();
	int i = 1;
	for (Map.Entry<String,Integer> entry:wordList)
	{
		if (i>10) {
			break;
		}
		top10.add(entry.getKey());
		i++;
	}
	return top10;
	
}
	
}
