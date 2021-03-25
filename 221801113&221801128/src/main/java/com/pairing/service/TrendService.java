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
}
