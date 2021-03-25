package com.fzu.mapper;

import com.fzu.pojo.Keyword;
import com.fzu.pojo.Paper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PaperMapper {
    //插入论文信息
    void addPaper(Paper paper);
    //插入论文id与作者的关联关系
    void insertAuthorWithId(@Param("paperId") Integer paperId, @Param("author") String author);
    //插入论文id与关键词的关联关系
    void insertKeywordWithId(@Param("paperId")Integer paperId,@Param("keyword")String keyword);

    //查询论文信息
    List<Paper> queryPaper(@Param("start") Integer start,@Param("rows") Integer rows);
    //根据id查询论文的keywords
    List<String> queryKeywords(@Param("paperId") Integer paperId);
    //根据id查询论文的authors
    List<String> queryAuthors(@Param("paperId") Integer paperId);

    //根据关键词模糊查询论文
    List<Paper> queryPaperByKeyword(@Param("keyword")String keyword,
                                    @Param("start")Integer start,
                                    @Param("rows")Integer rows);

    //根据作者查询论文
    List<Paper> queryPaperByAuthor(@Param("author")String author,
                                    @Param("start")Integer start,
                                    @Param("rows")Integer rows);
    //统计某会议某一年前10的关键词及其数量

    List<Keyword> queryTop10ByYear(@Param("year")Integer year, @Param("meet")String meet);
}
