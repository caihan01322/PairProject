package com.practice.pairproject.util;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.KeywordService;
import com.practice.pairproject.service.PaperService;
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
    private  PaperService paperService;

    @Autowired
    private  KeywordService keywordService;


    //index:0 1 2 3 4(测试)
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


    //ECCV
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
                        .meeting(my[0])  //meeting[index]
                        .year(my[1])
                        .build();
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

    //CVPR_ICCV
    public int loadCVPR_ICCVpapers(int index){
        ArrayList<File> fileList = this.loadFiles(index);

        //实例一个ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        try{
            for(int i = 0; i < fileList.size(); i++){
            //for (File f : fileList) {
                StringBuffer authorsName = new StringBuffer("");
                JsonNode rootNode = mapper.readValue(fileList.get(i), JsonNode.class);

                if(rootNode.path("abstract").asText().isEmpty() || rootNode.path("abstract").asText().equals("")){
                    System.out.println("【abstract】为空！！------跳过");
                    continue;
                }

                //作者
                JsonNode authors = rootNode.path("authors");
                //System.out.println("【作者名字】：");
                if (authors.isArray()) {
                    for (JsonNode at : authors) {
                        //System.out.println(at.path("name").asText());
                        authorsName.append(at.path("name").asText() + " / ");
                    }
                }

                //其他
                Paper paper = Paper.builder()
                        .abstractContent(rootNode.path("abstract").asText())
                        .meeting(meeting[index])
                        .year(rootNode.path("publicationYear").asText())
                        .publicDate(rootNode.path("publicationDate").asText())
                        .title(rootNode.path("title").asText())
                        .link(rootNode.path("doiLink").asText())
                        .authors(authorsName.toString())
                        .build();

                //System.out.println("【论文】：" + paper);
                //将此论文存入【paper】
                if( paperService.insertPaper(paper) > 0 ){
                    //System.out.println("------插入论文成功【" + paper.getPid() + "】: " + paper);
                }

                //关键词
                JsonNode keywords = rootNode.path("keywords");
                //System.out.println("【关键词】:");
                if (keywords.isArray()) {
                    for (JsonNode kw : keywords) {
                        JsonNode kwd = kw.path("kwd");
                        if (kwd.isArray()) {
                            for (JsonNode keyword : kwd) {
                                //System.out.println(keyword.toString());
                                Keyword k = Keyword.builder()
                                        .content(keyword.toString().replace("\"",""))
                                        .pid(paper.getPid())
                                        .meeting(paper.getMeeting())
                                        .year(paper.getYear())
                                        .build();
                                //System.out.println(kw.toString().replace("\"",""));
                                //将关键词存入【keyword】
                                if(keywordService.insertPKeywords(k) > 0){
                                    //System.out.println("~~~~~插入关键词成功【" + paper.getPid() + "】: " + k);
                                }
                            }
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
