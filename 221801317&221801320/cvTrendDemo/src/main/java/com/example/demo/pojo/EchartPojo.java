package com.example.demo.pojo;

import java.util.List;
import java.util.Map;

public class EchartPojo {

    List<Keyword> keywords;
    List<Map<String,Long>> trends;

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<Map<String, Long>> getTrends() {
        return trends;
    }

    public void setTrends(List<Map<String, Long>> trends) {
        this.trends = trends;
    }
}
