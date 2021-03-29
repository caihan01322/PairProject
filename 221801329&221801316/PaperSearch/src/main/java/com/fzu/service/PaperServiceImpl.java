package com.fzu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.mapper.PaperMapper;
import com.fzu.pojo.Keyword;
import com.fzu.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper paperMapper;

   /* @Override
    public void uploadPaper(Paper paper) {
        //添加论文的信息
        paperMapper.addPaper(paper);
        //获得返回的主键id
        Integer id=paper.getId();
        //添加论文与作者的关联关系(一篇论文有多个作者)
        List<String> authors =paper.getAuthor();
        for (String author : authors) {
            paperMapper.insertAuthorWithId(id,author);
        }
        //添加论文与关键词的关联关系
        List<String> keywords = paper.getKeywords();
        for (String keyword : keywords) {
            paperMapper.insertKeywordWithId(id,keyword);
        }
    }*/


    @Override
    public List<Paper> queryPaperByPage(Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryPaper(start,rows);
        for (Paper paper : paperList) {
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
        }
        return paperList;
    }

    @Override
    public List<Paper> queryPaperByKeyword(String keyword, Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryPaperByKeyword(keyword,start,rows);
        for (Paper paper : paperList) {
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
        }
        return paperList;
    }

    @Override
    public List<Paper> queryPaperByAuthor(String author, Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryPaperByAuthor(author,start,rows);
        for (Paper paper : paperList) {
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
        }
        return paperList;
    }

    @Override
    public List<Map<String, String>> queryTop10ByYear() {
        String []meets=new String[]{"CVPR","ECCV","ICCV"};
        Integer []years=new Integer[]{2016,2017,2018,2019,2020};
        List<Map<String,String>>data=new ArrayList<>();
        //0级数据
        Map<String,String> param0=new HashMap<>();
        param0.put("id","0.0");
        param0.put("parent","");
        param0.put("name","顶会五年总计");
        data.add(param0);
        //一级数据
        for(int i=0;i<3;i++){
            Map<String,String> param1=new HashMap<>();
            param1.put("id","1."+i);
            param1.put("parent","0.0");
            param1.put("name",meets[i]);
            data.add(param1);
        }
        //二级数据
        int k=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                Map<String,String> param2=new HashMap<>();
                param2.put("id","2."+k);
                param2.put("parent","1."+i);
                param2.put("name",String.valueOf(years[j]));
                data.add(param2);
                k++;
            }
        }
        //三级数据
        k=0;
        int n=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                //获得第i个会议，第j年的前10关键词及其数量
                List<Keyword> keywordMapList=paperMapper.queryTop10ByYear(years[j],meets[i]);
                //如果查询不到记录
                if(keywordMapList==null){
                    Map<String,String> param3=new HashMap<>();
                    param3.put("id","3."+n);
                    param3.put("parent","2."+k);
                    param3.put("name", " ");
                    param3.put("value"," ");
                    data.add(param3);
                    n++;
                }else{
                    //查询得到记录
                    for (Keyword keyword : keywordMapList) {
                        Map<String,String> param3=new HashMap<>();
                        param3.put("id","3."+n);
                        param3.put("parent","2."+k);
                        param3.put("name", keyword.getName());
                        param3.put("value",String.valueOf(keyword.getCount()));
                        data.add(param3);
                        n++;
                    }
                }

                k++;
            }
        }
        return data;
    }

    @Override
    public void register(String username, String password) {
        paperMapper.insertUser(username,password);
    }

    @Override
    public boolean login(String username, String password) {
        String result=paperMapper.selectPassword(username);
        if(result.equals(password))
            return true;
        else return false;

    }

    @Override
    public void addLike(Integer userId, Integer paperId) {
        paperMapper.insertLike(userId,paperId);
    }

    @Override
    public List<Paper> queryLikes(Integer userId,Integer start,Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryLikes(userId,start,rows);
        for (Paper paper : paperList) {
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
        }
        return paperList;
    }


}
