package com.fzu.server.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.server.dao.PaperDao;
import com.fzu.server.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class InputJson {

    @Autowired
    PaperDao dao;

    public void getECCVJson() throws IOException {
//        String filepath = "E:\\QQ\\1035928314\\FileRecv\\论文数据\\ECCV（2016至2020，3033份）";
        String filepath = "./论文数据/ECCV（2016至2020，3033份）";
        File file = new File(filepath);

//        if (!file.isDirectory()) {
//            System.out.println("文件");
//            System.out.println("path=" + file.getPath());
//            System.out.println("absolutepath=" + file.getAbsolutePath());
//            System.out.println("name=" + file.getName());
//
//        } else
        if (file.isDirectory()) {
            System.out.println("文件夹");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                JSONObject parse;
                JSONArray kw;
                Paper obj = new Paper();
                //System.out.println("list："+filelist[i].toString());
//                File readfile = new File(filepath + "\\" + filelist[i]);
                File readfile = new File(filepath + "/" + filelist[i]);
//                if (!readfile.isDirectory()) {
//                      System.out.println("path=" + readfile.getPath());
//                      System.out.println("absolutepath="
//                              + readfile.getAbsolutePath());
//                      System.out.println("name=" + readfile.getName());
//
//                } else if (readfile.isDirectory()) {
//                    //getJson(filepath + "\\" + filelist[i]);
//                    break;
//                }
                try {
                    FileReader fr = new FileReader(readfile);
                    BufferedReader bfr = new BufferedReader(fr);
                    StringBuilder json = new StringBuilder();
                    String line = null;
                    while ((line = bfr.readLine()) != null) {
//                        System.out.println(line);
                        json.append(line);
                    }
//                    System.out.println(json);
                    parse = (JSONObject) JSON.parse(json.toString());
                    kw = parse.getJSONArray("关键词");
                    if(parse.getString("摘要")!=null) {
                        if (parse.getString("摘要").length() < 200)
                            obj.setAbstract(parse.getString("摘要"));
                        else
                            obj.setAbstract(parse.getString("摘要").substring(0, 200).concat("..."));
                    }
                    obj.setMeeting(1);
                    obj.setName(parse.getString("论文名称"));
                    obj.setLink(parse.getString("原文链接"));
                    obj.setYear(parse.getString("会议和年份"));
                    obj.setTime(parse.getString("发布时间"));
//                    obj.setKeyword(kw.toJavaList(String.class));

                    dao.addECCVPaper(obj);
//                    dao.addPaper(obj);
                    if(kw!=null)
                    for (int j = 0; j < kw.size(); j++) {
                        dao.addECCVKeyword(obj.getID(), kw.getString(j));
//                        dao.addKeyword(obj.getID(), kw.getString(j));
                    }
//                    break;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public void getCVPRJson() throws IOException {
//        String filepath = "E:\\QQ\\1035928314\\FileRecv\\论文数据\\CVPR（2000年至2020年，6916篇";
        String filepath = "./论文数据/CVPR（2000年至2020年，6916篇";
        File file = new File(filepath);

        if (file.isDirectory()) {
            System.out.println("文件夹");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                JSONObject parse;
                JSONArray kw;
                JSONArray at;
                Paper obj = new Paper();
//                File readfile = new File(filepath + "\\" + filelist[i]);
                File readfile = new File(filepath + "/" + filelist[i]);

                try {
                    FileReader fr = new FileReader(readfile);
                    BufferedReader bfr = new BufferedReader(fr);
                    StringBuilder json = new StringBuilder();
                    String line = null;
                    while ((line = bfr.readLine()) != null) {
//                        System.out.println(line);
                        json.append(line);
                    }

                    parse = (JSONObject) JSON.parse(json.substring(0,json.length()-1));
                    kw = parse.getJSONArray("keywords");
                    at = parse.getJSONArray("authors");

                    if(parse.getString("abstract")!=null) {
                        if (parse.getString("abstract").length() < 200)
                            obj.setAbstract(parse.getString("abstract"));
                        else
                            obj.setAbstract(parse.getString("abstract").substring(0, 200).concat("..."));
                    }
                    obj.setMeeting(0);
                    obj.setName(parse.getString("publicationTitle"));
                    obj.setLink(parse.getString("doiLink"));
                    obj.setYear(parse.getString("conferenceDate"));
                    obj.setTime(parse.getString("dateOfInsertion"));


                    dao.addCVPRPaper(obj);
//                    dao.addPaper(obj);
                    if(kw!=null)
                    for(int j=0;j<kw.size();j++){
                        JSONObject ko=kw.getJSONObject(j);
                        JSONArray ka=ko.getJSONArray("kwd");
                        if(ka!=null)
                        for(int k=0;k<ka.size();k++){
                            dao.addCVPRKeyword(obj.getID(), ka.getString(k));
//                            dao.addKeyword(obj.getID(), ka.getString(k));
                        }
                    }
                    if(at!=null)
                    for(int j=0;j<at.size();j++){
                        JSONObject ao=at.getJSONObject(j);
                        dao.addCVPRAuthor(obj.getID(), ao.getString("name"));
//                        dao.addAuthor(obj.getID(), ao.getString("name"));
                    }
//                    break;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public void getICCVJson() throws IOException {
//        String filepath = "E:\\QQ\\1035928314\\FileRecv\\论文数据\\ICCV（2001年至2019年，3196篇）";
        String filepath = "./论文数据/ICCV（2001年至2019年，3196篇）";
        File file = new File(filepath);

        if (file.isDirectory()) {
            System.out.println("文件夹");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                JSONObject parse;
                JSONArray kw;
                JSONArray at;
                Paper obj = new Paper();
//                File readfile = new File(filepath + "\\" + filelist[i]);
                File readfile = new File(filepath + "/" + filelist[i]);

                try {
                    FileReader fr = new FileReader(readfile);
                    BufferedReader bfr = new BufferedReader(fr);
                    StringBuilder json = new StringBuilder();
                    String line = null;
                    while ((line = bfr.readLine()) != null) {
//                        System.out.println(line);
                        json.append(line);
                    }

                    parse = (JSONObject) JSON.parse(json.substring(0,json.length()-1));
                    kw = parse.getJSONArray("keywords");
                    at = parse.getJSONArray("authors");

                    if(parse.getString("abstract")!=null) {
                        if (parse.getString("abstract").length() < 200)
                            obj.setAbstract(parse.getString("abstract"));
                        else
                            obj.setAbstract(parse.getString("abstract").substring(0, 200).concat("..."));
                    }
                    obj.setMeeting(2);
                    obj.setName(parse.getString("publicationTitle"));
                    obj.setLink(parse.getString("doiLink"));
                    obj.setYear(parse.getString("conferenceDate"));
                    obj.setTime(parse.getString("dateOfInsertion"));


                    dao.addICCVPaper(obj);
//                    dao.addPaper(obj);
                    if(kw!=null)
                    for(int j=0;j<kw.size();j++){
                        JSONObject ko=kw.getJSONObject(j);
                        JSONArray ka=ko.getJSONArray("kwd");
                        if(ka!=null)
                        for(int k=0;k<ka.size();k++){
                            dao.addICCVKeyword(obj.getID(), ka.getString(k));
//                            dao.addKeyword(obj.getID(), ka.getString(k));
                        }
                    }
                    if(at!=null)
                    for(int j=0;j<at.size();j++){
                        JSONObject ao=at.getJSONObject(j);
                        dao.addICCVAuthor(obj.getID(), ao.getString("name"));
//                        dao.addAuthor(obj.getID(), ao.getString("name"));
                    }
//                    break;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

    }
}
