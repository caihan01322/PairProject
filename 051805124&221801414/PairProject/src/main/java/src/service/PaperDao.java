package src.service;

import java.util.ArrayList;

import src.Basedao;
import src.PaperBean;

public class PaperDao {
	
	public static ArrayList<PaperBean> searchPaper(String content) {
		String sql = "select * from academics where title like \"%"+content+"%\""
				+ " or academicNum in "
				+ "(select academicNum from keywords where keyword like \"%"+content+"%\")";
		System.out.print(sql);
		return Basedao.searchPaper(sql);
	}
	
	public static int editPaper(PaperBean p) {
		String sql = "update academics set title = "+p.getTitle()+",abstract = "+p.getAbst()+" where academicNum = "+p.getPaperNum();
		return Basedao.updatePaper(sql);
	}
	
	public static int deletePaper(PaperBean p) {
		String sql = "delete from academics where academic = "+p.getPaperNum();
		return Basedao.deletePaper(sql);
	}
	
	public static String getKeyWord(int paperNum) {
		String sql = "select * from keywords where academicNum = "+paperNum;
		return Basedao.getKeyWord(sql);
	}
}
