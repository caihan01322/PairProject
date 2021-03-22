package com.example.demo.pojo;

public class PaperKeyword {
    String paperKeywordID;
    int paperID;
    String keyword;
    String meeting;
    String year;
    long frequency;

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public String getPaperKeywordID() {
        return paperKeywordID;
    }

    public void setPaperKeywordID(String paperKeywordID) {
        this.paperKeywordID = paperKeywordID;
    }

    public int getPaperID() {
        return paperID;
    }

    public void setPaperID(int paperID) {
        this.paperID = paperID;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
