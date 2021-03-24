package com.topwordanalysis.databaseOperation.dao;

import com.topwordanalysis.databaseOperation.model.User;

import java.util.List;

/**
 * UserDaoImpl
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class UserDaoImpl implements BaseCRUD<User> {
    @Override
    public int create(User dataClass) {
        String sql="insert into User(mail,password) values(?,?)";
        Object[] paramsValue={dataClass.getMail(),
                dataClass.getPassword(),};
        baseDao.update(sql,paramsValue);
        return 0;
    }

    @Override
    public void delete(Object[] key) {

    }

    @Override
    public void update(String[] propertyName, Object[] value) {

    }

    /**
     * User表查询数据
     *
     * @param propertyName 无用，为null
     * @param value Object数组，value[0]为email
     * @return User列表
     */
    @Override
    public List<User> readByKey(String[] propertyName, Object[] value) {
        String sql="select * from User where email=?";
        List<User> usersList=baseDao.query(sql,value,User.class);
        return usersList.size()>0?usersList:null;
    }

    @Override
    public List<User> readRand(int num) {
        return null;
    }
}
