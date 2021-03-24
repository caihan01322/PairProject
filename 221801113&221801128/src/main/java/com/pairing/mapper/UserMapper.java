package com.pairing.mapper;

import com.pairing.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from User where username=#{userName}")
    public User getUserById(String userName);
}
