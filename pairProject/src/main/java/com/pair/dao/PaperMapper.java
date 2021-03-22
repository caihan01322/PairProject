package com.pair.dao;

import com.pair.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaperMapper {
    public void insertPaper(Paper paper_sql);
}
