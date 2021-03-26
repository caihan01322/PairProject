package cn.zcx.pa.PaperShow.controller;

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


  @ResponseBody
  @PostMapping("/getTop10Keywords")
  public String getTop10Keywords(@RequestParam("beginYear") int beginYear,
                        @RequestParam("endYear") int endYear,
                        @RequestParam("conference") String conference)
  {
    return echartsService.getTop10Json(beginYear,endYear,conference);
  }

  @ResponseBody
  @PostMapping("/getKeywordCount")
  public String getKeywordCount(@RequestParam("beginYear") int beginYear,@RequestParam("endYear") int endYear,
                                @RequestParam("keyword") String keyword)
  {
    return echartsService.getYearCountJson(beginYear, endYear, keyword);
  }


}
