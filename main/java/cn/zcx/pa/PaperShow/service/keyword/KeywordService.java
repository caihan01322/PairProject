package cn.zcx.pa.PaperShow.service.keyword;

import cn.zcx.pa.PaperShow.pojo.KeywordCount;

import java.util.List;
import java.util.Map;

public interface KeywordService
{

  List<KeywordCount> getKeyCountByMap(Map<String,Object> params);   //通过map查询关键词频率



}
