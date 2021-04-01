package cn.zcx.pa.PaperShow.dao;

import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface PaperDao
{
  void insertPaper(Paper paper);      //插入paper

  void deleteById(String pid);           //根据id删除

  void updateByObject(Paper paper);   //通过对象更新

  void updateByMap(Map params);       //通过map更新

  List<Paper> selectPapersByMap(Map params);    //通过map查询

  List<String> selectPidlistByMap(Map params);    //通过map查询pid列表

  Paper selectPaperById(String pid);        //通过id查询

  List<Paper> selectPapersByKeyword(String keyword);  //通过keyword查询论文列表


}
