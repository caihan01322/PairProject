package com.example.demo.service;

import com.example.demo.pojo.PaperKeyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Paper;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ServiceTest {
    @Autowired
    PaperService paperService;

    @Autowired
    PaperKeywordService paperKeywordService;

    public static Paper getPaperInstance(int id){
        Paper paper = new Paper();
        paper.setYear("2021");
        paper.setPaperID(1000000+id);
        paper.setMeeting("TEST");
        return paper;
    }
    public static PaperKeyword getPKInstance(int id,int pid,String kw){
        PaperKeyword pk = new PaperKeyword();
        pk.setFrequency(4000);
        pk.setYear("2021");
        pk.setPaperID(1000000+pid);
        pk.setPaperKeywordID(10000000+id);
        pk.setMeeting("TEST");
        pk.setKeyword(kw);
        return pk;
    }

    @Test
    public void testAdd(){
        paperService.add(getPaperInstance(1));
        for (int i = 0; i < 10; i++) {
            paperKeywordService.add(getPKInstance(i,1,"hello"+i));
        }
    }
    @Test
    public void testDel(){
        paperService.deletePaperByID(getPaperInstance(1).getPaperID());
    }
    @Test
    public void testHotWord(){
        testAdd();
        paperKeywordService.getHotWordResentYear().forEach(k->{
            System.out.println(k.getKeyword()+":"+k.getFrequency());
        });
        testDel();
    }

    @Test
    public void testWordTrend(){
        paperKeywordService.getWordTrend("Partitioning").forEach(k->{
            System.out.println(k.getKeyword()+":"+k.getYear()+":"+k.getFrequency());
        });
    }
    @Test
    public void testWordTrend_recent(){
        String keyword = "Partitioning";
        paperKeywordService.getWordTrendRecent(keyword)
                .entrySet()
                .forEach(stringLongEntry -> {
                    System.out.println(keyword+":"+stringLongEntry.getKey()+":"+stringLongEntry.getValue());
                });
    }
    @Test
    public void testPagerQuery_P_by_Keyword(){
        System.out.println("-----------------------------------------");

        paperKeywordService.getPaperByKeywordLimit("rolling",0,10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperKeywordService.getPaperByKeywordLimit("rolling",9,10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");
    }

    @Test
    public void testPagerQuery_P_by_title(){
        System.out.println("-----------------------------------------");

        paperService.queryByTitleLimit("Simultaneous",0,10).forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperService.queryByTitleLimit("Simultaneous",9,10).forEach(System.out::println);

        System.out.println("-----------------------------------------");
    }

    @Test
    public void testPagerQuery_Accuracy(){
        System.out.println("-----------------------------------------");

        Paper paper = new Paper();
        paper.setMeeting("CVPR");
        paper.setYear("2020");
        paper.setAbstractContent("Simultaneous");
        paperService.query(paper,0,10).forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperService.query(paper,9,10).forEach(System.out::println);
        System.out.println("-----------------------------------------");
    }
}
