package test;

import java.util.ArrayList;

public class Paper {
	private int academicNum;
	private String link;
	private String title;
	private String abstractString;
	private String year;
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	private ArrayList<String> keywords;
	
	public int getAcademicNum() {
		return academicNum;
	}
	public void setAcademicNum(int academicNum) {
		this.academicNum = academicNum;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstractString() {
		return abstractString;
	}
	public void setAbstractString(String abstractString) {
		this.abstractString = abstractString;
	}
	
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	
}
