package com.pair.dao;

import com.pair.pojo.keyword_sql;
import com.pair.pojo.paper_sql;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SqlMapper {
    public void insertPaper(paper_sql paper_sql);
    public void insertPK(String pid,String kid);
    public int selectKeyword(String keyword,String publisher);
    public void updateKeyword(keyword_sql keyword_sql);
    public void insertKeyword(String kid,String keyword,String publisher,int num);
    public String selectKid(String keyword,String publisher);
    public int selectNum(String keyword,String publisher);
}
