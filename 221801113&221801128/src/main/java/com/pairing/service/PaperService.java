package com.pairing.service;

import com.pairing.bean.Paper;
import com.pairing.mapper.PaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;

    /**
     * 获取paper的list和paper的总数
     * @param searchInfo
     * @param pageNum
     * @return
     */
    public Map<List<Paper>, Integer> getPaper(String searchInfo, int pageNum) {
        Map<List<Paper>, Integer> map= new HashMap<>();
        map.put(paperMapper.getPaper(searchInfo, Integer.valueOf(pageNum * 4)), paperMapper.getPaperCount(searchInfo));
        return map;
    }

}
