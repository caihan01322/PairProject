package com.jdzy.papersearch.dao;

import com.jdzy.papersearch.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface PaperDao {
	 List<Paper> findPaperByKeywordId(Integer keywordId,Integer meetId, Date publicationYear,Integer limit);
	 List<Paper> findPaperByWord(String searchWord,Integer orderBy,Date publicationYears,Integer meetId,Integer limit,Integer page);

	Integer findPaperCountByWord(String searchWord, Date publicationYears, Integer meetId);
}
