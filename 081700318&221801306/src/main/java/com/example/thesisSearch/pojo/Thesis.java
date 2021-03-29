package com.example.thesisSearch.pojo;


import java.util.List;
//用于显示论文的包装类
public class Thesis {
    private  String title;
    private  String date;
    private  int year;
    private  String link;
    private  String meeting;
    private  String abstractContent;
    private  int id;
    private String keyword;
    private  boolean isliked;
    private  List<String> keywordList;

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setIsliked(boolean isliked) {
        this.isliked = isliked;
    }

    public boolean isIsliked() {
        return isliked;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public String getDate() {
        return date;
    }

    public String getLink() {
        return link;
    }

    public String getMeeting() {
        return meeting;
    }

    public String getTitle() {
        return title;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

}