package com.topwordanalysis.databaseOperation.dao;

import com.topwordanalysis.databaseOperation.model.Paper;
import com.topwordanalysis.databaseOperation.model.TopWord;
import com.topwordanalysis.databaseOperation.model.TopWordResult;

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

    public List<TopWordResult> returnTop(String[] propertyName, Object[] value){
        String head="select word,count(*) as count from topword where ";
        String sql=head;
        int count=propertyName.length;
        //填入各个属性
        for(int i=0;i<count;i++) {
            if (i==0) {
                sql=sql+propertyName[i]+"=?";
            } else {
                sql = sql + " and " + propertyName[i] + "=?";
            }
        }
        sql = sql + " group by word order by count desc limit 20";
        List<TopWordResult> topWordList=baseDao.query(sql,value,TopWordResult.class);
        return topWordList.size()>0?topWordList:null;
    }
}

