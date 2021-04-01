package com.example.thesisSearch.pojo;

import java.io.Serializable;

public class HotWord implements Serializable {
    private  String keyword;
    private  int nums;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getNums() {
        return nums;
    }
}
