package cn.zcx.pa.PaperShow.service.keyword;

import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import cn.zcx.pa.PaperShow.pojo.YearCount;

import java.util.List;
import java.util.Map;

public interface KeywordService
{

  /**
   * 通过map查询关键词频率
   * @param params
   * @return
   */
  List<KeywordCount> getKeyCountByMap(Map<String,Object> params);

  /**
   * 通过map查询关键词频率走势
   * @param params
   * @return
   */
  List<YearCount> getYearCountByMap(Map<String,Object> params);


  /**
   * 获取频率前n位的关键词频率
   * @param size
   * @return
   */
  List<KeywordCount> getTopKeyCountBySize(int size);
}
