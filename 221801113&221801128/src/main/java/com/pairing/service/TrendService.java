package com.pairing.service;

import com.pairing.bean.NameAndYear;
import com.pairing.mapper.TrendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrendService {



    @Autowired
    TrendMapper trendMapper;

    public List<NameAndYear> getYear(){
        return trendMapper.getYear();
    }

    public void insertTrend(String json){
        trendMapper.insertTrend(json);
    }
    public void insertTrend2(String json){
        trendMapper.insertTrend2(json);
    }

    public String getjson1(){
        return trendMapper.getjson1();
    }
    public String getjson2(){
        return trendMapper.getjson2();
    }
}
