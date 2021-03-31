package com.topwordanalysis.databaseOperation.dao;

import java.util.List;

/**
 * 数据库操作接口
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public interface BaseCRUD<T> {
    BaseDao baseDao=new BaseDaoImpl();

    //增加数据接口
    public int create(T dataClass);
    //删除数据接口
    public void delete(Object[] key);
    //修改数据接口
    public void update(String[] propertyName,Object[] value);
    //根据条件查询(主键)
    public List<T> readByKey(String[] propertyName, Object[] value);
    //随机查询多条不重复数据
    public List<T> readRand(int num);
}
