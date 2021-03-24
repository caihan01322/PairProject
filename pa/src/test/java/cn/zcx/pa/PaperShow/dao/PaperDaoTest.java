//package cn.zcx.pa.PaperShow.dao;
//
//import cn.zcx.pa.PaperShow.pojo.Paper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//public class PaperDaoTest
//{
//  @Autowired
//  PaperDao paperDao;
//
//  @Test
//  void insertPaper()      //插入paper
//  {
//    for(int i=0;i<20;i++)
//    {
//      paperDao.insertPaper(new Paper(i+"title", i+"abst",
//          i+"conference",i+2000,i+"link",null));
//    }
//
//  }
//
//  @Test
//  void deleteById()           //根据id删除
//  {
//    paperDao.deleteById("1616322560193");
//  }
//
//  @Test
//  void updateByObject()   //通过对象更新
//  {
//    paperDao.updateByObject(new Paper("1616322560659","udbyobject", "udbyobject",
//       "udbyobject", 2077, "WWW.BAIDU.COM",null));
//  }
//
//  @Test
//  void updateByMap()
//  {
//    Map<String,Object> params=new HashMap<>();
//    params.put("pid","1616322560706");
//    params.put("title","udbymap");
//    params.put("abst","udbymap");
//    params.put("conference","udbymap");
//    params.put("publicationYear",2077);
//    params.put("link","udbymap");
//
//    paperDao.updateByMap(params);
//
//  }
//
//  @Test
//  void selectPapersByMapExactly()
//  {
//    Map<String,Object> params=new HashMap<>();
//    params.put("pid","1616322560706");
//    params.put("title","udbymap");
//    params.put("abst","udbymap");
//    params.put("conference","udbymap");
//    params.put("publicationYear",2077);
//    params.put("link","udbymap");
//
//    List<Paper> papers=paperDao.selectPapersByMapExactly(params);
//
//    for (Paper paper:papers)
//    {
//      System.out.println(paper.toString());
//    }
//  }
//
//  @Test
//  void selectPapersByMapVaguely()
//  {
//    Map<String,Object> params=new HashMap<>();
//    params.put("pid","1616322560706");
//    params.put("title","udbymap");
//    params.put("abst","udbymap");
//    params.put("conference","udbymap");
//    params.put("publicationYear",2077);
//    params.put("link","udbymap");
//
//    List<Paper> papers=paperDao.selectPapersByMapVaguely(params);
//
//    for (Paper paper:papers)
//    {
//      System.out.println(paper.toString());
//    }
//  }
//
//  @Test
//  void selectPapersByKid()
//  {
//    List<Paper> papers=paperDao.selectPapersByKid("1321321");
//
//    for (Paper paper:papers)
//    {
//      System.out.println(paper.toString());
//    }
//  }
//
//
//  @Test
//  void Test()
//  {
//    insertPaper();
//    deleteById();
//    updateByObject();
//    updateByMap();
//    selectPapersByMapExactly();
//    selectPapersByMapVaguely();
//    selectPapersByKid();
//  }
//
//
//
//}
