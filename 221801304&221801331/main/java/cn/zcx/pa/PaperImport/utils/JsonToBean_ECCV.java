package cn.zcx.pa.PaperImport.utils;

import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class JsonToBean_ECCV
{
    public static Paper getEssay(String fileName,String conference) {
        JSONObject object = ReadJsonUtil.getJson(fileName);

        String title = object.getString("论文名称");//获取论文标题

        String abst = object.getString("摘要");//获取摘要

        String year_confer = object.getString("会议和年份");

        int end = year_confer.length();
        String publicationYear = year_confer.substring(end-4, end);//获取年份

        String doiLink = object.getString("原文链接");//获取原文链接

        JSONArray keywords = object.getJSONArray("关键词");

        Paper paper=new Paper(title,abst,conference,Integer.parseInt(publicationYear),doiLink);

        Set<String> kwds = new HashSet<>();
        if(keywords != null){
            for(int i = 0; i < keywords.size(); i++) {
                String kwd = keywords.getString(i);
                kwds.add(kwd);//添加关键词
            }
        }
        paper.setKeywords(kwds);
        return paper;
    }

}
