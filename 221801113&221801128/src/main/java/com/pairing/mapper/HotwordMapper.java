package com.pairing.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotwordMapper {

    @Select("select keywords from data")
    public List<String> getAllWord();

    @Insert("insert into hotword values(1,#{json})")
    public  void insertHotword(@Param("json") String json);

    @Select("select hot from hotword where id = 1")
    public String getHotwordjson();
}
