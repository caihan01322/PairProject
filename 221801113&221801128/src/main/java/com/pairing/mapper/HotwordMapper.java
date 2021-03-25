package com.pairing.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotwordMapper {

    @Select("select keywords from data")
    public List<String> getAllWord();

}
