package cn.zcx.pa;

import cn.zcx.pa.PaperImport.utils.JsonToBean_ECCV;
import cn.zcx.pa.PaperImport.utils.JsonToBean_ICCV_CVPR;
import cn.zcx.pa.PaperShow.dao.KeywordDao;
import cn.zcx.pa.PaperShow.dao.PaperDao;
import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import cn.zcx.pa.PaperShow.utils.MyUUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class PaApplicationTests
{
  @Autowired
  PaperDao paperDao;

  @Autowired
  KeywordDao keywordDao;



  @Test
  void contextLoads()
  {
  }
}
