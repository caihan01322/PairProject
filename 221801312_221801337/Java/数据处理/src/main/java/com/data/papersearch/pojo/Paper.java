package com.data.papersearch.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Paper {
	String title;
	String url;
	Date publicationYear;
	String Abstract;
	List<String>keywords;
	String Meet;
	List<String>authors;

	public String getTitle() {
		if(title==null)return "";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		if(url==null)return "";
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPublicationYear() {
//		if(publicationYear==null)return new Date();
		return publicationYear;
	}

	public void setPublicationYear(Date publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getAbstract() {
		if(Abstract==null)return "";
		return Abstract;
	}


	public void setAbstract(String anAbstract) {
		if(anAbstract==null){
			Abstract="";
		}else{
			Abstract = anAbstract.substring(0,anAbstract.length()>255?254:anAbstract.length()-1);
		}
	}

	public List<String> getKeywords() {
		if(keywords==null)return new ArrayList<>();
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getMeet() {
		if(Meet==null)return "";
		return Meet;
	}

	public void setMeet(String meet) {
		Meet = meet;
	}

	public List<String> getAuthors() {
		if(authors==null)return new ArrayList<>();
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	public static PaperHandler getDefultPaperHandler(){
		return new PaperHandler() {
			@Override
			public Paper toPaper(JSONObject jo, String meetName, File file){
				Paper paper = new Paper();
				paper.setTitle(jo.getString("title"));
				paper.setAbstract(jo.getString("abstract"));
				if(paper.getAbstract()==null)paper.setAbstract("");
				List<String> authors=new ArrayList<>();
				final JSONArray ja = jo.getJSONArray("authors");
				for (int i = 0; i < ja.size(); i++) {
					authors.add(ja.getJSONObject(i).getString("name"));
				}
				paper.setAuthors(authors);
				if(jo.getJSONArray("keywords")!=null)
					paper.setKeywords(
							jo.getJSONArray("keywords")
									.getJSONObject(0).getJSONArray("kwd").toJavaList(String.class)
					);
				else
					paper.setKeywords(new ArrayList<>());
				paper.setMeet(meetName);
				final SimpleDateFormat year = new SimpleDateFormat("yyyy");
				Date date = null;
				try {
					date = year.parse(jo.getString("publicationYear"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				paper.setPublicationYear(date);
				paper.setUrl(jo.getString("pdfUrl"));
				return paper;
			}
		};
	}
	public static PaperHandler getECCVPaperHandler(){
		return new PaperHandler() {
			@Override
			public Paper toPaper(JSONObject jo, String meetName,File file) {
				Paper paper = new Paper();
				paper.setTitle(jo.getString("论文名称"));
				paper.setAbstract(jo.getString("摘要"));
				if(paper.getAbstract()==null)paper.setAbstract("");
				List<String> authors=new ArrayList<>();
				paper.setKeywords(jo.getJSONArray("关键词").toJavaList(String.class));
				paper.setMeet(meetName);
				final SimpleDateFormat year = new SimpleDateFormat("yyyy");
				Date date = null;
				try {
					String s = jo.getString("发布时间");
					if(s.length()<4){
						date=year.parse(file.getName().substring(0,4));
					}else{
						date = year.parse(s.substring(s.length()-5));
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				paper.setPublicationYear(date);
				paper.setUrl(jo.getString("原文链接"));
				return paper;
			}
		};
	}
}
