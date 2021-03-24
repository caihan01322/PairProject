package com.pair.dao;

import com.pair.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PaperMapper {
    public void insertPaper(Paper paper_sql);

    public List<Paper> selectAllPapers();

    public List<Paper> selectPaperByFuzzyMode(Map<String, String> map);//模糊查询

    public List<Paper> selectPaperByPreciseMode(Map<String, String> map);//精确查询

    public void deletePaperByPid(String pid);

    public Paper getPapersByPid(String pid);

    public int getPaperNum(Map<String, String> map);
}
