package src.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import src.Basedao;
import src.PaperBean;

public class PaperDao {
	
	public static ArrayList<PaperBean> searchPaper(String content, String option) {
		if(option != "keyword") {
			String sql = "select * from academics where " + option + " like \"%"+content+"%\"";
			System.out.print(sql);
			return Basedao.searchPaper(sql);
		}
		else {
			String sql = "select * from academics where academicNum in "
				+ "(select academicNum from keywords where keyword like \"%"+content+"%\")";
			System.out.print(sql);
			return Basedao.searchPaper(sql);
		}
		
	}
	public static ArrayList<PaperBean> showAll() {
			String sql = "select * from academics ";
			System.out.print(sql);
			return Basedao.searchPaper(sql);
	}
	public static PaperBean showPaper(int academicNum) {
		String sql = "select * from academics where academicNum = "+ academicNum;
		return Basedao.showPaper(sql);
	}
	
	public static int deletePaper(int academicNum) {
		String sql = "delete from academics where academicNum = "+ academicNum;
		return Basedao.deletePaper(sql);
	}
	
	public static String getKeyWord(int paperNum) {
		String sql = "select * from keywords where academicNum = "+paperNum;
		return Basedao.getKeyWord(sql);
	}

	public static ArrayList<String>  getKeyWords() {
		String sql = "select keyword from keywords ";
		return Basedao.getKeyWords(sql);
	}
}
