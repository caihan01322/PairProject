package cn.zcx.pa.PaperImport.service;

import cn.zcx.pa.PaperImport.utils.JsonToBean_ECCV;
import cn.zcx.pa.PaperImport.utils.JsonToBean_ICCV_CVPR;
import cn.zcx.pa.PaperShow.dao.KeywordDao;
import cn.zcx.pa.PaperShow.dao.PaperDao;
import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PaperImportServiceImpl implements PaperImportService
{

  @Autowired
  private PaperDao paperDao;

  @Autowired
  private KeywordDao keywordDao;

  @Override
  public void insertPaper(Paper paper)
  {
    paperDao.insertPaper(paper);  //导入论文

    //导入关键词
    for (String kwd:paper.getKeywords())
    {
      keywordDao.insertKeyword(new Keyword(paper.getPid(),kwd,paper.getConference(),paper.getPublicationYear()));
    }
  }

  @Override
  public void importPaper(String[] ICCV_CVPR, String[] ECCV)
  {
    for(int i = 0; i < ICCV_CVPR.length; i++)
    {
      String fileName = ICCV_CVPR[i];
      File file = new File(fileName);
      File[] files = file.listFiles();

      for(int j = 0; j < files.length; j++)
      {
        String jsonpath = files[j].getAbsolutePath();
        Paper essay = JsonToBean_ICCV_CVPR.getEssay(jsonpath,i==0?"CVPR":"ICCV");
        insertPaper(essay);
      }
    }

    for(int i = 0; i < ECCV.length; i++)
    {
      String fileName = ECCV[i];
      File file = new File(fileName);
      File[] files = file.listFiles();

      for(int j = 0; j < files.length; j++)
      {
        String jsonpath = files[j].getAbsolutePath();
        Paper essay = JsonToBean_ECCV.getEssay(jsonpath,"ECCV");
        insertPaper(essay);
      }
    }
  }
}
