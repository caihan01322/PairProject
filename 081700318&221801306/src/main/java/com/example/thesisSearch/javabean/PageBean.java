package com.example.thesisSearch.javabean;

import com.example.thesisSearch.pojo.Thesis;

import java.util.List;

public class PageBean {
    private int PageNum;
    private int PageSize;
    private int TotalRecord;
    private int TotalPage;
    private String SearchType;
    private String Input;
    private List<Thesis> list;

    public PageBean(int PageNum,int PageSize,int TotalRecord,List<Thesis> list,String SearchType,String Input)
    {
        this.PageNum=PageNum;
        this.PageSize=PageSize;
        this.TotalRecord=TotalRecord;
        this.TotalPage= (int) Math.ceil((double)TotalRecord/(double) PageSize);
        this.list=list;
        this.SearchType=SearchType;
        this.Input=Input;
    }

    public String getInput() {
        return Input;
    }

    public String getSearchType() {
        return SearchType;
    }

    public int getPageNum() {
        return PageNum;
    }

    public int getPageSize() {
        return PageSize;
    }

    public int getTotalPage() {
        return TotalPage;
    }

    public void setList(List<Thesis> list) {
        this.list = list;
    }

    public void setPageNum(int pageNum) {
        PageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        TotalPage = totalPage;
    }

    public List<Thesis> getList() {
        return list;
    }
}
