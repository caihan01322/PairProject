package com.lz.demo_01.dao;

import com.lz.demo_01.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
@Mapper
public interface AdminDao {
    //查找管理员信息
    List<Admin> findAdmin(Admin admin);
    //修改密码
    int changePwd(Admin admin2);
}
