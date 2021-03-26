package com.example.demo.pojo;

import pojo.Paper;

import java.util.LinkedList;
import java.util.List;

public class Pager {
    public static final int PAGE_NUM = 10;
    List<Paper> papers;
    int curPage;
    int nextPage;
    int prePage;
    int totalPageNum;
    List<Integer> shownPage;

    public Pager(List<Paper> papers, int curPage, int totalPageNum) {
        this.papers = papers;
        this.curPage = curPage;
        this.nextPage = (curPage+1)<totalPageNum?curPage+1:totalPageNum;
        this.prePage = (curPage-1)>0?curPage-1:1;
        this.totalPageNum = totalPageNum;
        this.shownPage = new LinkedList<>();
        for (int i = curPage-2; i <= curPage+2 ; i++){
            if (i>0&&i<=totalPageNum){
                shownPage.add(i);
            }
        }
    }


    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<Integer> getShownPage() {
        return shownPage;
    }

    public void setShownPage(List<Integer> shownPage) {
        this.shownPage = shownPage;
    }
}
