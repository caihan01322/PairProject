package com.pairing.service;

import com.pairing.bean.Paper;
import com.pairing.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;


    public List<Paper> getPaperByPageNum(int pageNum) {
        return paperMapper.getPaperByPageNum(pageNum * 4);
    }

    public int getPaperTotalNum() {
        return paperMapper.getTotalNum();
    }
}
