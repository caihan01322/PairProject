package com.pair;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pair.dao.KeywordMapper;
import com.pair.dao.PaperKeywordMapper;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Keyword;
import com.pair.pojo.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    PaperMapper paperMapper;
    KeywordMapper keywordMapper;
    PaperKeywordMapper paperKeywordMapper;

    @Test
    void contextLoads() throws IOException {
        putPapers("CVPR");
        putPapers("ICCV");
        putPapers("ECCV");
    }
    public void putPapers(String publisher) throws IOException {
        File f = new File("C:\\Users\\WWJ20\\Desktop\\data\\"+publisher);
        File[] file =f.listFiles();
        for (File file1 : file) {
            String path="C:\\Users\\WWJ20\\Desktop\\data\\"+publisher+"\\"+file1.getName();
            if(publisher.equals("ECCV")){//ECCV
                putDataIntoMysql2(path,publisher);
            }else{//CVPR&ICCV
                putDataIntoMysql1(path,publisher);
            }
            file1.delete();
        }
    }
    public void putDataIntoMysql1(String path,String publisher) throws IOException {
        Set<String> kwdList=new HashSet<>();
        FileReader fileReader=new FileReader(path);
        int temp=0;
        String data="";//json数据
        while((temp=fileReader.read())!=-1){
            data+=(char)temp;
        }
        data=data.substring(0,data.length()-1);
        fileReader.close();
        JSONObject objects = JSON.parseObject(data);
        JSONArray keywords = objects.getJSONArray("keywords");
        if(keywords!=null){
            for(int i=0;i<keywords.size();i++) {
                JSONObject jsonObject = keywords.getJSONObject(i);
                JSONArray kwds = jsonObject.getJSONArray("kwd");
                for (Object kwd : kwds) {
                    kwdList.add(kwd.toString());
                }
            }
        }else{
        }
        String title = objects.getString("title");
        String abs = objects.getString("abstract");
        String publicationYear = objects.getString("publicationYear");
        String doiLink = objects.getString("doiLink");
        String pid="pid_"+new Date().getTime();
        paperMapper.insertPaper(new Paper(pid,title,abs,publisher,publicationYear,doiLink));
        if(keywords!=null){
            for (String s : kwdList) {
                String kid;
                if(keywordMapper.selectKeyword(s,publisher)==0){
                    kid="kid_"+new Date().getTime();
                    keywordMapper.insertKeyword(kid,s,publisher,1);
                }else{
                    kid = keywordMapper.selectKid(s,publisher);
                    keywordMapper.updateKeyword(new Keyword(kid,s,publisher, keywordMapper.selectNum(s,publisher)+1));
                }
                paperKeywordMapper.insertPK(pid,kid);
            }
        }
    }
    public void putDataIntoMysql2(String path,String publisher) throws IOException {
        Set<String> kwdList=new HashSet<>();
        FileReader fileReader=new FileReader(path);
        int temp=0;
        String data="";//json数据
        while((temp=fileReader.read())!=-1){
            data+=(char)temp;
        }
        fileReader.close();
        JSONObject objects = JSON.parseObject(data);
        JSONArray keywords = objects.getJSONArray("关键词");
        if(keywords!=null){
            for(int i=0;i<keywords.size();i++) {
                kwdList.add(keywords.getString(i));
            }
        }else{
        }
        String title = objects.getString("论文名称");
        String abs = objects.getString("摘要");
        String publicationYear = objects.getString("会议和年份").split(" ")[1];
        String doiLink = objects.getString("原文链接");
        String pid="pid_"+new Date().getTime();
        paperMapper.insertPaper(new Paper(pid,title,abs,publisher,publicationYear,doiLink));
        if(keywords!=null){
            for (String s : kwdList) {
                String kid;
                if(keywordMapper.selectKeyword(s,publisher)==0){
                    kid="kid_"+new Date().getTime();
                    keywordMapper.insertKeyword(kid,s,publisher,1);
                }else{
                    kid = keywordMapper.selectKid(s,publisher);
                    keywordMapper.updateKeyword(new Keyword(kid,s,publisher, keywordMapper.selectNum(s,publisher)+1));
                }
                paperKeywordMapper.insertPK(pid,kid);
            }
        }
    }
}
