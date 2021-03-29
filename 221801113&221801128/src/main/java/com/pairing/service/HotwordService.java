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

    public List<String> getAllWord(){
        return hotwordMapper.getAllWord();
    }

    public void insertHotword(String json){
        hotwordMapper.insertHotword(json);
    }

    public String getHotwordjson(){
        return hotwordMapper.getHotwordjson();
    }
}
