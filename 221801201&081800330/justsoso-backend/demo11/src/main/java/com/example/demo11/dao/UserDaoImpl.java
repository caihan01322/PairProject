package com.example.demo11.dao;

import com.example.demo11.model.User;
import com.example.demo11.model.imgurl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.security.util.Password;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDaoImpl implements UserJBDCDAO{
    @Resource
   private JdbcTemplate jdbcTemplate;
    @Override
    public User getUserByAccount(String account,String Password)
        {
            List<User> list= jdbcTemplate.query("select * from users where account = ? and password = ?",
                new Object[]{account, Password},new BeanPropertyRowMapper<>(User.class));
        if(list.size() != 1)
        {
            return  new User("","");
        }
        return list.get(0);
            //return jdbcTemplate.queryForObject("select * from users where account = ? and password = ?",
                    //new Object[]{account, Password},new BeanPropertyRowMapper<>(User.class));
        }
        @Override
    public boolean Register(String Account,String Password)
    {
        List<User> list = jdbcTemplate.query("select * from users where account = ?",
                new Object[]{Account},new BeanPropertyRowMapper<>(User.class));
        if(list.size() == 0)
        {
            jdbcTemplate.update("insert into users values(?,?,?,?)",
                    new Object[]{Account,Password,Account,""});
            return true;
        }
        return false;

    }
    @Override
    public int getUserByAccount(String account)
    {
        List<User> list = jdbcTemplate.query("select * from users where account = ?",
                new Object[]{account},new BeanPropertyRowMapper<>(User.class));
        return list.size();
    }
    @Override
    public String saveImg(String username,String imgUrl)
    {
        List<imgurl> list = jdbcTemplate.query("select * from img where username = ?",
                new Object[]{username},new BeanPropertyRowMapper<>(imgurl.class));
        if(list.size() == 0)
        {
            jdbcTemplate.update("insert into img values(?,?)",
                    new Object[]{username,imgUrl});
        }
        else
        {
            jdbcTemplate.update("update img set imgUrl = ? where username = ?",
                    new Object[]{imgUrl,username});
        }
        return imgUrl;
    }
    @Override
    public String getImg(String account)
    {
        List<imgurl> list = jdbcTemplate.query("select * from img where username = ?",
                new Object[]{account},new BeanPropertyRowMapper<>(imgurl.class));
        if(list.size() == 0)
        {
            return "";
        }
        else
        {
            imgurl imgurl = list.get(0);
            return imgurl.imgUrl;
        }
    }
    @Override
    public boolean changeInfo(String Account,String password,String username)
    {
        List<User> users = jdbcTemplate.query("select * from users where account = ?",
                new Object[]{Account},new BeanPropertyRowMapper<>(User.class));
        if(users.size()!= 1)
        {
            return false;
        }
        jdbcTemplate.update("update users set username = ? where account = ?",new Object[]{username,Account});
        jdbcTemplate.update("update users set password = ? where account = ?",new Object[]{password,Account});
        return true;
    }
}
