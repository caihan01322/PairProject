package com.practice.pairproject.dao;

import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.KeywordVO;
import com.practice.pairproject.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 选取所有已爬取论文 在 years 年间的热词 top-topNum（默认10）
     * @param years
     * @param topNum
     * @return
     */
    List<KeywordVO> selectAllTOPKeyword(@Param("years")String years, @Param("topNum")Integer topNum);

    /**
     * 选取某 meeting 在 years 年间的热词 top-topNum
     * @param meeting
     * @param years
     * @param topNum
     * @return
     */
    List<KeywordVO> selectTopKeyword(@Param("meeting")String meeting, @Param("years")String years, @Param("topNum")Integer topNum);

    /**
     * 选取某 meeting 在第 year年 的关于 热词list 的信息
     * @param paramMap
     * @return
     */
    KeywordVO selectTOPKeywordXYear(Map<String, String> paramMap);

    Keyword selectByPrimaryKey(Integer kpid);

    List<Keyword> selectAll();

    int updateByPrimaryKey(Keyword record);
}