package com.example.dao;

import com.example.model.ConferenceKwd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KeywordTrendDao {

    @Select("SELECT * FROM cvprkwd WHERE keyword = #{keyword}")
    List<ConferenceKwd> getCvprKwd(String keyword);

    @Select("SELECT * FROM eccvkwd WHERE keyword = #{keyword}")
    List<ConferenceKwd> getEccvKwd(String keyword);

    @Select("SELECT * FROM iccvkwd WHERE keyword = #{keyword}")
    List<ConferenceKwd> getIccvKwd(String keyword);

    @Select("SELECT year FROM cvpr WHERE number = #{number}")
    List<String> getCvprYear(int number);

    @Select("SELECT year FROM eccv WHERE number = #{number}")
    List<String> getEccvYear(int number);

    @Select("SELECT year FROM iccv WHERE number = #{number}")
    List<String> getIccvYear(int number);
}
