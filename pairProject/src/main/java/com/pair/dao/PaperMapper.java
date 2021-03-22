package com.pair.dao;

import com.pair.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperMapper {
    public void insertPaper(Paper paper_sql);
    public List<Paper> selectAllPapers();
}
