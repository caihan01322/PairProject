package com.pairing.mapper;

import com.pairing.bean.NameAndYear;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrendMapper {

    @Select("SELECT keywords,publicationYear FROM data")
    public List<NameAndYear> getYear();

}
