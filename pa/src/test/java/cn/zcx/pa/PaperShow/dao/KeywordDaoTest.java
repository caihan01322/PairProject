package cn.zcx.pa.PaperShow.dao;

import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
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
  void insertKeyword()      //插入keyword
  {
    for(int i=0;i<20;i++)
    {
      keywordDao.insertKeyword(new Keyword("14dbf809-2b90-4a75-b23b-1eb47fe6ac12",
          i+"keyword","conference",i+2000));
    }

  }

  @Test
  void deleteById()           //根据id删除
  {
    keywordDao.deleteById("09a8a91a-b467-4fda-b2cc-6a2cf395a004");
  }

  @Test
  void updateByObject()   //通过对象更新
  {
    keywordDao.updateByObject(new Keyword("183fee45-a0a8-40cd-80e2-7f2bcbf68db4",
        "14dbf809-2b90-4a75-b23b-1eb47fe6ac12",
        "udbyobject", "udbyobject",2077));
  }

  @Test
  void updateByMap()
  {
    Map<String,Object> params=new HashMap<>();
    params.put("kid","6d4c9477-5804-4e69-8597-2d8103a34712");
    params.put("pid","978bcbfb-fea7-47af-9802-8db65bb6808f");
    params.put("keyword","udbymap");
    params.put("conference","udbymap");
    params.put("publicationYear",2077);

    keywordDao.updateByMap(params);
  }



  @Test
  void selectKeywordsByMap()    //通过map查询
  {
    Map<String,Object> params=new HashMap<>();
    params.put("kid","6d4c9477-5804-4e69-8597-2d8103a34712");
    params.put("pid","978bcbfb-fea7-47af-9802-8db65bb6808f");
    params.put("keyword","udbymap");
    params.put("conference","udbymap");
    params.put("publicationYear",2077);

    List<String> keywords=keywordDao.selectKeywordsByMap(params);
    for (String keyword:keywords)
    {
      System.out.println(keyword);
    }
  }



  @Test
  void Test()
  {
//    insertKeyword();
    deleteById();
    updateByObject();
    updateByMap();
    selectKeywordsByMap();
  }


}
