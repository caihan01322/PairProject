package cn.zcx.pa.PaperImport.controller;

import cn.zcx.pa.PaperShow.pojo.KeywordCount;
import cn.zcx.pa.PaperShow.pojo.Paper;
import cn.zcx.pa.PaperShow.service.echarts.EchartsService;
import cn.zcx.pa.PaperShow.service.keyword.KeywordService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class KeywordController
{
  @Autowired
  private EchartsService echartsService;

  @GetMapping("/echarts")
  public String echarts()
    {
      return "echarts";
    }


//  @RequestParam("params") Map<String,Object> params
  @ResponseBody
  @PostMapping("/getTop10Keywords")
  public String getjson(@RequestParam("beginYear") int beginYear,
                        @RequestParam("endYear") int endYear,
                        @RequestParam("conference") String conference)
  {
    System.out.println(beginYear);
    System.out.println(endYear);

    System.out.println(conference);


    Map<String,Object> params=new HashMap<>();
    params.put("beginYear",beginYear);
    params.put("endYear",endYear);
    params.put("conference",conference);

        return echartsService.getTop10Json(params);





//    JSONObject jsonObject=new JSONObject();
//
//    List<String> x=new ArrayList<>();
//    List<Integer> y=new ArrayList<>();
//
//    List<KeywordCount> list=new ArrayList<>();
//
//    list.add(new KeywordCount("learning",333));
//    list.add(new KeywordCount("reading",555));
//    list.add(new KeywordCount("happy",777));
//    list.add(new KeywordCount("3d",999));
//    list.add(new KeywordCount("PRINT",444));
//    list.add(new KeywordCount("hello",132));
//    list.add(new KeywordCount("world",579));
//    list.add(new KeywordCount("welcome",592));
//    list.add(new KeywordCount("nba",420));
//    list.add(new KeywordCount("cba",521));
//
//
//    for (KeywordCount kc:list)
//    {
//      x.add(kc.getKeyword());
//      y.add(kc.getCount());
//    }
//
//    jsonObject.put("x",x);
//    jsonObject.put("y",y);
//
//    return jsonObject.toJSONString();
  }


}
