package com.pair.dao;

import com.pair.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PaperMapper {
    public void insertPaper(Paper paper_sql);
    public List<Paper> selectAllPapers();
    public List<Paper> selectPaperByTitle(String title);
    public List<Paper> selectPaperByAbs(String abs);
    public List<Paper> selectPaperByPublisher(String publisher);
    public List<Paper> selectPaperByPublicationYear(String publicationYear);
    public List<Paper> selectPaperByKeyword(String keyword);
}
