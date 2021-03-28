package com.fzu.server.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkService {
    public void query(Map<String,Object>name){
        System.out.println(name.get("name"));
        Object data = name.get("data");
        Map<String,Object> map = (Map<String, Object>) data;
        System.out.println(map.get("d1"));
        List<String> arr = (List<String>) map.get("d2");
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
