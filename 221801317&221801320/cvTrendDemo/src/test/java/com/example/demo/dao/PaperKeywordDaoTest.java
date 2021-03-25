package com.example.demo.dao;

import com.example.demo.pojo.Keyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pojo.Paper;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class PaperKeywordDaoTest {

    @Autowired
    PaperKeywordDao paperKeywordDao;
    @Test
    public void testgetHotWordResentYear(){
        Calendar cur = Calendar.getInstance();
        List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,null);
        //List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(10);
        hotWordResentYear.stream().forEach(keyword -> {
            System.out.println(keyword.getKeyword()+": "+keyword.getFrequency());
        });
    }

    @Test
    public void testGetWordTread(){
        Calendar cur = Calendar.getInstance();
        List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,null);
        //List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(10);
        Keyword keyword = hotWordResentYear.get(5);
        List<Keyword> wordTrend = paperKeywordDao.getWordTrend(keyword.getKeyword(),null);
        wordTrend.stream().forEach(keyword1 -> {
            System.out.println(keyword1.getKeyword()+"--"+keyword1.getYear()+"--"+keyword1.getFrequency());
        });
    }

    @Test
    public void testGetWordTread_CVPR(){
        Calendar cur = Calendar.getInstance();
        List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,"CVPR");
        //List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(10);
        Keyword keyword = hotWordResentYear.get(5);
        List<Keyword> wordTrend = paperKeywordDao.getWordTrend(keyword.getKeyword(),"CVPR");
        wordTrend.stream().forEach(keyword1 -> {
            System.out.println(keyword1.getKeyword()+"--"+keyword1.getYear()+"--"+keyword1.getFrequency());
        });
    }
    /**
     * 通过关键词搜索相关的paper
     */
    @Test
    public void testGetPaperByWord() {
        Calendar cur = Calendar.getInstance();
        List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,null);
        //List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(10);
        Keyword keyword = hotWordResentYear.get(5);
        List<Paper> papers = paperKeywordDao.getPaperByKeyword(keyword.getKeyword());
        papers.stream().limit(10).forEach(paper -> {
            System.out.println(paper.toString());
        });
    }

    @Test
    public void testGetPaperNumByWord() {
        Calendar cur = Calendar.getInstance();
        List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,null);
        //List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(10);
        Keyword keyword = hotWordResentYear.get(5);
        int num = paperKeywordDao.getPaperNumByKeyword(keyword);
        System.out.println(num);
    }

    //通过paperID搜索相关的关键词
    @Test
    public void testGetWordByPaperID() {
        Calendar cur = Calendar.getInstance();
        List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(cur.get(Calendar.YEAR)-5,cur.get(Calendar.YEAR),10,null);
        //List<Keyword> hotWordResentYear = paperKeywordDao.getOrderedWord(10);
        Keyword keyword = hotWordResentYear.get(5);
        List<Paper> papers = paperKeywordDao.getPaperByKeyword(keyword.getKeyword());

        List<Keyword> keywords = paperKeywordDao.getKeywordByPaper(papers.get(0).getPaperID());
        System.out.println("fine");
    }

}
