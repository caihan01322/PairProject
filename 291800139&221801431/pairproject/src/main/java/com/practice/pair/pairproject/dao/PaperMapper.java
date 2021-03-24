package com.practice.pair.pairproject.dao;

import com.practice.pair.pairproject.pojo.Paper;
import java.util.List;

public interface PaperMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Paper record);

    Paper selectByPrimaryKey(Integer pid);

    List<Paper> selectAll();

    int updateByPrimaryKey(Paper record);
}