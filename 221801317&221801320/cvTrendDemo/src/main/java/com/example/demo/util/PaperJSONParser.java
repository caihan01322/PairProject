package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pojo.Paper;

import java.io.IOException;

public enum PaperJSONParser  {
    CVPR{
        @Override
        Paper getPaperByJSON(StringBuilder json) throws IOException {
            Paper paper = new Paper();
            JSONObject paperJSONObj = JSON.parseObject(json.substring(0,json.lastIndexOf(";")));

            //base info
            paper.setMeeyting("CVPR");
            paper.setTitle(paperJSONObj.getString("title"));
            paper.setAbstractContent(paperJSONObj.getString("abstract"));
            paper.setYear(paperJSONObj.getString("publicationYear"));
            paper.setLink(paperJSONObj.getString("doiLink"));

            //author
            JSONArray authors = paperJSONObj.getJSONArray("authors");
            StringBuilder authorsInfo = new StringBuilder();
            for (Object author:authors) {
                JSONObject authorInfo = (JSONObject)author;
                authorsInfo.append(authorInfo.getString("name")).append(",");
            }
            paper.setAuthors(authorsInfo.substring(0,authorsInfo.length()-1));
            return paper;

        }
    },
    ECCV{
        @Override
        Paper getPaperByJSON(StringBuilder json) throws IOException {
            Paper paper = new Paper();
            JSONObject paperJSONObj = JSON.parseObject(json.toString());

            //base info
            paper.setMeeyting("ECCV");
            paper.setTitle(paperJSONObj.getString("论文名称"));
            paper.setAbstractContent(paperJSONObj.getString("摘要"));
            String publishTime = paperJSONObj.getString("发布时间");
            paper.setYear(publishTime.substring(publishTime.length()-5,publishTime.length()));
            paper.setLink(paperJSONObj.getString("原文链接"));
            paper.setAuthors("暂无数据");

            return paper;

        }
    },
    ICCV{
        @Override
        Paper getPaperByJSON(StringBuilder json) throws IOException {
            Paper paper = new Paper();
            JSONObject paperJSONObj = JSON.parseObject(json.substring(0,json.lastIndexOf(";")));

            //base info
            paper.setMeeyting("ICCV");
            paper.setTitle(paperJSONObj.getString("title"));
            paper.setAbstractContent(paperJSONObj.getString("abstract"));
            paper.setYear(paperJSONObj.getString("publicationYear"));
            paper.setLink(paperJSONObj.getString("doiLink"));

            //author
            JSONArray authors = paperJSONObj.getJSONArray("authors");
            StringBuilder authorsInfo = new StringBuilder();
            for (Object author:authors) {
                JSONObject authorInfo = (JSONObject)author;
                authorsInfo.append(authorInfo.getString("name")).append(",");
            }
            paper.setAuthors(authorsInfo.substring(0,authorsInfo.length()-1));
            return paper;

        }
    };

    //abstract Paper getPaper(String path) throws IOException;
    abstract Paper getPaperByJSON(StringBuilder json) throws IOException;

}
