package com.practice.pairproject.service;

import com.practice.pairproject.pojo.Paper;
import java.util.List;

public interface PaperService {

    /**
     * 插入一个论文
     * @param paper
     * @return 数据库自增id
     */
    Integer insertPaper(Paper paper);

    /**
     * 通过主键删除
     * @param pid
     * @return
     */
    int deleteByPrimaryKey(Integer pid);

    /**
     * 通过主键list删除
     * @param pids
     * @return
     */
    int deleteByPrimaryKeyList(List<Integer> pids);
}
