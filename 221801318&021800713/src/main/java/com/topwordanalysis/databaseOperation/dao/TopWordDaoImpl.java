package com.topwordanalysis.databaseOperation.dao;

import com.topwordanalysis.databaseOperation.model.TopWord;

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
        return 0;
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
}

