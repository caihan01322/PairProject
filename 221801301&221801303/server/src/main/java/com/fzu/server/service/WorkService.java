package com.fzu.server.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fzu.server.dao.PaperDao;
import com.fzu.server.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkService {
    @Autowired
    PaperDao dao;
    int count;

    public Object queryName(Map<String, Object> req) {
//        System.out.println(name.get("name"));
//        Object data = name.get("data");
//        Map<String,Object> map = (Map<String, Object>) data;
//        System.out.println(map.get("d1"));
//        List<String> arr = (List<String>) map.get("d2");
//        for (String s : arr) {
//            System.out.println(s);
//        }
        String name;
        if(req.get("name")==""||req.get("name")==null)
            name="computer";
        else
            name=req.get("name").toString();

        int page = (int) req.get("page");
        int limit = (int) req.get("limit");
        int start = (page - 1) * limit;
        if(page==1)count=dao.getCount(name,0);
        List<Paper> paper = dao.getPaperByName(name, start, limit);
        for (Paper pp : paper) {
            pp.setKeyword(dao.getKeyword(pp.getID()));
            pp.setAuthor(dao.getAuthor(pp.getID()));

        }
        Map<String,Object> result=new HashMap<>();
        result.put("data",paper);
        result.put("count",count);

        return JSONObject.toJSON(result);
    }

    public Object queryAuthor(Map<String, Object> req) {
        String author;
        author = req.get("author").toString();
        int page = (int) req.get("page");
        int limit = (int) req.get("limit");
        int start = (page - 1) * limit;
        if (page == 1) count = dao.getCount(author, 1);
        List<Paper> paper = dao.getPaperByAuthor(author, start, limit);
        for (Paper pp : paper) {
            pp.setKeyword(dao.getKeyword(pp.getID()));
            pp.setAuthor(dao.getAuthor(pp.getID()));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("data", paper);

        return JSONObject.toJSON(result);
    }

    public Object getCVPR() {
        List<Map<String, String>> cvpr = dao.getCVPR();
        Object obj = JSONArray.toJSON(cvpr);
        return obj;
    }

    public Object getECCV() {
        List<Map<String, String>> eccv = dao.getECCV();
        Object obj = JSONArray.toJSON(eccv);
        return obj;
    }

    public Object getICCV() {
        List<Map<String, String>> iccv = dao.getICCV();
        Object obj = JSONArray.toJSON(iccv);
        return obj;
    }

    public Object getDetail(Map<String,Object> req){
        int id = (int)req.get("id");
        Paper paper = dao.getDetail(id);
        paper.setAuthor(dao.getAuthor(id));
        paper.setKeyword(dao.getKeyword(id));
        return JSONObject.toJSON(paper);
    }
}
