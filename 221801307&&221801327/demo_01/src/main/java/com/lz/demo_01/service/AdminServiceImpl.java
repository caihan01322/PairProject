package com.lz.demo_01.service;

import com.lz.demo_01.dao.AdminDao;
import com.lz.demo_01.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    //查询管理员用户实现
    public List<Admin> findAdmin(Admin admin) {
        return adminDao.findAdmin(admin);

    }

    //修改密码
    public int changePwd(Admin admin2) {
        return adminDao.changePwd(admin2);
    }
}
