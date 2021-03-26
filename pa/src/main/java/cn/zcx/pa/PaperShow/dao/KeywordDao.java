package cn.zcx.pa.PaperShow.dao;


import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import cn.zcx.pa.PaperShow.pojo.Paper;
import cn.zcx.pa.PaperShow.pojo.YearCount;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface KeywordDao
{
  void insertKeyword(Keyword keyword);      //插入keyword

  void deleteById(String kid);           //根据id删除

  void updateByObject(Keyword keyword);   //通过对象更新

  void updateByMap(Map params);       //通过map更新

  List<KeywordCount> selectKeywordCountByMap(Map params);   //通过map查询频率前n位的keyword

  List<YearCount> selectYearCountByMap(Map parmas);   //通过map查询关键词的历年统计

  List<String> selectKeywordsByMap(Map params);    //通过map查询

  Keyword selectKeywordById(String kid);       //通过id查询

}
