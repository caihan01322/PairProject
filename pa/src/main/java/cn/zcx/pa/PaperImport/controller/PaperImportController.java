package cn.zcx.pa.PaperImport.controller;

import cn.zcx.pa.PaperImport.utils.JsonToBean_ECCV;
import cn.zcx.pa.PaperImport.utils.JsonToBean_ICCV_CVPR;
import cn.zcx.pa.PaperShow.dao.KeywordDao;
import cn.zcx.pa.PaperShow.dao.PaperDao;
import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class PaperImportController
{
  @Autowired
  PaperDao paperDao;

  @Autowired
  KeywordDao keywordDao;


  @GetMapping("/import")
  public void paperImport()
  {
    String[] fileName_CVPR_ICCV = {"D:\\DeskTop\\论文数据\\CVPR",
        "D:\\DeskTop\\论文数据\\ICCV"};
    String[] fileName_ECCV = {"D:\\DeskTop\\论文数据\\ECCV",
        "D:\\DeskTop\\论文数据\\ECCV补充"};


    for(int i = 0; i < 2; i++) {
      String fileName = fileName_CVPR_ICCV[i];
      File file = new File(fileName);
      File[] files = file.listFiles();
      for(int j = 0; j < files.length; j++) {
        String jsonpath = files[j].getAbsolutePath();
        Paper essay = JsonToBean_ICCV_CVPR.getEssay(jsonpath,i==0?"CVPR":"ICCV");

        insertPaper(essay);
        files[j].delete();
      }
    }
    for(int i = 0; i < 2; i++) {
      String fileName = fileName_ECCV[i];
      File file = new File(fileName);
      File[] files = file.listFiles();
      for(int j = 0; j < files.length; j++) {
        String jsonpath = files[j].getAbsolutePath();
        Paper essay = JsonToBean_ECCV.getEssay(jsonpath,"ECCV");

        insertPaper(essay);
        files[j].delete();
      }
    }
  }


  private void insertPaper(Paper paper)
  {
    paperDao.insertPaper(paper);  //导入论文

    //导入关键词
    for (Keyword keyword:paper.getKeywords())
    {
      if(keywordDao.isKeywordExist(keyword.getKeyword(),keyword.getConference(),keyword.getPublicationYear()))
      {

      }
      else
      {
        keywordDao.insertKeyword(keyword);
      }
    }
  }
}
