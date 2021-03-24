package cn.zcx.pa.PaperImport.utils;


import cn.zcx.pa.PaperImport.pojo.Essay;
import cn.zcx.pa.PaperShow.pojo.Keyword;
import cn.zcx.pa.PaperShow.pojo.Paper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class JsonToBean_ICCV_CVPR {

    public static Paper getEssay(String fileName,String conference) {
        JSONObject object = ReadJsonUtil.getJson(fileName);

        String abst = object.getString("abstract");//获取文章摘要

        String title = object.getString("title");//获取标题

        String publicationYear = object.getString("publicationYear").trim();//获取年份




        String doiLink = object.getString("doiLink");//获取原文链接

        JSONArray keywords = object.getJSONArray("keywords");


        Paper paper=new Paper(title,abst,conference,Integer.parseInt(publicationYear),doiLink);

        Set<Keyword> kwds = new HashSet<>();
        if(keywords != null){
            for(int i = 0; i < keywords.size(); i++) {
                JSONArray keyword = keywords.getJSONObject(i).getJSONArray("kwd");
                for(int j = 0; j < keyword.size(); j++){
                    String kwd=keyword.getString(j);
                    kwds.add(new Keyword(paper.getPid(),kwd,conference,Integer.parseInt(publicationYear)));//添加关键词
                }
            }
        }

        return paper;
    }
}
