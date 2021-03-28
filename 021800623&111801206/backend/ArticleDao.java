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

public static ArrayList<Map.Entry<String,Integer>> selectYearData(String year,String meeting) {
	
	int min = 0;
	int max = 0;
	ArrayList<Paper> list = new ArrayList<Paper>();
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
	
	String sqlString = "select * from academics where (year = '"+year+"') and  (academicNum >= "+min+") and (academicNum <="+max+")";
	ResultSet rs = null;
	
	Connection connection = Base.getConn();
	
	PreparedStatement ps = null;
	
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
	
	List<Map.Entry<String, Integer>> wordList;
	Map<String,Integer> wordMap = new HashMap<String, Integer>();
	for (int i = 0; i < list.size(); i++) {
		ArrayList<String> tempArrayList = list.get(i).getKeywords();
		for (int j = 0; j < tempArrayList.size(); j++) {
			String wordString = tempArrayList.get(j);
			Integer countInteger = wordMap.get(wordString);
			if (countInteger == null) {
				countInteger = 1;
			}
			else {
				countInteger++;
			}
			wordMap.put(wordString, countInteger);
		}
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
	
	int i = 1;
	Map<String,Integer> wordMap1 = new HashMap<String, Integer>();
	for (Map.Entry<String,Integer> entry:wordList)
	{
		if (i>10) {
			break;
		}
		wordMap1.put(entry.getKey(), entry.getValue());
		i++;
	}
	ArrayList<Map.Entry<String, Integer>> wordList1 = new ArrayList<Map.Entry<String,Integer>>(wordMap1.entrySet());;
	return wordList1;
}
	
public static ArrayList<HotWord> getCVPRChart() {
	
	ArrayList<HotWord> hotWordList = new ArrayList<HotWord>();
	ArrayList<Map.Entry<String, Integer>> wordList2018 = ArticleDao.selectYearData("2018", "CVPR");
	for (Map.Entry<String,Integer> entry:wordList2018)
	{
		HotWord hotWord = new HotWord();
		hotWord.setWordString(entry.getKey());
		ArrayList<Integer> tArrayList= hotWord.getWordList();
		tArrayList.set(0, entry.getValue());
		hotWord.setWordList(tArrayList);
		hotWordList.add(hotWord);
	}
	ArrayList<Map.Entry<String, Integer>> wordList2019 = ArticleDao.selectYearData("2019", "CVPR");
	for (Map.Entry<String,Integer> entry:wordList2019)
	{
		boolean exist = false;
		for (int i = 0; i < hotWordList.size(); i++) {
			if (hotWordList.get(i).getWordString().equals(entry.getKey())) {
				exist = true;
				ArrayList<Integer> tArrayList = hotWordList.get(i).getWordList();
				tArrayList.set(1, entry.getValue());
				break;
			}
		}
		
		if (!exist) {
			HotWord hotWord = new HotWord();
			hotWord.setWordString(entry.getKey());
			ArrayList<Integer> tArrayList= hotWord.getWordList();
			tArrayList.set(1, entry.getValue());
			hotWord.setWordList(tArrayList);
			hotWordList.add(hotWord);
		}
	}
	
	ArrayList<Map.Entry<String, Integer>> wordList2020 = ArticleDao.selectYearData("2020", "CVPR");
	for (Map.Entry<String,Integer> entry:wordList2020)
	{
		boolean exist = false;
		for (int i = 0; i < hotWordList.size(); i++) {
			if (hotWordList.get(i).getWordString().equals(entry.getKey())) {
				exist = true;
				ArrayList<Integer> tArrayList = hotWordList.get(i).getWordList();
				tArrayList.set(2, entry.getValue());
				break;
			}
		}
		
		if (!exist) {
			HotWord hotWord = new HotWord();
			hotWord.setWordString(entry.getKey());
			ArrayList<Integer> tArrayList= hotWord.getWordList();
			tArrayList.set(2, entry.getValue());
			hotWord.setWordList(tArrayList);
			hotWordList.add(hotWord);
		}
	}
	
	
	return hotWordList;
}

public static ArrayList<HotWord> getICCVChart() {
	
	ArrayList<HotWord> hotWordList = new ArrayList<HotWord>();
	ArrayList<Map.Entry<String, Integer>> wordList2018 = ArticleDao.selectYearData("2017", "ICCV");
	for (Map.Entry<String,Integer> entry:wordList2018)
	{
		HotWord hotWord = new HotWord();
		hotWord.setWordString(entry.getKey());
		ArrayList<Integer> tArrayList= hotWord.getWordList();
		tArrayList.set(0, entry.getValue());
		hotWord.setWordList(tArrayList);
		hotWordList.add(hotWord);
	}
	ArrayList<Map.Entry<String, Integer>> wordList2019 = ArticleDao.selectYearData("2018", "ICCV");
	for (Map.Entry<String,Integer> entry:wordList2019)
	{
		boolean exist = false;
		for (int i = 0; i < hotWordList.size(); i++) {
			if (hotWordList.get(i).getWordString().equals(entry.getKey())) {
				exist = true;
				ArrayList<Integer> tArrayList = hotWordList.get(i).getWordList();
				tArrayList.set(1, entry.getValue());
				break;
			}
		}
		
		if (!exist) {
			HotWord hotWord = new HotWord();
			hotWord.setWordString(entry.getKey());
			ArrayList<Integer> tArrayList= hotWord.getWordList();
			tArrayList.set(1, entry.getValue());
			hotWord.setWordList(tArrayList);
			hotWordList.add(hotWord);
		}
	}
	
	ArrayList<Map.Entry<String, Integer>> wordList2020 = ArticleDao.selectYearData("2019", "ICCV");
	for (Map.Entry<String,Integer> entry:wordList2020)
	{
		boolean exist = false;
		for (int i = 0; i < hotWordList.size(); i++) {
			if (hotWordList.get(i).getWordString().equals(entry.getKey())) {
				exist = true;
				ArrayList<Integer> tArrayList = hotWordList.get(i).getWordList();
				tArrayList.set(2, entry.getValue());
				break;
			}
		}
		
		if (!exist) {
			HotWord hotWord = new HotWord();
			hotWord.setWordString(entry.getKey());
			ArrayList<Integer> tArrayList= hotWord.getWordList();
			tArrayList.set(2, entry.getValue());
			hotWord.setWordList(tArrayList);
			hotWordList.add(hotWord);
		}
	}
	
	
	
	return hotWordList;
}

public static ArrayList<HotWord> getECCVChart() {
	
	ArrayList<HotWord> hotWordList = new ArrayList<HotWord>();
	ArrayList<Map.Entry<String, Integer>> wordList2018 = ArticleDao.selectYearData("2018", "ECCV");
	for (Map.Entry<String,Integer> entry:wordList2018)
	{
		HotWord hotWord = new HotWord();
		hotWord.setWordString(entry.getKey());
		ArrayList<Integer> tArrayList= hotWord.getWordList();
		tArrayList.set(0, entry.getValue());
		hotWord.setWordList(tArrayList);
		hotWordList.add(hotWord);
	}
	ArrayList<Map.Entry<String, Integer>> wordList2019 = ArticleDao.selectYearData("2019", "ECCV");
	for (Map.Entry<String,Integer> entry:wordList2019)
	{
		boolean exist = false;
		for (int i = 0; i < hotWordList.size(); i++) {
			if (hotWordList.get(i).getWordString().equals(entry.getKey())) {
				exist = true;
				ArrayList<Integer> tArrayList = hotWordList.get(i).getWordList();
				tArrayList.set(1, entry.getValue());
				break;
			}
		}
		
		if (!exist) {
			HotWord hotWord = new HotWord();
			hotWord.setWordString(entry.getKey());
			ArrayList<Integer> tArrayList= hotWord.getWordList();
			tArrayList.set(1, entry.getValue());
			hotWord.setWordList(tArrayList);
			hotWordList.add(hotWord);
		}
	}
	
	ArrayList<Map.Entry<String, Integer>> wordList2020 = ArticleDao.selectYearData("2020", "ECCV");
	for (Map.Entry<String,Integer> entry:wordList2020)
	{
		boolean exist = false;
		for (int i = 0; i < hotWordList.size(); i++) {
			if (hotWordList.get(i).getWordString().equals(entry.getKey())) {
				exist = true;
				ArrayList<Integer> tArrayList = hotWordList.get(i).getWordList();
				tArrayList.set(2, entry.getValue());
				break;
			}
		}
		
		if (!exist) {
			HotWord hotWord = new HotWord();
			hotWord.setWordString(entry.getKey());
			ArrayList<Integer> tArrayList= hotWord.getWordList();
			tArrayList.set(2, entry.getValue());
			hotWord.setWordList(tArrayList);
			hotWordList.add(hotWord);
		}
	}
	
	return hotWordList;
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
			keywordList.add(rs.getString("keyword"));
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
