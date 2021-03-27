package com.practice.pairproject.dao;

import com.practice.pairproject.pojo.Keyword;
import java.util.List;

public interface KeywordMapper {
    int deleteByPrimaryKey(Integer kpid);

    /**
     * 插入对应一篇论文的关键词（1-->n）
     * @param kw
     * @return 自增id
     */
    int insertPKeywords(Keyword kw);

    /**
     * 查询拥有此keyword的所有pid的list
     * @param kw
     * @return
     */
    List<Integer> searchKeywords(String kw);

    Keyword selectByPrimaryKey(Integer kpid);

    List<Keyword> selectAll();

    int updateByPrimaryKey(Keyword record);
}