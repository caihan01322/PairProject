package com.practice.pairproject.service;

import com.practice.pairproject.pojo.Paper;

public interface PaperService {

    /**
     * 插入一个论文
     * @param paper
     * @return 数据库自增id
     */
    Integer insertPaper(Paper paper);
}
