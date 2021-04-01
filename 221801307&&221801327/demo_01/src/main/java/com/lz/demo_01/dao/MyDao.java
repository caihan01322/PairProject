package com.lz.demo_01.dao;

import com.lz.demo_01.pojo.Academics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MyDao {
    //查询所有文章个数
    int findAllArticles();

    //查询所有文章
    List<Academics> findAllArticleList();

    //查询所有文章分页
    List<Academics> getPage(@Param("start") int page, @Param("size") int limit);

    //添加文章
    void addAcademic(Academics academics);

    //更新文章
    void updateAcademic(Academics academics);

    //查找一篇文章
    List<Academics> findOneAcademic(Academics academics);

    //批量删除文章
    void delAcademics(@Param("academicNum") int id);

    //按条件查询文章
    List<Academics> queryByParam(@Param("author") String author,
                                 @Param("academicNum") int academicNum,
                                 @Param("title") String title,
                                 @Param("keyword") String keyword);

    //按条件查询并分页
    List<Academics> queryByParamPage(@Param("page") int page,
                                     @Param("limit") int limit,
                                     @Param("author") String author,
                                     @Param("academicNum") int academicNum,
                                     @Param("title") String title,
                                     @Param("keyword") String keyword);

}
