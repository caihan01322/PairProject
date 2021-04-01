package com.fzu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.mapper.PaperMapper;
import com.fzu.pojo.Keyword;
import com.fzu.pojo.Paper;
import com.fzu.pojo.User;
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
    public JSONObject queryPaperByPage(Integer userId, Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryPaper(start,rows);
        List<JSONObject> jsonObjects=new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject jsonObject=new JSONObject();
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
            jsonObject.put("data",paper);
            jsonObject.put("isLike",paperMapper.isLike(userId,paperId));
            jsonObjects.add(jsonObject);
        }
        Integer total=paperMapper.countAll();
        JSONObject result=new JSONObject();
        result.put("paper",jsonObjects);
        result.put("total",total);
        return result;
    }

    @Override
    public JSONObject queryPaperByKeyword(Integer userId,String keyword, Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryPaperByKeyword(keyword,start,rows);
        List<JSONObject> jsonObjects=new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject jsonObject=new JSONObject();
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
            jsonObject.put("data",paper);
            jsonObject.put("isLike",paperMapper.isLike(userId,paperId));
            jsonObjects.add(jsonObject);
        }
        Integer total=paperMapper.countAllByKeyword(keyword);
        JSONObject result=new JSONObject();
        result.put("paper",jsonObjects);
        result.put("total",total);
        return result;
    }

    @Override
    public JSONObject queryPaperByAuthor(Integer userId,String author, Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryPaperByAuthor(author,start,rows);
        List<JSONObject> jsonObjects=new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject jsonObject=new JSONObject();
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
            jsonObject.put("data",paper);
            jsonObject.put("isLike",paperMapper.isLike(userId,paperId));
            jsonObjects.add(jsonObject);
        }
        Integer total=paperMapper.countAllByAuthor(author);
        JSONObject result=new JSONObject();
        result.put("paper",jsonObjects);
        result.put("total",total);
        return result;

    }

    @Override
    public JSONObject queryPaperByTitle(Integer userId,String title, Integer start, Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryByTitle(title,start,rows);
        List<JSONObject> jsonObjects=new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject jsonObject=new JSONObject();
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
            jsonObject.put("data",paper);
            jsonObject.put("isLike",paperMapper.isLike(userId,paperId));
            jsonObjects.add(jsonObject);
        }
        Integer total=paperMapper.countAllByTitle(title);
        JSONObject result=new JSONObject();
        result.put("paper",jsonObjects);
        result.put("total",total);
        return result;
    }

    @Override
    public List<JSONObject> queryTop10ByYear() {
        String []meets=new String[]{"CVPR","ECCV","ICCV"};
        Integer []years=new Integer[]{2016,2017,2018,2019,2020};
        List<JSONObject> data=new ArrayList<>();
        //0级数据
        Map<String,String> param0=new HashMap<>();
        JSONObject jsonObject0 =new JSONObject();
        jsonObject0.put("id","0.0");
        jsonObject0.put("parent","");
        jsonObject0.put("name","顶会五年总计");
        data.add(jsonObject0);
        //一级数据
        for(int i=0;i<3;i++){
           JSONObject jsonObject1=new JSONObject();
           jsonObject1.put("id","1."+i);
           jsonObject1.put("parent","0.0");
           jsonObject1.put("name",meets[i]);
           data.add(jsonObject1);
        }
        //二级数据
        int k=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<5;j++){
                JSONObject jsonObject2=new JSONObject();
                jsonObject2.put("id","2."+k);
                jsonObject2.put("parent","0.0");
                jsonObject2.put("parent","1."+i);
                jsonObject2.put("name",String.valueOf(years[j]));
                data.add(jsonObject2);
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
                if(keywordMapList.size()==0){
                    for(int m=0;m<10;m++){
                        JSONObject jsonObject3=new JSONObject();
                        jsonObject3.put("id","3."+n);
                        jsonObject3.put("parent","2."+k);
                        jsonObject3.put("name", "nothing");
                        jsonObject3.put("value",1);
                        data.add(jsonObject3);
                        n++;
                    }

                }else{
                    //查询得到记录
                    for (Keyword keyword : keywordMapList) {
                        JSONObject jsonObject3=new JSONObject();
                        jsonObject3.put("id","3."+n);
                        jsonObject3.put("parent","2."+k);
                        jsonObject3.put("name", keyword.getName());
                        jsonObject3.put("value",keyword.getCount());
                        data.add(jsonObject3);
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
    public Map<String,Integer> login(String username, String password) {
        Map<String,Integer> map=new HashMap<>();
        User user=paperMapper.selectPassword(username);
        if(user.getPassword().equals(password)){
            map.put("userId",user.getId());
            return map;
        }else{
            map.put("userId",-1);
            return map;
        }
    }

    @Override
    public void addLike(Integer userId, Integer paperId) {
        paperMapper.insertLike(userId,paperId);
    }

    @Override
    public void deleteLike(Integer userId, Integer paperId) {
        paperMapper.deleteLike(userId, paperId);
    }

    @Override
    public JSONObject queryLikes(Integer userId,Integer start,Integer rows) {
        List<Paper> paperList;
        paperList=paperMapper.queryLikes(userId,start,rows);
        List<JSONObject> jsonObjects=new ArrayList<>();
        for (Paper paper : paperList) {
            JSONObject jsonObject=new JSONObject();
            Integer paperId=paper.getId();
            List<String> keywords=paperMapper.queryKeywords(paperId);
            List<String> authors=paperMapper.queryAuthors(paperId);
            paper.setKeywords(keywords);
            paper.setAuthor(authors);
            jsonObject.put("data",paper);
            jsonObject.put("isLike",paperMapper.isLike(userId,paperId));
            jsonObjects.add(jsonObject);
        }
        Integer total=paperMapper.countAllByLike(userId);
        JSONObject result=new JSONObject();
        result.put("paper",jsonObjects);
        result.put("total",total);
        return result;
    }

    @Override
    public Integer isLike(Integer userId, Integer paperId) {
            return paperMapper.isLike(userId,paperId);
    }

    @Override
    public Integer countAllByKeyword(String keyword) {
        return paperMapper.countAllByKeyword(keyword);
    }

    @Override
    public Integer countAllByAuthor(String author) {
        return paperMapper.countAllByAuthor(author);
    }

    @Override
    public Integer countAllByTitle(String title) {
        return paperMapper.countAllByTitle(title);
    }

    @Override
    public Integer countAllByLike(Integer userId) {
        return paperMapper.countAllByLike(userId);
    }

    @Override
    public JSONArray getKeywordTrends() {
        List<String> keywords=paperMapper.selectHotWords();
        Integer[] years={2016,2017,2018,2019,2020};
        JSONArray result=new JSONArray();
        JSONArray jsonArray1=new JSONArray();
        jsonArray1.add("product");
        for (Integer year : years) {
            jsonArray1.add(String.valueOf(year));
        }
        result.add(jsonArray1);
        for (String keyword : keywords) {
            JSONArray jsonArray=new JSONArray();
            jsonArray.add(keyword);
            for (Integer year : years) {
                Integer count=paperMapper.countByYear(keyword,year);
                jsonArray.add(count);
            }
            result.add(jsonArray);
        }
        return result;
    }


}
