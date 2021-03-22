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
            paper.setMeeting("CVPR");
            paper.setTitle(paperJSONObj.getString("title"));
            paper.setAbstractContent(paperJSONObj.getString("abstract"));
            paper.setYear(paperJSONObj.getString("publicationYear").trim());
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
            try {
                Paper paper = new Paper();
                JSONObject paperJSONObj = JSON.parseObject(json.toString());

                //base info
                paper.setMeeting("ECCV");
                paper.setTitle(paperJSONObj.getString("论文名称"));
                paper.setAbstractContent(paperJSONObj.getString("摘要"));
                String publishTime = paperJSONObj.getString("会议和年份").trim();
                paper.setYear(publishTime.substring(publishTime.length()-4,publishTime.length()).trim());
                paper.setLink(paperJSONObj.getString("原文链接").trim());
                paper.setAuthors("empty");

                return paper;
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;

        }
    },
    ICCV{
        @Override
        Paper getPaperByJSON(StringBuilder json) throws IOException {
            Paper paper = new Paper();
            JSONObject paperJSONObj = JSON.parseObject(json.substring(0,json.lastIndexOf(";")));

            //base info
            paper.setMeeting("ICCV");
            paper.setTitle(paperJSONObj.getString("title"));
            paper.setAbstractContent(paperJSONObj.getString("abstract"));
            paper.setYear(paperJSONObj.getString("publicationYear").trim());
            paper.setLink(paperJSONObj.getString("doiLink").trim());

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
