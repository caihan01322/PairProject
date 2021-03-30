package cn.zcx.pa.PaperShow.service.echarts;

import cn.zcx.pa.PaperShow.pojo.YearCount;

import java.util.List;
import java.util.Map;

public interface EchartsService
{
  /**
   * 根据查询条件获取对应年份、会议的top10关键词
   * @param beginYear
   * @param endYear
   * @param conference
   * @return
   */
  String getTop10Json(int beginYear,int endYear,String conference);

  /**
   * 根据查询条件获取对应关键词的频率走势
   * @param beginYear
   * @param endYear
   * @param keyword
   * @return
   */
  String getYearCountJson(int beginYear,int endYear,String keyword);

  /**
   * 根据会议名等查询条件获取关键词频率走势
   * @param conference
   * @param params
   * @return
   */
  List<YearCount> getYearCountByConference(String conference, Map<String,Object> params);
}
