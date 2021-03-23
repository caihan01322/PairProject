package com.topwordanalysis.databaseOperation.model;


/**
 * Paper类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */



public class Paper {
    private String title;
    private String link;
    private String paperAbstract;
    private String academicNum;
    private String type;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getAcademicNum() {
        return academicNum;
    }

    public void setAcademicNum(String academicNum) {
        this.academicNum = academicNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

