package cn.zcx.pa.PaperShow.service.echarts;

import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import cn.zcx.pa.PaperShow.service.keyword.KeywordService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EchartsServiceImpl implements EchartsService
{
  @Autowired
  private KeywordService keywordService;


  @Override
  public String getTop10Json(Map<String, Object> params)
  {
    JSONObject jsonObject=new JSONObject();
    List<String> x=new ArrayList<>();
    List<Integer> y=new ArrayList<>();

    params.put("size",10);    //取前10位
    List<KeywordCount> list = keywordService.getKeyCountByMap(params);  //查询

    //转换成echarts需要的json格式
    for (KeywordCount kc:list)
    {
      x.add(kc.getKeyword());
      y.add(kc.getCount());
    }
    jsonObject.put("x",x);
    jsonObject.put("y",y);
    return jsonObject.toJSONString();
  }



}
