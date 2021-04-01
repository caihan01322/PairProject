package cn.zcx.pa.PaperImport.controller;

import cn.zcx.pa.PaperImport.service.PaperImportService;
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
  PaperImportService paperImportService;


  //导入
  @GetMapping("/import")
  public void paperImport()
  {
    String[] ICCV_CVPR = {"D:\\DeskTop\\论文数据\\CVPR",
        "D:\\DeskTop\\论文数据\\ICCV"};
    String[] ECCV = {"D:\\DeskTop\\论文数据\\ECCV",
        "D:\\DeskTop\\论文数据\\ECCV补充"};

    //导入论文数据
    paperImportService.importPaper(ICCV_CVPR,ECCV);
  }
}
