package com.topwordanalysis.service;

import com.topwordanalysis.databaseOperation.dao.PaperDaoImpl;
import com.topwordanalysis.databaseOperation.model.Paper;
import com.topwordanalysis.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Paper类Service
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
@Service
public class PaperService {

    @Autowired
    private PaperMapper paperMapper;

    public void add(Paper paper){
        PaperDaoImpl paperDao = new PaperDaoImpl();
        paperDao.create(paper);
    }

    public List<Paper> queryAll(){
        List<Paper> paperList = new ArrayList<>();
        PaperDaoImpl paperDao = new PaperDaoImpl();
        paperList = paperDao.readAll();
        return paperList;
    }

    public List<Paper> searchByTitle(String title){
        List<Paper> paperList = new ArrayList<>();
        PaperDaoImpl paperDao = new PaperDaoImpl();
        paperList = paperDao.readByKey(new String[]{"title"},new Object[]{title});
        return paperList;
    }

    public List<Paper> selectType(String type){
        List<Paper> paperList = new ArrayList<>();
        PaperDaoImpl paperDao = new PaperDaoImpl();
        paperList = paperDao.readByKey(new String[]{"type"},new Object[]{type});
        return paperList;
    }

    public List<Paper> selectYear(String year){
        List<Paper> paperList = new ArrayList<>();
        PaperDaoImpl paperDao = new PaperDaoImpl();
        paperList = paperDao.readByKey(new String[]{"year"},new Object[]{year});
        return paperList;
    }

}
