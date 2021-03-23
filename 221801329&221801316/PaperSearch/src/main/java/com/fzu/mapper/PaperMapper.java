package com.fzu.mapper;

import com.fzu.pojo.Paper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperMapper {
    //插入论文信息
    void addPaper(Paper paper);
    //插入论文id与作者的关联关系
    void insertAuthorWithId(@Param("paperId") Integer paperId, @Param("author") String author);
    //插入论文id与关键词的关联关系
    void insertKeywordWithId(@Param("paperId")Integer paperId,@Param("keyword")String keyword);
}
