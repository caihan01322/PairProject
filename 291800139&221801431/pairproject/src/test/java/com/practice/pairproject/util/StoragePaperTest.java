package com.practice.pairproject.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.pairproject.pojo.Paper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class StoragePaperTest {

    @Test
    void loadPaper() {
        //实例一个ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        File temp = new File("E:\\papers\\ECCV\\2016_A Neural Approach to Blind Motion Deblurring.json");
        try {
            JsonNode rootNode = mapper.readValue(temp, JsonNode.class);    /*方法1*/
            //JsonNode node = mapper.readTree(temp);
            String abstractContent = rootNode.path("摘要").asText();
            //log.info("【摘要】：" + abstractContent);
            System.out.println("【摘要】：" + abstractContent);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    //读取文件夹下的所有文件
    void loadFiles() {
        String fullPath = "E:\\papers\\" + "111";

        ArrayList<File> fileList = new ArrayList<File>();
        //ArrayList<String> filenames = new ArrayList<String>();

        File file = new File(fullPath);
        // 获取目录下的所有文件或文件夹
        File[] tempList = file.listFiles();
        // 如果目录为空，直接退出
        if (tempList == null) {
            log.warn("文件目录为空！！1");
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

        /****************************************************************/
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
                System.out.println("【其他】：" + paper);
                //会议和年份
                String meeting_year = rootNode.path("会议和年份").asText();
                String[] my = meeting_year.split(" ");
                System.out.println("【会议】：" + my[0]+"; " + "【年份】：" + my[1]);

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


}