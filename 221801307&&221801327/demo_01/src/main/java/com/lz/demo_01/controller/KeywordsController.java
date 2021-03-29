package com.lz.demo_01.controller;

import com.lz.demo_01.pojo.Keywords;
import com.lz.demo_01.service.KeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

@RestController
@RequestMapping("keywords")
public class KeywordsController {
    @Autowired
    private KeywordsService keywordsService;

    @RequestMapping("dataTozhuAndzhe")
    public Map<String, List> dataToPic2() {
        List<Keywords> list = keywordsService.findAllKeywords();
        Map<String, List> map = new HashMap<>();
        Map<String, Integer> ma = new HashMap<>();
        int num = 10;

        for (int i = 0; i < list.size(); i++) {
            Keywords keywords = list.get(i);
            if (ma.get(keywords.getKeyword()) != null) {
                int value = ma.get(keywords.getKeyword());
                value++;
                ma.put(keywords.getKeyword(), value);
            } else {
                ma.put(keywords.getKeyword(), 1);
            }

        }

        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(ma.entrySet());
        List<Integer> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        //idea自动转化成lambda表达式
        list3.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        //同词频单词表
        List<String> sameFrequency = new ArrayList<>();
        //输出统计
        int outputCount = 0;
        if (list3.size() < num) {
            num = list3.size();
        }
        if (list3.size() == 1) {
            list4.add(list3.get(0).getValue());
            list5.add(list3.get(0).getKey());
            map.put("x", list5);
            map.put("y", list4);
            return map;
        }

        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if ((i == list.size() - 1) || !list3.get(i).getValue().equals(list3.get(i + 1).getValue())) {
                //将当前单词加入同词频单词表
                sameFrequency.add(list3.get(i).getKey());
                //对同词频单词表排序
                sameFrequency.sort(String::compareTo);
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    list4.add(list3.get(i).getValue());
                    list5.add(s);
                    outputCount++;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list3.get(i).getKey());
        }
        map.put("x", list5);
        map.put("y", list4);
        //System.out.println(map);
        return map;
    }

    @RequestMapping("dataTobin")
    public Map<String, List<Map<String, String>>> dataToPic1() {
        List<Keywords> list = keywordsService.findAllKeywords();
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        Map<String, Integer> ma = new HashMap<>();

        int num = 10;

        for (int i = 0; i < list.size(); i++) {
            Keywords keywords = list.get(i);
            if (ma.get(keywords.getKeyword()) != null) {
                int value = ma.get(keywords.getKeyword());
                value++;
                ma.put(keywords.getKeyword(), value);
            } else {
                ma.put(keywords.getKeyword(), 1);
            }

        }

        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(ma.entrySet());
        Map<String, String> ma2 = new HashMap<>();
        List<Map<String, String>> listmap = new ArrayList<Map<String, String>>();
        //idea自动转化成lambda表达式
        list3.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        //同词频单词表
        List<String> sameFrequency = new ArrayList<>();
        //输出统计
        int outputCount = 0;
        if (list3.size() < num) {
            num = list3.size();
        }
        if (list3.size() == 1) {
            ma2.put("name", list3.get(0).getKey());
            ma2.put("value", list3.get(0).getValue().toString());
            listmap.add(ma2);
            map.put("data_pie", listmap);
            return map;
        }

        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if ((i == list.size() - 1) || !list3.get(i).getValue().equals(list3.get(i + 1).getValue())) {
                //将当前单词加入同词频单词表
                sameFrequency.add(list3.get(i).getKey());
                //对同词频单词表排序
                sameFrequency.sort(String::compareTo);
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    Map<String, String> mapp = new HashMap<>();
                    mapp.put("name", s);
                    mapp.put("value", list3.get(i).getValue().toString());
                    listmap.add(mapp);
                    outputCount++;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list3.get(i).getKey());
        }
        map.put("data_pie", listmap);
        //System.out.println(map);
        return map;
    }

    @RequestMapping("dataTozhuAndzhe31")
    public Map<String, List> dataToPic31() {
        List<Keywords> list = keywordsService.findAllKeywordsCVPR();
        //System.out.println(list);
        Map<String, List> map = new HashMap<>();
        Map<String, Integer> ma = new HashMap<>();
        int num = 10;

        for (int i = 0; i < list.size(); i++) {
            Keywords keywords = list.get(i);
            if (ma.get(keywords.getKeyword()) != null) {
                int value = ma.get(keywords.getKeyword());
                value++;
                ma.put(keywords.getKeyword(), value);
            } else {
                ma.put(keywords.getKeyword(), 1);
            }

        }

        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(ma.entrySet());
        List<Integer> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        //idea自动转化成lambda表达式
        list3.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        //同词频单词表
        List<String> sameFrequency = new ArrayList<>();
        //输出统计
        int outputCount = 0;
        if (list3.size() < num) {
            num = list3.size();
        }
        if (list3.size() == 1) {
            list4.add(list3.get(0).getValue());
            list5.add(list3.get(0).getKey());
            map.put("x", list5);
            map.put("y", list4);
            return map;
        }

        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if ((i == list.size() - 1) || !list3.get(i).getValue().equals(list3.get(i + 1).getValue())) {
                //将当前单词加入同词频单词表
                sameFrequency.add(list3.get(i).getKey());
                //对同词频单词表排序
                sameFrequency.sort(String::compareTo);
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    list4.add(list3.get(i).getValue());
                    list5.add(s);
                    outputCount++;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list3.get(i).getKey());
        }
        map.put("x", list5);
        map.put("y", list4);
        //System.out.println(map);
        return map;
    }

    @RequestMapping("dataTozhuAndzhe32")
    public Map<String, List> dataToPic32() {
        List<Keywords> list = keywordsService.findAllKeywordsICCV();
        //System.out.println(list);
        Map<String, List> map = new HashMap<>();
        Map<String, Integer> ma = new HashMap<>();
        int num = 10;

        for (int i = 0; i < list.size(); i++) {
            Keywords keywords = list.get(i);
            if (ma.get(keywords.getKeyword()) != null) {
                int value = ma.get(keywords.getKeyword());
                value++;
                ma.put(keywords.getKeyword(), value);
            } else {
                ma.put(keywords.getKeyword(), 1);
            }

        }

        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(ma.entrySet());
        List<Integer> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        //idea自动转化成lambda表达式
        list3.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        //同词频单词表
        List<String> sameFrequency = new ArrayList<>();
        //输出统计
        int outputCount = 0;
        if (list3.size() < num) {
            num = list3.size();
        }
        if (list3.size() == 1) {
            list4.add(list3.get(0).getValue());
            list5.add(list3.get(0).getKey());
            map.put("x", list5);
            map.put("y", list4);
            return map;
        }

        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if ((i == list.size() - 1) || !list3.get(i).getValue().equals(list3.get(i + 1).getValue())) {
                //将当前单词加入同词频单词表
                sameFrequency.add(list3.get(i).getKey());
                //对同词频单词表排序
                sameFrequency.sort(String::compareTo);
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    list4.add(list3.get(i).getValue());
                    list5.add(s);
                    outputCount++;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list3.get(i).getKey());
        }
        map.put("x", list5);
        map.put("y", list4);
        //System.out.println(map);
        return map;
    }

    @RequestMapping("dataTozhuAndzhe33")
    public Map<String, List> dataToPic33() {
        List<Keywords> list = keywordsService.findAllKeywordsECCV();
        //System.out.println(list);
        Map<String, List> map = new HashMap<>();
        Map<String, Integer> ma = new HashMap<>();
        int num = 10;

        for (int i = 0; i < list.size(); i++) {
            Keywords keywords = list.get(i);
            if (ma.get(keywords.getKeyword()) != null) {
                int value = ma.get(keywords.getKeyword());
                value++;
                ma.put(keywords.getKeyword(), value);
            } else {
                ma.put(keywords.getKeyword(), 1);
            }

        }

        //将HashMap中的包含映射关系的视图entrySet转换为List,然后重写比较器
        List<Map.Entry<String, Integer>> list3 = new ArrayList<>(ma.entrySet());
        List<Integer> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();
        //idea自动转化成lambda表达式
        list3.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //输出
        //同词频单词表
        List<String> sameFrequency = new ArrayList<>();
        //输出统计
        int outputCount = 0;
        if (list3.size() < num) {
            num = list3.size();
        }
        if (list3.size() == 1) {
            list4.add(list3.get(0).getValue());
            list5.add(list3.get(0).getKey());
            map.put("x", list5);
            map.put("y", list4);
            return map;
        }

        for (int i = 0; i < list.size() && outputCount < num; i++) {
            //如果当前字符词频与下一个不一样，则对当前所有同词频单词排序
            if ((i == list.size() - 1) || !list3.get(i).getValue().equals(list3.get(i + 1).getValue())) {
                //将当前单词加入同词频单词表
                sameFrequency.add(list3.get(i).getKey());
                //对同词频单词表排序
                sameFrequency.sort(String::compareTo);
                //按字典顺序记录同词频单词
                for (String s : sameFrequency) {
                    list4.add(list3.get(i).getValue());
                    list5.add(s);
                    outputCount++;
                }
                sameFrequency.clear();
            } else sameFrequency.add(list3.get(i).getKey());
        }
        map.put("x", list5);
        map.put("y", list4);
        //System.out.println(map);
        return map;
    }
}
