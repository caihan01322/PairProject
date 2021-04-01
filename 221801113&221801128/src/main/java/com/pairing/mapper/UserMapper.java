package com.pairing.mapper;

import com.pairing.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{userName}")
    public User isMember(String userName);

    @Insert("insert into user(username,password) values(#{userName},#{password})")
    public Integer register(@Param("userName") String userName, @Param("password") String password);
}
