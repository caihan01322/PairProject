package cn.zcx.pa.PaperShow.dao;


import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;

@Mapper
public interface KeywordDao
{
  void insertKeyword(Keyword keyword);      //插入keyword

  void deleteById(String kid);           //根据id删除

  boolean isKeywordExist(String keyword,String conference,int publicationYear);   //判断关键词是否存在

//  void countIncrease(Keyword keyword);    //增加关键词频率

  void updateByObject(Keyword keyword);   //通过对象更新

  void updateByMap(Map params);       //通过map更新

//  List<Keyword> selectKeywordsOrderByCount(int size);   //查询频率前n位的keyword

  List<Keyword> selectKeywordsByMap(Map params);    //通过map查询

  Keyword selectKeywordById(String kid);       //通过id查询

}
