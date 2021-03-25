package com.fzu.server.pojo;

import java.util.ArrayList;
import java.util.List;

public class ECCV {
    int ID;
    String Abstract;
    String year;
    String time;
    String name;
    String link;
    List<String> keyword;

    public ECCV() {

    }

    public ECCV(int ID, String Abstract, String year, String time, String name, String link, List<String> keyword) {
        this.ID = ID;
        this.Abstract = Abstract;
        this.year = year;
        this.time = time;
        this.name = name;
        this.link = link;
        this.keyword = new ArrayList<String>(keyword);
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
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
