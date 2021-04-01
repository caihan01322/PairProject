package com.data.papersearch.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperDao {
	public int getKeywordCount(String name);
	public int getAuthorCount(String name);
	public int getMeetCount(String name);
	public int getPaperCount(String name);
	public int getPaperAuthorCount(int paperId,int authorId);
	public int getPaperKeywordCount(int paperId,int keywordId);
	public int insertKeyword(String keyword);
	public int insertMeet(String keyword);
	public int insertAuthor(String keyword);
	public int insertPaper(Object... o);
	public int findMeetIdByName(String name);
	public int findAuthorIdByName(String name);
	public int findPaperIdByName(String name);
	public int findKeywordIdByName(String name);

	public int insertPaperAuthor(int paperId, int authorId);
	public int insertPaperKeyword(int paperId, int keywordId);

}
