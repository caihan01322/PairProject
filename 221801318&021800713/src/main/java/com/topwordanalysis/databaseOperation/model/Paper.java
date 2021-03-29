package com.topwordanalysis.databaseOperation.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Paper类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */

@NoArgsConstructor
@AllArgsConstructor
public class Paper {
    private int id;
    private String title;
    private String link;
    private String paperAbstract;   //这个如果用abstract命名会报错
    private String type;
    private String year;
    private String keyword;

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

    public int getid() {
        return id;
    }

    public void setid(int academicNum) {
        this.id = academicNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

