package com.practice.pairproject.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.KeywordService;
import com.practice.pairproject.service.PaperService;
import com.practice.pairproject.service.impl.KeywordServiceImpl;
import com.practice.pairproject.service.impl.PaperServiceImpl;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//将论文json文件反序列化存入数据库中！
@Slf4j
@Builder
@Component
public class StoragePaper {

    //文件夹路径
    private static String path = "E:\\papers" + File.separator;
    //文件夹
    private static String[] folder = {"CVPR","ECCV","ECCV+","ICCV","111"};
    //会议类型
    private static String[] meeting = {"CVPR","ECCV","ECCV","ICCV"};
    //年份
    private static String year;

    @Autowired
    private  PaperServiceImpl paperService;

    @Autowired
    private  KeywordServiceImpl keywordService;


    //index:0 1 2 3
    public  ArrayList<File> loadFiles(int index){
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
        /*for (File f1 : fileList) {
            System.out.println(f1.getName());
        }*/
        return fileList;
    }


    public int loadECCVpapers(int index){
        ArrayList<File> fileList = this.loadFiles(index);

        //实例一个ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try{
            for (File f : fileList) {
                JsonNode rootNode = mapper.readValue(f, JsonNode.class);
                //会议和年份
                String meeting_year = rootNode.path("会议和年份").asText();
                String[] my = meeting_year.split(" ");

                //其他
                Paper paper = Paper.builder()
                        .abstractContent(rootNode.path("摘要").asText())
                        .publicDate(rootNode.path("发布时间").asText())
                        .title(rootNode.path("论文名称").asText())
                        .link(rootNode.path("原文链接").asText())
                        .meeting(my[0])
                        .year(my[1])
                        .build();
                /*Paper p = new Paper();
                p.setAbstractContent(rootNode.path("摘要").asText());
                p.setPublicDate(rootNode.path("发布时间").asText());
                p.setTitle(rootNode.path("论文名称").asText());
                p.setLink(rootNode.path("原文链接").asText());
                p.setMeeting(my[0]);
                p.setYear(my[1]);*/

                //System.out.println("【论文】：" + paper);
                //将此论文存入【paper】
                if( paperService.insertPaper(paper) > 0 ){
                    //System.out.println("------插入论文成功【" + paper.getPid() + "】: " + paper);
                }

                //关键词
                JsonNode keywords = rootNode.path("关键词");
                //System.out.println("【关键词：】");
                if (keywords.isArray()) {
                    for (JsonNode kw : keywords) {
                        Keyword keyword = Keyword.builder()
                                .content(kw.toString().replace("\"",""))
                                .pid(paper.getPid())
                                .meeting(my[0])
                                .year(my[1])
                                .build();
                        //System.out.println(kw.toString().replace("\"",""));
                        //将关键词存入【keyword】
                        if(keywordService.insertPKeywords(keyword) > 0){
                            //System.out.println("~~~~~插入关键词成功【" + paper.getPid() + "】: " + keyword);
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return fileList.size();
    }




    public boolean loadPaperTest(){
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
