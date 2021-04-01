package com.jdzy.papersearch.dao;

import com.jdzy.papersearch.pojo.Keyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface KeywordDao {
	Keyword findKeywordIdByName(String keyword);
	List<Map<String,Object>> findTopKeyword(Integer meetId, Date publicationYear,Integer limit);
	List<Keyword> findSimilarKeyword(String keyword,Integer limit);
	List<Keyword> findKeywordByPaperId(Integer paperId,Integer limit);
}
