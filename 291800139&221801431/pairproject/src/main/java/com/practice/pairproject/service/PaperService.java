package com.practice.pairproject.service;

//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.plugins.Page;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.util.MyPage;

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
     * @param page
     * @return  List<Paper>
     */
    Page<Paper> searchPaper(MyPage<Paper> page, Map<String, String> paramMap);


    /**
     * 【分页】
     * 得到所有论文
     * @param page
     * @return
     */
    Page<Paper> selectAll(MyPage<Paper> page);


    /**
     * 根据keyword查询相关的论文列表
     * @param keyword
     * @param page
     * @return
     */
    Page<Paper> selectPaperByKeyword(MyPage<Paper> page, String keyword);

    Paper selectByPrimaryKey(Integer pid);

}
