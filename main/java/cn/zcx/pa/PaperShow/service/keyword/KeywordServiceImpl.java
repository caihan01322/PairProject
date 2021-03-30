package cn.zcx.pa.PaperShow.service.keyword;

import cn.zcx.pa.PaperShow.dao.KeywordDao;
import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import cn.zcx.pa.PaperShow.pojo.YearCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

  @Override
  public List<YearCount> getYearCountByMap(Map<String, Object> params)
  {
    return keywordDao.selectYearCountByMap(params);
  }

  @Override
  public List<KeywordCount> getTopKeyCountBySize(int size)
  {
    Map<String,Object> params=new HashMap<>();
    params.put("size",10);
    return keywordDao.selectKeywordCountByMap(params);
  }
}
