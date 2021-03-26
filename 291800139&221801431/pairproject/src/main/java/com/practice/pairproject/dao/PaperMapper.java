package com.practice.pairproject.dao;

import com.practice.pairproject.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface PaperMapper {

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
    int deleteByPrimaryKeyList(@Param("pids") List<Integer> pids);

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