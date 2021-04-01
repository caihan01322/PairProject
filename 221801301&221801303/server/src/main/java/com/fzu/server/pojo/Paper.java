package com.fzu.server.pojo;

import java.util.ArrayList;
import java.util.List;

public class Paper {
    int ID;
    int meeting;//0为CVPR,1为ECCV,2为ICCV
    String Abstract;
    String year;
    String time;
    String name;
    List<String> author;
    String link;
    List<String> keyword;


    public Paper() {

    }

    public Paper(int ID,int meeting, String Abstract, String year, String time, String name, List<String> author, String link, List<String> keyword) {
        this.ID = ID;
        this.meeting = meeting;
        this.Abstract = Abstract;
        this.year = year;
        this.time = time;
        this.name = name;
        this.author = new ArrayList<>(author);
        this.link = link;
        this.keyword = new ArrayList<>(keyword);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setMeeting(int meeting) {
        this.meeting = meeting;
    }

    public int getMeeting() {
        return meeting;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public List<String> getKeyword() {
        return keyword;
    }


}
