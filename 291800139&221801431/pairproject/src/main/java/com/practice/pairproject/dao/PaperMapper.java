package com.practice.pairproject.dao;

import com.practice.pairproject.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 通过pid-list查询对应的paper列表
     * @param pids
     * @return
     */
    List<Paper> selelctByIDlist(@Param("pids") List<Integer> pids);

    /**
     * 插入一个论文
     * @param paper
     * @return 数据库自增id
     */
    Integer insertPaper(Paper paper);


    /**
     * 多条件联合模糊查询
     * 若查询条件存在keyword，则在模糊查询此keyword得到的paper里面进行相关查询
     * @param paramMap
     * @Param ("pids") List<Integer> pids
     * @return  List<Paper>
     */
    List<Paper> searchPaper(Map<String, String> paramMap);


    /**
     * 【分页】
     * 得到所有论文
     * @return
     */
    List<Paper> selectAll();

    int updateByPrimaryKey(Paper record);
}