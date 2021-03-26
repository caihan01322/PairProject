package cn.zcx.pa.PaperShow.service.echarts;

import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import cn.zcx.pa.PaperShow.pojo.YearCount;
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
  public String getTop10Json(int beginYear,int endYear,String conference)
  {

    JSONObject jsonObject=new JSONObject();
    List<String> x=new ArrayList<>();
    List<Integer> y=new ArrayList<>();

    Map<String,Object> params=new HashMap<>();
    params.put("beginYear",beginYear);
    params.put("endYear",endYear);
    params.put("conference",conference);
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

  @Override
  public String getYearCountJson(int beginYear,int endYear,String keyword)
  {
    JSONObject jsonObject=new JSONObject();
    List<Integer> x=new ArrayList<>();
    List<Integer> CVPR=new ArrayList<>();
    List<Integer> ICCV=new ArrayList<>();
    List<Integer> ECCV=new ArrayList<>();
    
    Map<String,Object> params=new HashMap<>();
    params.put("beginYear",beginYear);
    params.put("endYear",endYear);
    params.put("keyword",keyword);

    List<YearCount> cvprs=getYearCountByConference("CVPR",params);
    List<YearCount> iccvs=getYearCountByConference("ICCV",params);
    List<YearCount> eccvs=getYearCountByConference("ECCV",params);

    for(int i=beginYear,cv=0,ec=0,ic=0;i<=endYear;i++)
    {
      x.add(i);
      if(cv<cvprs.size())
      {
        CVPR.add(cvprs.get(cv).getYear()==i?cvprs.get(cv++).getCount():0);
      }
      else
      {
        CVPR.add(0);
      }
      if(ec<eccvs.size())
      {
        ECCV.add(eccvs.get(ec).getYear()==i?eccvs.get(ec++).getCount():0);
      }
      else
      {
        ECCV.add(0);
      }
      if(ic<iccvs.size())
      {
        ICCV.add(iccvs.get(ic).getYear()==i?iccvs.get(ic++).getCount():0);
      }
      else
      {
        ICCV.add(0);
      }
    }

    jsonObject.put("x",x);
    jsonObject.put("CVPR",CVPR);
    jsonObject.put("ICCV",ICCV);
    jsonObject.put("ECCV",ECCV);
    return jsonObject.toJSONString();
  }

  @Override
  public List<YearCount> getYearCountByConference(String conference,Map<String,Object> params)
  {
    params.put("conference",conference);
    return keywordService.getYearCountByMap(params);
  }


}
