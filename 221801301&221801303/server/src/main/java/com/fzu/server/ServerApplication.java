package com.fzu.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.server.dao.PaperDao;
import com.fzu.server.pojo.ECCV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.*;


@SpringBootApplication
public class ServerApplication {
    @Autowired
    PaperDao dao;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }

    @PostConstruct
    public void showName() throws IOException {
        //System.out.println(dao.getName(1));
//        JSONObject parse = JSON.parse();
        getJson("123");
    }

    public void getJson(String filepath) throws IOException {
        filepath = "E:\\QQ\\1035928314\\FileRecv\\论文数据\\ECCV（2016至2020，3033份）";
        File file = new File(filepath);

        if (!file.isDirectory()) {
            System.out.println("文件");
            System.out.println("path=" + file.getPath());
            System.out.println("absolutepath=" + file.getAbsolutePath());
            System.out.println("name=" + file.getName());

        } else if (file.isDirectory()) {
            System.out.println("文件夹");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                JSONObject parse;
                JSONArray kw;
                ECCV obj = new ECCV();
                //System.out.println("list："+filelist[i].toString());
                File readfile = new File(filepath + "\\" + filelist[i]);
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
                        System.out.println(line);
                        json.append(line);
                    }
                    System.out.println(json);
                    parse = (JSONObject) JSON.parse(json.toString());
                    kw=parse.getJSONArray("关键词");
                    obj.setAbstract(parse.getString("摘要").substring(200));
                    obj.setName(parse.getString("论文名称"));
                    obj.setLink(parse.getString("原文链接"));
                    obj.setYear(parse.getString("会议和年份"));
                    obj.setTime(parse.getString("发布时间"));
                    obj.setKeyword(kw.toJavaList(String.class));


                    dao.addPaper(obj);
                    System.out.println(obj.getID());
                    break;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }

    }


}
