package com.example.demo11.dao;

import com.example.demo11.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;


public interface UserJBDCDAO
{
    User getUserByAccount(String account,String Password);
    boolean Register(String Account,String Password);
    int getUserByAccount(String account);
    String saveImg(String Account,String imgUrl);
    String getImg(String account);
}
