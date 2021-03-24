package com.topwordanalysis.databaseOperation.dao;

import com.topwordanalysis.databaseOperation.model.Paper;

import java.util.List;

/**
 * PaperDaoImpl
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class PaperDaoImpl implements BaseCRUD<Paper>{
    @Override
    public int create(Paper dataClass) {
        return 0;
    }

    /**
     * Paper表删除数据
     *
     * @param key Object数组，key[0]为id
     */
    @Override
    public void delete(Object[] key) {
        String sql="delete from paper where ID=?";
        baseDao.update(sql,key);
    }


    @Override
    public void update(String[] propertyName, Object[] value) {

    }

    /**
     * Paper表查询数据
     *
     * @param propertyName 查询字段名，顺序与value一致
     * @param value 查询字段值，顺序与propertyName一致
     * @return Paper列表
     */
    @Override
    public List<Paper> readByKey(String[] propertyName, Object[] value) {
        String head="select * from Paper where ";
        if (propertyName.length<1){
            List<Paper> paperList=baseDao.query(head,value,Paper.class);
            return paperList.size()>0?paperList:null;
        }
        String sql=head;
        sql=sql+propertyName[0]+" like %";
        List<Paper> paperList=baseDao.query(sql,value,Paper.class);
        return paperList.size()>0?paperList:null;
    }

    /**
     * 随机查询数据
     * TODO : 有需求再实现
     *
     * @param num
     * @return
     */
    @Override
    public List<Paper> readRand(int num) {
        return null;
    }


}
