package com.practice.pairproject.dao;

//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.practice.pairproject.pojo.Paper;
import org.apache.ibatis.annotations.Param;
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
     * @param page
     * @param pids
     * @return
     */
    List<Paper> selelctByIDlist(Pagination page, @Param("pids") List<Integer> pids);

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
     * @param page
     * @return  List<Paper>
     */
    List<Paper> searchPaper(Pagination page, Map<String, String> paramMap);


    /**
     * 【分页】mybatis-plus
     * 得到所有论文
     * 注意!!: 如果入参是有多个,需要加注解指定参数名才能在xml中取值
     *
     * @param page 分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @return 分页对象
     */
    List<Paper> selectAll(Pagination page);


    int updateByPrimaryKey(Paper record);
}