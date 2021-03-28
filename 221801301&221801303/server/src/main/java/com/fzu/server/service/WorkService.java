package com.fzu.server.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.server.dao.PaperDao;
import com.fzu.server.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkService {
    @Autowired
    PaperDao dao;
    public Object query(Map<String,Object>name){
//        System.out.println(name.get("name"));
//        Object data = name.get("data");
//        Map<String,Object> map = (Map<String, Object>) data;
//        System.out.println(map.get("d1"));
//        List<String> arr = (List<String>) map.get("d2");
//        for (String s : arr) {
//            System.out.println(s);
//        }
        List<Paper> paper = dao.getPaper(name.get("name").toString());
        for (Paper paper1 : paper) {
            paper1.setKeyword(dao.getKeyword(paper1.getID()));

        }
        Object obj = JSONArray.toJSON(paper);
        return obj;
    }
}
