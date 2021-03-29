package com.lz.demo_01.dao;

import com.lz.demo_01.pojo.Academics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MyDao {
    int findAllArticles();

    List<Academics> findAllArticleList();

    List<Academics> getPage(@Param("start") int page, @Param("size") int limit);

    void addAcademic(Academics academics);

    void updateAcademic(Academics academics);

    List<Academics> findOneAcademic(Academics academics);

    //批量删除用户
    void delAcademics(@Param("academicNum") int id);

    //按条件查询
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
