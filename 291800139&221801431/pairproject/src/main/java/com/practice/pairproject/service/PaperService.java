package com.practice.pairproject.service;

import com.practice.pairproject.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

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

    /**
     * 多条件联合模糊查询
     * @param paramMap
     * @return  List<Paper>
     */
    List<Paper> searchPaper(Map<String, String> paramMap);


    /**
     * 【分页】
     * 得到所有论文
     * @return
     */
    List<Paper> selectAll();


    /**
     * 根据keyword查询相关的论文列表
     * @param keyword
     * @return
     */
    List<Paper> selectPaperByKeyword(String keyword);

}
