package com.topwordanalysis.databaseOperation.dao;

import com.topwordanalysis.databaseOperation.model.TopWord;

import java.util.ArrayList;
import java.util.List;

/**
 * TopWordImpl
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class TopWordDaoImpl implements BaseCRUD<TopWord> {

    @Override
    public int create(TopWord dataClass) {
        String sql="insert into topword(word,year,type) values(?,?,?)";
        Object[] paramsValue={dataClass.getTopWord(), dataClass.getYear(), dataClass.getType()};
        baseDao.update(sql,paramsValue);
        return -1;
    }

    @Override
    public void delete(Object[] key) {

    }

    @Override
    public void update(String[] propertyName, Object[] value) {

    }

    @Override
    public List<TopWord> readByKey(String[] propertyName, Object[] value) {
        return null;
    }

    @Override
    public List<TopWord> readRand(int num) {
        return null;
    }

    public List<TopWord> returnTop(){
        String sql="SELECT word, count( * ) AS count\n" +
                "FROM keyword\n" +
                "GROUP BY word\n" +
                "ORDER BY count DESC\n" +
                "LIMIT 20";
        List<TopWord> topWords=baseDao.query(sql,null,null);
        return topWords.size()>0?topWords:null;
    }
}

