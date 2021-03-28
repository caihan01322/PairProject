package com.example.thesisSearch.service;

import com.example.thesisSearch.dao.LikeListDao;
import com.example.thesisSearch.dao.ThesisDAO;
import com.example.thesisSearch.javabean.PageBean;
import com.example.thesisSearch.pojo.Thesis;

import java.util.List;

public class SearchService {
      static  public int pagesize=5;
      static  public  PageBean search(String type, String input,ThesisDAO SearchThesisDAO,int PageNum)
    {
        int TotalResultNum;
        int SearchStart;
        int SearchLength;
        PageBean SearchResult=null;
             TotalResultNum=SearchThesisDAO.getNum(input,type);
             SearchStart=(PageNum-1)*pagesize;
             SearchLength=PageNum*pagesize<TotalResultNum?5:TotalResultNum-(PageNum-1)*pagesize;
            SearchResult=new PageBean(PageNum,pagesize,TotalResultNum,SearchThesisDAO.getLimit(SearchStart,SearchLength,input,type),type,input);//构建一个pagebean并且传出
        for(Thesis t:SearchResult.getList())
        {
            LikeListDao lld=new LikeListDao();
            t.setIsliked(lld.isliked(t.getId()));
        }


        return  SearchResult;
    }
}
