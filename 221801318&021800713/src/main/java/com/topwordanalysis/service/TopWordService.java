package com.topwordanalysis.service;

import com.topwordanalysis.databaseOperation.dao.TopWordDaoImpl;
import com.topwordanalysis.databaseOperation.model.TopWord;
import com.topwordanalysis.databaseOperation.model.TopWordResult;
import com.topwordanalysis.mapper.TopWordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
@Service
public class TopWordService {
    @Autowired
    private TopWordMapper topWordMapper;

    public void add(TopWord topWord){
        TopWordDaoImpl topWordDao = new TopWordDaoImpl();
        topWordDao.create(topWord);
    }

    public List<TopWordResult> select(String[] propertyName,Object[] value){
        TopWordDaoImpl topWordDao = new TopWordDaoImpl();
        List<TopWordResult> topWords = topWordDao.returnTop(propertyName,value);
        return topWords;
    }

    public List<TopWordResult> returnAllTop(){
        TopWordDaoImpl topWordDao = new TopWordDaoImpl();
        List<TopWordResult> topWordResults = topWordDao.returnAllTop();
        return topWordResults;
    }


}
