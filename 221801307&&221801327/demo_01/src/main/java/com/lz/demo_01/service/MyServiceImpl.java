package com.lz.demo_01.service;

import com.lz.demo_01.dao.MyDao;
import com.lz.demo_01.pojo.Academics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class MyServiceImpl implements MyService{
    @Autowired
    private MyDao myDao;
    public List<Academics> findAllArticlesList(){return myDao.findAllArticleList();}

    @Override
    public List<Academics> getPage(int page, int limit) {
        return myDao.getPage(page,limit);
    }

    @Override
    public int findAllArticles() {
        return myDao.findAllArticles();
    }

    public void addAcademic(Academics academics){myDao.addAcademic(academics);}

    public void updateAcademic(Academics academics){myDao.updateAcademic(academics);}

    public List<Academics> findOneAcademic(Academics academics) {
        return myDao.findOneAcademic(academics);
    }

    public void delAcademics(int[] ids){
        for(int i = 0;i<ids.length;++i) {
            myDao.delAcademics(ids[i]);
        }
    }

    public List<Academics> queryByParam(String author,
                                 int academicNum,
                                 String title,
                                 String keyword){
        return myDao.queryByParam(author,academicNum,title,keyword);
    }
    //按条件查询分页
    @Override
    public List<Academics> queryByParamPage(int page,
                                     int limit,
                                     String author,
                                     int academicNum,
                                     String title,
                                     String keyword){
        return myDao.queryByParamPage(page,limit,author,academicNum,title,keyword);
    }


//    public List<author> findAllAuthorList(){return myDao.findAllAuthorList();}
//
//    public void updateAuthor(author author){myDao.updateAuthor(author);};
}
