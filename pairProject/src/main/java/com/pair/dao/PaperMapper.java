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

    public List<Paper> selectPaperByFuzzyMode(Map<String, Object> map);//模糊查询

    public List<Paper> selectPaperByPreciseMode(Map<String, Object> map);//精确查询

    public void deletePaperByPid(String pid);

    public Paper getPapersByPid(String pid);

    public int getPaperNum(Map<String, String> map);

    public List<Paper> getPapersByKeyword(String keyword);

    //根据pid查找文章完整信息
    public Paper selectPaperByPid(String pid);

    //模糊查询 标题、摘要、会议名称、发布年份
    public List<Paper> getPidByFuzzyModeByPaperInfo(Map<String,String> map);

    //精准查询 标题、摘要、会议名称、发布年份
    public List<Paper> selectPaperByPreciseModeByPaperInfo(Map<String, String> map);

}
