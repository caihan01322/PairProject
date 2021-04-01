package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RegisterMapper
{
    void addUser(String username,String password);
    int getUserByName(String name);
}
