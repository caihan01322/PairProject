package cn.zcx.pa;

import cn.zcx.pa.PaperShow.dao.KeywordDao;
import cn.zcx.pa.PaperShow.dao.PaperDao;
import cn.zcx.pa.PaperShow.utils.MyUUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

//    System.out.println(keywordDao.isKeywordExist("1", "1", 31));
  }
}
