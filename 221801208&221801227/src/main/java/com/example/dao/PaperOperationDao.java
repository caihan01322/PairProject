package com.example.dao;

import com.example.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperOperationDao {

    @Select("SELECT * FROM cvpr WHERE title LIKE '%${_parameter}%'")
    List<Conference> getCvpr(String _parameter);

    @Select("SELECT * FROM eccv WHERE title LIKE '%${_parameter}%'")
    List<Conference> getEccv(String _parameter);

    @Select("SELECT * FROM iccv WHERE title LIKE '%${_parameter}%'")
    List<Conference> getIccv(String _parameter);

    @Select("SELECT * FROM cvpr WHERE number = #{number}")
    List<Conference> getCvprBasedOnNumber(int number);

    @Select("SELECT * FROM eccv WHERE number = #{number}")
    List<Conference> getEccvBasedOnNumber(int number);

    @Select("SELECT * FROM iccv WHERE number = #{number}")
    List<Conference> getIccvBasedOnNumber(int number);

    @Select("SELECT * FROM cvprkwd WHERE number = #{number}")
    List<ConferenceKwd> getCvprKwd(int number);

    @Select("SELECT * FROM eccvkwd WHERE number = #{number}")
    List<ConferenceKwd> getEccvKwd(int number);

    @Select("SELECT * FROM iccvkwd WHERE number = #{number}")
    List<ConferenceKwd> getIccvKwd(int number);

    @Select("SELECT * FROM cvprkwd WHERE keyword = #{keyword}")
    List<ConferenceKwd> getCvprKwdBasedOnKwd(String keyword);

    @Select("SELECT * FROM eccvkwd WHERE keyword = #{keyword}")
    List<ConferenceKwd> getEccvKwdBasedOnKwd(String keyword);

    @Select("SELECT * FROM iccvkwd WHERE keyword = #{keyword}")
    List<ConferenceKwd> getIccvKwdBasedOnKwd(String keyword);

    @Delete("DELETE FROM cvpr WHERE number = #{number}")
    void deleteFromCvpr(int number);

    @Delete("DELETE FROM eccv WHERE number = #{number}")
    void deleteFromEccv(int number);

    @Delete("DELETE FROM iccv WHERE number = #{number}")
    void deleteFromIccv(int number);

    @Insert("insert into collection values(#{title},#{number},#{paperabstract},#{link},#{year})")
    void insertCollection(String title,int number,String paperabstract,String link,String year);

    @Insert("INSERT INTO collectionkwd VALUES(#{number},#{keyword})")
    void insertCollectionKwd(int number,String keyword);

    @Delete("DELETE FROM collection WHERE number = #{number}")
    void deleteCollection(int number);

    @Delete("DELETE FROM collectionkwd WHERE number = #{number}")
    void deleteCollectionKwd(int number);
}
