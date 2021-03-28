package src.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import src.Basedao;
import src.PaperBean;

public class PaperDao {
	
	public static ArrayList<PaperBean> searchPaper(String content, String option) {
		if( !option.equals("keyword")) {
			String sql = "select * from article where " + option + " like \"%"+content+"%\"";
			System.out.print(sql);
			return Basedao.searchPaper(sql);
		}
		else {
			String sql = "select * from article where id in "
				+ "(select id from keywords where keyword like \"%"+content+"%\")";
			System.out.print(sql);
			return Basedao.searchPaper(sql);
		}
		
	}
	public static ArrayList<PaperBean> showAll() {
			String sql = "select * from article ";
			System.out.print(sql);
			return Basedao.searchPaper(sql);
	}
	public static PaperBean showPaper(int id) {
		String sql = "select * from article where id = "+ id;
		System.out.print(sql);
		return Basedao.showPaper(sql);
	}
	
	public static int deletePaper(int id) {
		String sql = "delete from article where id = "+ id;
		System.out.print(sql);
		return Basedao.deletePaper(sql);
	}
	
	public static String getKeyWord(int id) {
		String sql = "select * from keywords where id = "+ id;
		System.out.print(sql);
		return Basedao.getKeyWord(sql);
	}

	public static ArrayList<String>  getKeyWords() {
		String sql = "select keyword from keywords ";
		System.out.print(sql);
		return Basedao.getKeyWords(sql);
	}
	public static ArrayList<String> getKeyWordsByYear(String year) {
		String sql = "select keyword from keywords where id in "
				+ "(select id from article where year = " + year + ")";
		System.out.println(sql);
		return Basedao.getKeyWords(sql);
	}
}
