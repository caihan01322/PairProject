package com.practice.pairproject.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.practice.pairproject.pojo.Paper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//将论文json文件反序列化存入数据库中！
@Slf4j
@Builder
public class StoragePaper {

    //文件夹路径
    private static String path = "E:\\papers" + File.separator;
    //文件夹
    private static String[] folder = {"CVPR","ECCV","ECCV+","ICCV"};
    //会议类型
    private static String[] meeting = {"CVPR","ECCV","ECCV","ICCV"};
    //年份
    private static String year;


    //index:0 1 2 3
    public static ArrayList<File> loadFiles(int index){
        String fullPath = path + folder[index];

        ArrayList<File> fileList = new ArrayList<File>();
        //ArrayList<String> filenames = new ArrayList<String>();

        File file = new File(fullPath);
        // 获取目录下的所有文件或文件夹
        File[] tempList = file.listFiles();
        // 如果目录为空，直接退出
        if (tempList == null) {
            log.warn("文件目录为空！！1");
            return null;
        }

        // 遍历，目录下的所有文件
        for (File f : tempList) {
            if (f.isFile()) {
                fileList.add(f);
            }
        }
        for (File f1 : fileList) {
            System.out.println(f1.getName());
        }
        return fileList;
    }

    
    public static void loadECCVpapers(int index){
        ArrayList<File> fileList = StoragePaper.loadFiles(index);

        //实例一个ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try{
            for (File f : fileList) {
                JsonNode rootNode = mapper.readValue(f, JsonNode.class);
                //其他
                Paper paper = Paper.builder()
                        .abstractContent(rootNode.path("摘要").asText())
                        .publicDate(rootNode.path("发布时间").asText())
                        .title(rootNode.path("论文名称").asText())
                        .link(rootNode.path("原文链接").asText())
                        .build();
                //会议和年份
                String meeting_year = rootNode.path("会议和年份").asText();
                String[] my = meeting_year.split(" ");
                paper.setMeeting(my[0]);
                paper.setYear(my[1]);
                //将此论文存入【paper】


                //将关键词存入【keyword】
                //关键词
                JsonNode keywords = rootNode.path("关键词");
                System.out.println("【关键词：】");
                if (keywords.isArray()) {
                    for (JsonNode kw : keywords) {
                        System.out.println(kw.toString().replace("\"",""));

                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }




    public static boolean loadPaperTest(){
        //实例一个ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        File temp = new File("E:\\papers\\ECCV\\2016_A Neural Approach to Blind Motion Deblurring.json");
        try {
            JsonNode rootNode = mapper.readValue(temp, JsonNode.class);    /*方法1*/
            JsonNode node = mapper.readTree(temp);
            String abstractContent = node.path("摘要").asText();
            //log.info("【摘要】：" + abstractContent);
            System.out.println("【摘要】：" + abstractContent);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
