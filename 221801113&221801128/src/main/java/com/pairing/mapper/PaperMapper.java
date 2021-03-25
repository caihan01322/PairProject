package com.pairing.mapper;

import com.pairing.bean.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaperMapper {

    @Select("select * from data where keywords like concat('%', #{searchInfo}, '%') " +
            "or publicationTitle like concat('%', #{searchInfo}, '%') limit #{begin},4")
    public List<Paper> getPaper(@Param(value = "searchInfo") String searchInfo, @Param(value = "begin") int begin);

    @Select("select count(*) from data where keywords like concat('%', #{searchInfo}, '%') " +
            "or publicationTitle like concat('%', #{searchInfo}, '%')")
    public int getPaperCount(@Param(value = "searchInfo") String searchInfo);
}
