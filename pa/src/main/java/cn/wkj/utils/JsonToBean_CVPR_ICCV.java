package cn.wkj.utils;

import cn.wkj.pojo.Essay;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;
import java.util.TreeSet;

public class JsonToBean_CVPR_ICCV {

    public static Essay getEssay(String fileName, String conference) {
        JSONObject object = ReadJsonUtil.getJson(fileName);

        String abst = object.getString("abstract");//获取文章摘要

        String title = object.getString("title");//获取标题

        String publicationYear = object.getString("publicationYear");//获取年份

        String doiLink = object.getString("doiLink");//获取原文链接

        JSONArray keywords = object.getJSONArray("keywords");
        Set<String> kwds = new TreeSet<>();
        if(keywords != null){
            for(int i = 0; i < keywords.size(); i++) {
                JSONArray keyword = keywords.getJSONObject(i).getJSONArray("kwd");
                for(int j = 0; j < keyword.size(); j++){
                    kwds.add(keyword.getString(j));//添加关键词
                }
            }
        }

        return new Essay(title,abst,conference,publicationYear,doiLink,kwds);
    }
}
