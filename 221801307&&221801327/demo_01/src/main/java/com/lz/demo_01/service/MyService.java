package com.lz.demo_01.service;


import com.lz.demo_01.pojo.Academics;

import java.util.List;

public interface MyService {
    //查询所有用户数集合
    List<Academics> findAllArticlesList();

    int findAllArticles();

    //分页获取文章
    List<Academics> getPage(int page, int limit);

    void addAcademic(Academics academics);

    void updateAcademic(Academics academics);

    List<Academics> findOneAcademic(Academics academics);

    void delAcademics(int[] ids);

    //按条件查询
    List<Academics> queryByParam(String author,
                                 int academicNum,
                                 String title,
                                 String keyword);

    //按条件查询分页
    List<Academics> queryByParamPage(int page,
                                     int limit,
                                     String author,
                                     int academicNum,
                                     String title,
                                     String keyword);

}
