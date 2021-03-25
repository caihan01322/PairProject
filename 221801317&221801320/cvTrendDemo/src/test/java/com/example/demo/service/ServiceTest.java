package com.example.demo.service;

import com.example.demo.pojo.Keyword;
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
    public void testHotWord(){//五年来的热词列表
//        testAdd();
        paperKeywordService.getHotWordResentYear().forEach(k->{
            System.out.println(k.getKeyword()+":"+k.getFrequency());
        });
        System.out.println("cvpr-------------------------");
        paperKeywordService.getHotWordResentYear("cvpr").forEach(k->{
            System.out.println(k.getKeyword()+":"+k.getFrequency());
        });
//        testDel();
    }

    @Test
    public void testWordTrend(){//某个词的趋势
        paperKeywordService.getWordTrend("Partitioning").forEach(k->{
            System.out.println(k.getKeyword()+":"+k.getYear()+":"+k.getFrequency());
        });
        System.out.println("cvpr-------------------------");
        paperKeywordService.getWordTrend("Partitioning","cvpr").forEach(k->{
            System.out.println(k.getKeyword()+":"+k.getYear()+":"+k.getFrequency());
        });

    }
    @Test
    public void testWordTrend_recent(){//某个词的趋势map，近五年 空的补0
        String keyword = "Partitioning";
        paperKeywordService.getWordTrendRecent(keyword)
                .entrySet()
                .forEach(stringLongEntry -> {
                    System.out.println(keyword+":"+stringLongEntry.getKey()+":"+stringLongEntry.getValue());
                });

        System.out.println("cvpr-------------------------");
        paperKeywordService.getWordTrendRecent(keyword,"cvpr")
                .entrySet()
                .forEach(stringLongEntry -> {
                    System.out.println(keyword+":"+stringLongEntry.getKey()+":"+stringLongEntry.getValue());
                });
    }
    @Test
    public void testPagerQuery_P_by_Hotword(){//通过keyword查找文章 文章热词跳转
        Keyword param = new Keyword();
        param.setKeyword("rolling");
        System.out.println(paperKeywordService.getPaperNumByKeyword(param));

        System.out.println("-----------------------------------------");

        paperKeywordService.getPaperByKeywordLimit(param,0,10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperKeywordService.getPaperByKeywordLimit(param,9,10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");
    }
    @Test
    public void testPagerQuery_P_by_Keyword(){//通过keyword查找文章 模糊搜索
        System.out.println(paperService.queryPaperNumByKeyword("rolling"));

        System.out.println("-----------------------------------------");

        paperService.queryByKeywordLimit("rolling",0,10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperService.queryByKeywordLimit("rolling",9,10)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");
    }

    @Test
    public void testPagerQuery_P_by_title(){//通过title查找文章
        System.out.println(paperService.queryPaperNumByTitle("Simultaneous"));

        System.out.println("-----------------------------------------");

        paperService.queryByTitleLimit("Simultaneous",0,10).forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperService.queryByTitleLimit("Simultaneous",9,10).forEach(System.out::println);

        System.out.println("-----------------------------------------");
    }

    @Test
    public void testPagerQuery_Accuracy(){//通过精确信息查找文章
        Paper paper = new Paper();
        paper.setMeeting("CVPR");
        paper.setYear("2020");
        paper.setAbstractContent("Simultaneous");

        System.out.println(paperService.queryPaperNum(paper));

        System.out.println("-----------------------------------------");

        paperService.query(paper,0,10).forEach(System.out::println);

        System.out.println("-----------------------------------------");

        paperService.query(paper,15,10).forEach(System.out::println);
        System.out.println("-----------------------------------------");
    }
}
