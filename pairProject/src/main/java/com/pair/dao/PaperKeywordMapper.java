package com.pair.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaperKeywordMapper {
    public void insertPK(String pid, String kid);

    public void deletePKByPid(String pid);
}
