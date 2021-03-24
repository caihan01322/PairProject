package com.fzu.server.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    String getName(int id);
}
