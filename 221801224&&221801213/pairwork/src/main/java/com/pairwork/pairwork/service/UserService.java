package com.pairwork.pairwork.service;


import com.pairwork.pairwork.dao.UserDao;
import com.pairwork.pairwork.entity.Paper;
import com.pairwork.pairwork.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service//变成Spring 容器中的一个bean
public class UserService {
    @Resource//获得将其他部分资源引入
    private UserDao userDao;

    public void add(User user){//添加用户 => 注册
        userDao.save(user);
    }

    public User findById(Long id){//根据id查不到则返回null
        return  userDao.findById(id).orElse(null);
    }

    public boolean findByAccount(String account){//根据account查询用户，查不到则返回false
        return userDao.findAccount(account);//在dao中自定义函数
    }

    public Page<Paper> findCollection(Integer pageNum,Integer pageSize,Long id){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");//倒序
        Pageable pageable = PageRequest.of(pageNum - 1,pageSize,sort);
        return userDao.findNameLike(id,pageable);
    }
}
