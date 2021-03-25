package cn.zcx.pa.PaperShow.service.echarts;

import java.util.Map;

public interface EchartsService
{
  /**
   * 根据查询条件获取对应年份、会议的top10关键词
   * @param params
   * @return
   */
  String getTop10Json(Map<String,Object> params);
}
