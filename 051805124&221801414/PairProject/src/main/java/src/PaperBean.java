package src;

public class PaperBean {
	private int paperNum;
	private String title;
	private String link;
	private String abst;
	private String year;
	private String magazine;
	private String keyword;
	public PaperBean(int paperNum,String title, String link, String abst, String year, String magazine) {
		super();
		this.paperNum = paperNum;
		this.title = title;
		this.link = link;
		this.abst = abst;
		this.year = year;
		this.magazine = magazine;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPaperNum() {
		return paperNum;
	}

	public void setPaperNum(int paperNum) {
		this.paperNum = paperNum;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAbst() {
		return abst;
	}
	public void setAbst(String abst) {
		this.abst = abst;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMagazine() {
		return magazine;
	}
	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}
}
