package com.practice.pairproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pairproject.pojo.KeywordVO;
import com.practice.pairproject.service.KeywordService;
import com.practice.pairproject.util.AjaxResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
@RequestMapping({"/keyword"})
public class KeywordController {

    static private Calendar date = Calendar.getInstance();

    @Autowired
    private KeywordService keywordService;

    @RequestMapping("/home")
    public String keywordAnalyze(){
        return "keywordAtlas";
    }

    /**
     * 选取所有已爬取论文 在 years（默认5）年间的热词 top-topNum（默认10）
     * 形成如关键词图谱之类直观的查看方式，点击某个关键词可展现相关的论文；
     * @param years
     * @param topNum
     * @param model
     * @return
     */
    @ResponseBody
    @GetMapping("/top1")
    public Object selectAllTOPKeyword(
            @RequestParam(name= "years" , defaultValue = "5") String years,
            @RequestParam(name= "topNum" , defaultValue = "10") Integer topNum,
            Model model) throws JsonProcessingException {

        //1.创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();

        List<KeywordVO> keywordVOList = keywordService.selectAllTOPKeyword(years,topNum);
        if(keywordVOList.isEmpty()){
            log.error("【分析所有论文，获取 热词top10 失败！】");
            //return AjaxResponse.fail(500, "【分析所有论文，获取 热词top10 失败！】");
        }
        log.info("【分析所有论文，获取 热词top10 成功！】");
        //return AjaxResponse.success(keywordVOList, "【分析所有论文，获取 热词top10 成功！】");
       /* model.addAttribute("data",mapper.writeValueAsString(keywordVOList));  //mapper.writeValueAsString(keywordVOList) //keywordVOList
        return "keywordAtlas";*/

       Map<String, Object> result= new HashMap<>();
       result.put("data",keywordVOList);  //mapper.writeValueAsString(keywordVOList)
       return result;
    }

    /**
     * 可对多年间、不同顶会的热词呈现热度走势对比，
     * 以动图的形式呈现（这里将范畴限定在计算机视觉的三大顶会CVPR、ICCV、ECCV内）
     */
    /**
     * 选取某 meeting 在每 years 年间的热词 top-topNum
     * 并返回每年次会议这些 keyword 的频率（map）
     * @param years
     * @param topNum
     * @param model
     * @return
     */
    @ResponseBody
    @GetMapping("/top2/{meeting}")
    public Object selectTOPKeywordEveryYear(
            @PathVariable("meeting") String meeting,
            @RequestParam(name= "years" , defaultValue = "5") String years,
            @RequestParam(name= "topNum" , defaultValue = "10") Integer topNum,
            Model model) throws JsonProcessingException {

        //1.创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();

        //某会议某年 years年间 的热词 topNum
        List<KeywordVO> keywordVOList = keywordService.selectTopKeyword(meeting, years, topNum);
        if(keywordVOList.isEmpty()){
            log.error("【分析" + meeting + "的论文，获取 热词top10 失败！】");
            return AjaxResponse.fail(500, "【分析" + meeting + "的论文，获取 热词top10 失败！】");
        }

        //当前年份
        int nowYear = date.get(Calendar.YEAR);
        //总共几年
        int yearNum = Integer.parseInt(years);
        //每年关于keywordVOList的频率信息
        Map<Integer, List<KeywordVO>> meeting_kList = new HashMap<>();

        //用于查询的map
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("meeting", meeting);
        //循环-查询每年的keywordVOList的频率信息
        while((yearNum--) > 0){
            int year = nowYear-yearNum;
            paramMap.put("year", String.valueOf(year));
            //第year年的keywordVOList的频率信息
            List<KeywordVO> kyList = keywordService.selectTOPKeywordXYear(paramMap, keywordVOList);

            if(kyList.isEmpty()){
                log.error("【分析("+meeting+"-"+year+")的论文，获取 热词top10 的频率失败！】");
            }else{
                meeting_kList.put(year, kyList);
            }
        }

        Map<String, Object> result = new HashMap<>();
        /*result.put("top"+topNum, keywordVOList);
        result.put("mkList", meeting_kList);*/
        result.put("map", meeting_kList);  //mapper.writeValueAsString(keywordVOList)
        result.put("code",200);
        log.info("【分析" + meeting + "的论文，获取 热词top10 成功！】");
        //return AjaxResponse.success(meeting_kList , "【分析("+meeting+":"+yearNum+")内的论文，获取 热词top10 的频率失败！】");
        return result;
    }

}
