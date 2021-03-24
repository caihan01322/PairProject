package cn.zcx.pa.PaperShow.dao;

import cn.zcx.pa.PaperShow.pojo.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class KeywordDaoTest
{
  @Autowired
  KeywordDao keywordDao;



  @Test
  void deleteById()           //根据id删除
  {
    keywordDao.deleteById("1616324884999862");
  }

//  @Test
//  void updateByObject()   //通过对象更新
//  {
//    keywordDao.updateByObject(new Keyword("1616324885407649", "udbyobject", "udbyobject"));
//  }

  @Test
  void updateByMap()
  {
    Map<String,Object> params=new HashMap<>();
    params.put("kid","1616324885422163");
    params.put("keyword","udbymap");
    params.put("conference","udbymap");
    params.put("count",2077);

    keywordDao.updateByMap(params);
  }

//  @Test
//  void selectKeywordsOrderByCount()
//  {
//    List<Keyword> keywords= keywordDao.selectKeywordsOrderByCount(10);
//    for (Keyword keyword:keywords)
//    {
//      System.out.println(keyword.toString());
//    }
//
//  }


  @Test
  void selectKeywordsByMap()    //通过map查询
  {
    Map<String,Object> params=new HashMap<>();
    params.put("kid","1616324885422163");
    params.put("keyword","udbymap");
    params.put("conference","udbymap");
    params.put("count",2077);

    List<Keyword> keywords=keywordDao.selectKeywordsByMap(params);
    for (Keyword keyword:keywords)
    {
      System.out.println(keyword.toString());
    }
  }

//  @Test
//  void isKeywordExist()
//  {
//    System.out.println(keywordDao.isKeywordExist("11111", "sssss"));
//  }

  @Test
  void countIncrease()
  {

  }
//
//  @Test
//  void Test()
//  {
////    insertKeyword();
//    deleteById();
//    updateByObject();
//    updateByMap();
//    selectKeywordsByMap();
//  }


}
