package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper
{
    List<User> getUserByName(String username);
    String getPassword(String username);
}
