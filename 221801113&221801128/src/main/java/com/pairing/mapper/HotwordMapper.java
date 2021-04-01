package com.pairing.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotwordMapper {

    @Select("select keywords from data where typepaper = 'ECCV'")
    public List<String> getAllWord();

    @Insert("insert into hotword values(111,#{json})")
    public  void insertHotword(@Param("json") String json);

    @Select("select hot from hotword where id = #{type}")
    public String getHotwordjson(@Param("type") String type);
}
