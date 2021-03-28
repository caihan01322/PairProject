package com.practice.pairproject.service.impl;

import com.practice.pairproject.pojo.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class PaperServiceImplTest {

    @Autowired
    private static PaperServiceImpl paperService;

    @Autowired
    private static KeywordServiceImpl keywordService;

    @Test
    void insertPaper() {

        //PaperService ppp = new PaperServiceImpl();

        Paper paper = Paper.builder()
                .abstractContent("摘要")
                .publicDate("发布时间")
                .title("论文名称")
                .link("原文链接")
                .meeting("ECCV")
                .year("2021")
                .build();

        /*Paper p = new Paper();
        p.setAbstractContent("摘要");
        p.setPublicDate("发布时间");
        p.setTitle("论文名称");
        p.setLink("原文链接");
        p.setMeeting("ECCV");
        p.setYear("2021");*/
        paperService.insertPaper(paper);
        /*if(paperService.insertPaper(paper) >0 ){
            System.out.println("------插入论文成功【+" + "pid" + "+】: " + paper);
        }*/
    }

    @Test
    void testInsertPaper() {
    }

    @Test
    void deleteByPrimaryKey() {
    }

    @Test
    void deleteByPrimaryKeyList() {
    }

    @Test
    void searchPaper() {
    }

    @Test
    void selectAll() {
    }

    @Test
    void selectPaperByKeyword() {
    }

    @Test
    void setMyPage() {
        List<Integer> plist = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        System.out.println("【plist】：" + plist);
    }
}