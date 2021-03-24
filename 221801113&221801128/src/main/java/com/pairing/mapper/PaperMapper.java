package com.pairing.mapper;

import com.pairing.bean.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Select("select * from data limit #{begin},4")
    public List<Paper> getPaperByPageNum(int begin);

    @Select("select count(*) from data")
    public int getTotalNum();
}
