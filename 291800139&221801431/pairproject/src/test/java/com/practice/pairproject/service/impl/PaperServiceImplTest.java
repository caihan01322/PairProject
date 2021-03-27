package com.practice.pairproject.service.impl;

import com.practice.pairproject.pojo.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
}