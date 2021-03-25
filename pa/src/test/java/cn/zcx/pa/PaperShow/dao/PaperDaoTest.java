package cn.zcx.pa.PaperShow.dao;

import cn.zcx.pa.PaperShow.pojo.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class PaperDaoTest
{
  @Autowired
  PaperDao paperDao;

  @Test
  void insertPaper()      //插入paper
  {
    for(int i=0;i<20;i++)
    {
      paperDao.insertPaper(new Paper(i+"title", i+"abst",
          "conference",i+2000,i+"link"));
    }

  }

  @Test
  void deleteById()           //根据id删除
  {

    paperDao.deleteById("ee2d6b41-9b65-4284-ba03-74937656d76b");
  }

  @Test
  void updateByObject()   //通过对象更新
  {
    paperDao.updateByObject(new Paper("03da3783-ae5e-4363-a927-bf4d8a4c6049","udbyobject", "udbyobject",
       "udbyobject", 2077, "WWW.BAIDU.COM",null));
  }

  @Test
  void updateByMap()
  {
    Map<String,Object> params=new HashMap<>();
    params.put("pid","40b0af13-1913-4921-b21a-81b58e792fb2");
    params.put("title","udbymap");
    params.put("abst","udbymap");
    params.put("conference","udbymap");
    params.put("publicationYear",2077);
    params.put("link","udbymap");

    paperDao.updateByMap(params);

  }

  @Test
  void selectPapersByMapExactly()
  {
    Map<String,Object> params=new HashMap<>();
//    params.put("pid","40b0af13-1913-4921-b21a-81b58e792fb2");
//    params.put("title","udbymap");
//    params.put("abst","udbymap");
//    params.put("conference","udbymap");
//    params.put("publicationYear",2077);
//    params.put("link","udbymap");

    List<Paper> papers=paperDao.selectPapersByMapExactly(params);

    for (Paper paper:papers)
    {
      System.out.println(paper.toString());
    }
  }

  @Test
  void selectPapersByMapVaguely()
  {
    Map<String,Object> params=new HashMap<>();
    params.put("pid","40b0af13-1913-4921-b21a-81b58e792fb2");
    params.put("title","title");
//    params.put("title","byma");
//    params.put("abst","byma");
//    params.put("conference","udbymap");
//    params.put("beginYear",2000);
//    params.put("endYear",2100);
//    params.put("link","byma");

    List<Paper> papers=paperDao.selectPapersByMapVaguely(params);

    for (Paper paper:papers)
    {
      System.out.println(paper.toString());
    }
  }

  @Test
  void selectPapersByKeyword()
  {
    List<Paper> papers=paperDao.selectPapersByKeyword("5keyword");

    for (Paper paper:papers)
    {
      System.out.println(paper.toString());
    }
  }

  @Test
  void selectPapersByPid()
  {
      System.out.println(paperDao.selectPaperById("14dbf809-2b90-4a75-b23b-1eb47fe6ac12"));
  }


  @Test
  void Test()
  {
    insertPaper();
    deleteById();
    updateByObject();
    updateByMap();
    selectPapersByMapExactly();
    selectPapersByMapVaguely();
    selectPapersByKeyword();
  }



}
