package com.practice.pairproject.service;

import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.KeywordVO;

import java.util.List;
import java.util.Map;

public interface KeywordService {

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
     * 分析已爬取到的论文信息，提取top10个热门领域或热门研究方向
     * 形成如关键词图谱之类直观的查看方式，点击某个关键词可展现相关的论文
     */
    /**
     * 选取所有已爬取论文 在 years 年间的热词 top-topNum（默认10）
     * @param years
     * @param topNum
     * @return
     */
    List<KeywordVO> selectAllTOPKeyword(String years, Integer topNum);

    /**
     * 选取某 meeting 在 years 年间的热词 top-topNum
     * @param meeting
     * @param years
     * @param topNum
     * @return
     */
    List<KeywordVO> selectTopKeyword(String meeting, String years, Integer topNum);

    /**
     * 选取某 meeting 在第 year年 的关于 热词list 的信息
     * @param paramMap
     * @return
     */
    List<KeywordVO> selectTOPKeywordXYear(Map<String, String> paramMap, List<KeywordVO> kList);

    /**
     * 查询某pid的论文的keyword
     * @param pid
     * @return
     */
    List<Keyword> selectByPid(Integer pid);
}
