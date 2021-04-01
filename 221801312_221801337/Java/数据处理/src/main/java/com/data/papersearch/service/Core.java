package com.data.papersearch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.data.papersearch.dao.PaperDao;
import com.data.papersearch.pojo.Paper;
import com.data.papersearch.pojo.PaperHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Core {
	@Autowired
	PaperDao dao;

	PaperHandler pa;
	String path;
	String meetName;
	public Core(String path, String meetName, PaperHandler pa){
		this.pa=pa;
		this.meetName=meetName;
		this.path=path;
	}

	public List<Paper> getPapers() throws IOException {
		File dir = new File(path);
		File[] files = dir.listFiles();

		ArrayList<Paper> papers = new ArrayList<>();
		for (File file : files) {
			StringBuilder sb = new StringBuilder();

			FileReader fr = new FileReader(file);
			Scanner scanner = new Scanner(fr);
			//从文件读取
			while(scanner.hasNextLine()){
				sb.append(scanner.nextLine());
			}
			//去除最后的";"
			if(sb.charAt(sb.length()-1)==';')
				sb=sb.replace(sb.length()-1,sb.length(),"");

			JSONObject jo = JSON.parseObject(sb.toString());
			papers.add(pa.toPaper(jo,meetName,file));
		}
		return papers;
	}
	@Transactional
	public void savePaper(Paper paper){
		if(dao.getMeetCount(paper.getMeet())==0){
			dao.insertMeet(paper.getMeet());
		}
		for (String keyword : paper.getKeywords()) {
			if(dao.getKeywordCount(keyword)==0){
				dao.insertKeyword(keyword);
			}
		}
		for (String author : paper.getAuthors()) {
			if(dao.getAuthorCount(author)==0){
				dao.insertAuthor(author);
			}
		}
		System.out.println(paper.getMeet()+": "+paper.getTitle());
		if(dao.getPaperCount(paper.getTitle())==0){
			dao.insertPaper(
					paper.getTitle(),
					paper.getUrl(),
					paper.getPublicationYear(),
					paper.getAbstract(),
					dao.findMeetIdByName(paper.getMeet()));
		}
		int paperId=dao.findPaperIdByName(paper.getTitle());
		for (String author : paper.getAuthors()) {
			int authorId=dao.findAuthorIdByName(author);
			if(dao.getPaperAuthorCount(paperId,authorId)==0)
			dao.insertPaperAuthor(paperId,authorId);
		}
		for (String keyword : paper.getKeywords()) {
			int keywordId=dao.findKeywordIdByName(keyword);
			if(dao.getPaperKeywordCount(paperId,keywordId)==0)
				dao.insertPaperKeyword(paperId,keywordId);
		}

	}
	public void start(){
		List<Paper> papers = null;
		Core core=this;
		try {
			papers = getPapers();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Paper paper : papers) {
			this.savePaper(paper);
		}
	}
	public PaperHandler getPa() {
		return pa;
	}

	public void setPa(PaperHandler pa) {
		this.pa = pa;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMeetName() {
		return meetName;
	}

	public void setMeetName(String meetName) {
		this.meetName = meetName;
	}
}
