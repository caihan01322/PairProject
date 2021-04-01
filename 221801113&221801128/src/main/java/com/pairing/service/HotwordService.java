package com.pairing.service;


import com.pairing.mapper.HotwordMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotwordService {

    @Autowired
    HotwordMapper hotwordMapper;

    /**
     * 获取数据库所有热词
     * @return
     */
    public List<String> getAllWord(){
        return hotwordMapper.getAllWord();
    }

    /**
     * 预存入数据
     * @param json
     */
    public void insertHotword(String json){
        hotwordMapper.insertHotword(json);
    }

    /**
     * 得到top10热词
     * @param type
     * @return
     */
    public String getHotwordjson(String type){
        return hotwordMapper.getHotwordjson(type);
    }
}
