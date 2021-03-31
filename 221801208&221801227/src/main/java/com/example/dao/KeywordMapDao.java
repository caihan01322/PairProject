package com.example.dao;

import com.example.model.Conference;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KeywordMapDao {

    @Select("SELECT * FROM cvpr WHERE number = #{number}")
    List<Conference> getCvpr(int number);

    @Select("SELECT * FROM eccv WHERE number = #{number}")
    List<Conference> getEccv(int number);

    @Select("SELECT * FROM iccv WHERE number = #{number}")
    List<Conference> getIccv(int number);

    @Select("SELECT number FROM cvprkwd WHERE keyword = #{keyword}")
    List<Integer> getCvprId(String keyword);

    @Select("SELECT number FROM eccvkwd WHERE keyword = #{keyword}")
    List<Integer> getEccvId(String keyword);

    @Select("SELECT number FROM iccvkwd WHERE keyword = #{keyword}")
    List<Integer> getIccvId(String keyword);

}
