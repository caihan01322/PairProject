package com.topwordanalysis.databaseOperation.model;

/**
 * TopWord类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class TopWord {
    private String academicNum;
    private String topWord;
    private int times;

    public String getAcademicNum() {
        return academicNum;
    }

    public void setAcademicNum(String academicNum) {
        this.academicNum = academicNum;
    }

    public String getTopWord() {
        return topWord;
    }

    public void setTopWord(String topWord) {
        this.topWord = topWord;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
