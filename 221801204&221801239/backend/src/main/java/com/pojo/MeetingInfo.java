package com.pojo;

import org.apache.ibatis.type.Alias;

@Alias("meetinginfo")
public class MeetingInfo
{
    private int publication_year;
    private String magazine;
    private int number;

    public MeetingInfo(int publication_year, String magazine, int number)
    {
        this.publication_year = publication_year;
        this.magazine = magazine;
        this.number = number;
    }

    public MeetingInfo() {
    }

    @Override
    public String toString() {
        return "MeetingInfo{" +
                "publish_year=" + publication_year +
                ", magazine='" + magazine + '\'' +
                ", number=" + number +
                '}';
    }

    public int getPublish_year() {
        return publication_year;
    }

    public void setPublish_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
