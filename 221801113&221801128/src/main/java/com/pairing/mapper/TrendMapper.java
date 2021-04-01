package com.pairing.mapper;

import com.pairing.bean.NameAndYear;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrendMapper {

    @Select("SELECT keywords,publicationYear FROM data")
    public List<NameAndYear> getYear();

    @Insert("insert into hotword values(2,#{json})")
    public  void insertTrend(@Param("json") String json);

    @Insert("insert into hotword values(3,#{json})")
    public  void insertTrend2(@Param("json") String json);

    @Select("select hot from hotword where id = 2")
    public String getjson1();

    @Select("select hot from hotword where id = 3")
    public String getjson2();
}
