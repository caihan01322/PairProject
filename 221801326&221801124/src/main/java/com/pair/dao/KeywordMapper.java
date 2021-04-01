package com.pair.dao;

import com.pair.pojo.Keyword;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface KeywordMapper {
    public int selectKeyword(String keyword, String publisher);

    public void updateKeyword(Keyword Keyword);

    public void insertKeyword(String kid, String keyword, String publisher, int num);

    public String selectKid(String keyword, String publisher);

    public int selectNum(String keyword, String publisher);

    public List<Keyword> getKeyWords(String publisher);

    public List<String> getKid(String keyword);

    public void updateKeywordByPid(String pid);

    public List<Keyword> getTop10Keyword(String publisher);

    public List<Keyword> getKeyWordsByPid(String pid);

    //模糊查询 通过keyword
    public List<String> getKidByFuzzyMode(Map<String, String> map);

    //精确查询 通过keyword
    public List<String> getKidByByPreciseMode(Map<String, String> map);


}
