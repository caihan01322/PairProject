package com.pair.model;

import java.io.Serializable;

public class Thesis implements Serializable {
    private int id;
    private String title;
    private String abstractContent;
    private String meet;
    private String year;
    private String link;
    private String keyword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Thesis{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", abstractContent='" + abstractContent + '\'' +
                ", meet='" + meet + '\'' +
                ", year='" + year + '\'' +
                ", link='" + link + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
