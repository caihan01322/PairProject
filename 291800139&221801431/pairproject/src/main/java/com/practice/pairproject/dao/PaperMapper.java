package com.practice.pairproject.dao;

import com.practice.pairproject.pojo.Paper;
import java.util.List;

public interface PaperMapper {
    int deleteByPrimaryKey(Integer pid);

    /**
     * 插入一个论文
     * @param paper
     * @return 数据库自增id
     */
    Integer insertPaper(Paper paper);

    Paper selectByPrimaryKey(Integer pid);

    List<Paper> selectAll();

    int updateByPrimaryKey(Paper record);
}