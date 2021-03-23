package com.pair.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
public interface PaperKeywordMapper {
    public void insertPK(String pid,String kid);
}
