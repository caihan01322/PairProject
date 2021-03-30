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

    /**
     * 获取关键词年份对象
     * @return
     */
    public List<NameAndYear> getYear(){
        return trendMapper.getYear();
    }

    /**
     * 预存入动态柱状图参数
     * @param json
     */
    public void insertTrend(String json){
        trendMapper.insertTrend(json);
    }
    public void insertTrend2(String json){
        trendMapper.insertTrend2(json);
    }

    /**
     * 得到动态柱状图数据
     * @return
     */
    public String getjson1(){
        return trendMapper.getjson1();
    }
    public String getjson2(){
        return trendMapper.getjson2();
    }
}
