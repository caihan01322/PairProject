package com.example.dao;

import com.example.model.ConferenceKwd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HotWordFrequencyDao {
    @Select("SELECT * FROM cvprkwd")
    List<ConferenceKwd> getCvprKwd();

    @Select("SELECT * FROM eccvkwd")
    List<ConferenceKwd> getEccvKwd();

    @Select("SELECT * FROM iccvkwd")
    List<ConferenceKwd> getIccvKwd();
}
