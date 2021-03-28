package com.example.demo11.dao;

import com.example.demo11.model.*;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ArticalDaoImpl implements ArticleJDBCDAO{
    @Resource
   private JdbcTemplate jdbcTemplate;
    @Override
    public List<Artical> searchArtical(String keywors)
    {
        List<Artical> list = jdbcTemplate.query("select * from academics where " +
                "title like ? or Abstract like ?",new Object[]{"%"+keywors+"%","%"+keywors+"%"},new BeanPropertyRowMapper<>(Artical.class));

        return list;
    }
    @Override
    public List<keywords> searchKeywords(int academicNum)
    {
        return jdbcTemplate.query("select keyword from keywords where " +
                "academicNum = ?",new Object[]{academicNum},new BeanPropertyRowMapper<>(keywords.class));
    }
    @Override
    public List<authors> searchAuthors(int academicNum)
    {
        return jdbcTemplate.query("select author from authors where " +
                "academicNum = ?",new Object[]{academicNum},new BeanPropertyRowMapper<>(authors.class));
    }
    @Override
    public int Collect(String username,int [] academicNum)
    {
        List<User> users = jdbcTemplate.query("select * from users where account = ?",
                new Object[]{username},new BeanPropertyRowMapper<>(User.class));
        if(users.size() != 1)
        {
            return 0;
        }
        String sql = "";
        int count = 0;
        for (int Num :academicNum
             ) {
            List<favorite> favorites = jdbcTemplate.query("select * from favorites where " +
                    "academicNum = ? and userName = ?",new Object[]{Num,username},
                    new BeanPropertyRowMapper<>(favorite.class));
            if(favorites.size() != 0)
            {
                continue;
            }
            sql = String.format("insert into favorites values(%d,\"%s\");",Num,username);
            System.out.println(sql);
            count++;
            jdbcTemplate.update(sql);
        }

        return count;
    }
    @Override
    public List<Artical> getCollection(String username)
    {
        List<Artical> Articals = new ArrayList<Artical>();
        List<favorite> list = jdbcTemplate.query("select * from favorites where username = ?",
                new Object[]{username},new BeanPropertyRowMapper<>(favorite.class));
        for (favorite favor:list
        ) {
            Artical artical = jdbcTemplate.queryForObject("select * from academics where academicNum = ?",
                    new Object[]{favor.academicNum},new BeanPropertyRowMapper<>(Artical.class));
            Articals.add(artical);
        }
        return Articals;
    }
    @Override
    public Set<String> getAllkeywords()
    {
        Set<String> allKeys = new HashSet<String>();
        List<keywords> keys = jdbcTemplate.query("select keyword from keywords",new Object[]{},new BeanPropertyRowMapper<>(keywords.class));
        for (keywords key:keys
             ) {
            allKeys.add(key.keyword);
            int count = jdbcTemplate.queryForObject("select  count(*) from keywords where keyword = ?",
                    new Object[]{key},new BeanPropertyRowMapper<>(int.class));
            System.out.println(count);
            jdbcTemplate.update("insert into hotkey values(?,?)",new Object[]{key,count});
        }

        return allKeys;
    }
    @Override
    public int deleteCollections(String username,int []academicNum)
    {
        int count = 0;
        for (Integer academicnum :
                academicNum)    {
            List<favorite> favorites = jdbcTemplate.query("select * from favorites where academicNum = ? and userName = ?",
                    new Object[]{academicnum,username},new BeanPropertyRowMapper<>(favorite.class));
            if(favorites.size() == 0)
            {
                continue;
            }
            jdbcTemplate.update("delete from favorites where username = ? and academicNum = ?",
                    new Object[]{username,academicnum});
            count++;
        }
        return count;
    }
    @Override
    public List<hotkey> top20()
    {
        List<hotkey> hotkeys = jdbcTemplate.query("select * from hotkey where Number > 100 order by Number desc",new BeanPropertyRowMapper<>(hotkey.class));
        List<hotkey> keys = hotkeys.subList(0,20);
        return keys;
    }


}
