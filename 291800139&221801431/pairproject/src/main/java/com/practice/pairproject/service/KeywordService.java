package com.practice.pairproject.service;

import com.practice.pairproject.pojo.Keyword;

public interface KeywordService {

    /**
     * 插入对应一篇论文的关键词（1-->n）
     * @param kw
     * @return 自增id
     */
    int insertPKeywords(Keyword kw);
}
