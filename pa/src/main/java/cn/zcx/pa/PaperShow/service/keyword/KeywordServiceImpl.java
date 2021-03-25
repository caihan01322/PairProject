package cn.zcx.pa.PaperShow.service.keyword;

import cn.zcx.pa.PaperShow.dao.KeywordDao;
import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KeywordServiceImpl implements KeywordService
{
  @Autowired
  private KeywordDao keywordDao;


  @Override
  public List<KeywordCount> getKeyCountByMap(Map<String, Object> params)
  {
    return keywordDao.selectKeywordCountByMap(params);
  }
}
