package com.pair.dao;

import com.pair.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PaperMapper {
    public void insertPaper(Paper paper_sql);

    public List<Paper> selectAllPapers();

    public List<Paper> selectPaperListWithoutKeywords();


    public void deletePaperByPid(String pid);

    public Paper getPapersByPid(String pid);

    public int getPaperNum(Map<String, String> map);

    public List<Paper> getPapersByKeyword(String keyword);


    public List<String> getPaperIdByFuzzyMode(Map<String, Object> map);
    public List<String> getPaperIdByPreciseMode(Map<String, Object> map);
    public Paper getPaperById(String pid);
}
