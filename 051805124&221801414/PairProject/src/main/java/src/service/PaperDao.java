package src.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import src.Basedao;
import src.PaperBean;

public class PaperDao {
	
	public static ArrayList<PaperBean> searchPaper(String content, String option, int curpage) {
		int start = (curpage-1)*6;
		if(content == null || content.equals("")) {
			String sql = "select * from article limit "+start+",6";
			System.out.println(sql);
			return Basedao.searchPaper(sql);
		}
		if( !option.equals("keyword")) {
			String sql = "select * from article where " + option + " like \"%" + content + "%\" limit "+start + ",6";
			System.out.println(sql);
			return Basedao.searchPaper(sql);
		}
		else {
			String sql = "select * from article where id in "
				+ "(select id from keywords where keyword like \"%"+content+"%\") limit "+start+",6";
			System.out.println(sql);
			return Basedao.searchPaper(sql);
		}
	}
	
	public static int getItemNum(String content, String option) {
		if(content == null || content.equals("")) {
			String sql = "select count(*) from article";
			System.out.println(sql);
			return Basedao.getItemNum(sql);
		}
		if( !option.equals("keyword")) {
			String sql = "select count(*) from article where " + option + " like \"%" + content + "%\"";
			System.out.println(sql);
			return Basedao.getItemNum(sql);
		}
		else {
			String sql = "select count(*) from article where id in "
				+ "(select id from keywords where keyword like \"%"+content+"%\")";
			System.out.println(sql);
			return Basedao.getItemNum(sql);
		}
	}

	public static PaperBean showPaper(int id) {
		String sql = "select * from article where id = "+ id;
		System.out.println(sql);
		return Basedao.showPaper(sql);
	}
	
	public static int deletePaper(int id) {
		String sql = "delete from article where id = "+ id;
		System.out.println(sql);
		return Basedao.deletePaper(sql);
	}
	
	public static String getKeyWord(int id) {
		String sql = "select * from keywords where id = "+ id;
		System.out.println(sql);
		return Basedao.getKeyWord(sql);
	}

	public static ArrayList<String>  getKeyWords() {
		String sql = "select keyword from keywords ";
		System.out.println(sql);
		return Basedao.getKeyWords(sql);
	}
	public static ArrayList<String> getKeyWordsByYear(String year) {
		String sql = "select keyword from keywords where id in "
				+ "(select id from article where year = " + year + ")";
		System.out.println(sql);
		return Basedao.getKeyWords(sql);
	}
	public static ArrayList<String> getMagazineByYK(String year, String keyword) {
		String sql = "select magazine from article where year = "+ year + " and id in "
				+ "(select id from keywords where keyword = \'" + keyword + "\') ";
		System.out.println(sql);
		return Basedao.getMagazine(sql);
	}
	public static Map<String, Integer> getMagazineOccur(ArrayList<String> list){
		Map<String, Integer> map = new HashMap<String,Integer>();
		for(String str : list) {
			if (map.containsKey(str)) {
    			int occurs = map.get(str);
    			map.put(str, occurs+1);
    		} else {
    			map.put(str, 1);
    		}
		}
		return map;
	}

	public static int updatePaper(int id, String title, String year, String magazine, String link, String abst) {
		String sql = "update article set title = \'"+title+"\', year = "+year+", conclude = \'"+abst+"\', link = \'"+link+"\', magazine = \'"+magazine+"\' where id = "+id;
		System.out.println(sql);
		return Basedao.updatePaper(sql);
	}
}
