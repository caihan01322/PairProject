package com.pairing.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pairing.bean.HotWord;
import com.pairing.bean.NameAndYear;
import com.pairing.bean.Worditem;
import com.pairing.service.HotwordService;
import com.pairing.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HotwordController {

    @Autowired
    HotwordService hotwordService;

    /**
     * 返回热词统计页面
     * @return
     */
    @GetMapping("/hot_areas")
    public String hotAreas() {
        return "paperAnalyze/hot_areas";
    }

    /**
     * 返回走势对比界面
     * @return
     */
    @GetMapping("/trend_compare")
    public String trendCompare() {
        return "paperAnalyze/trend_compare";
    }

    /**
     * 预存入热门领域数据
     * @return
     */
    @GetMapping("/hot2")
    @ResponseBody
    public void getWord2() throws JsonProcessingException {

        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<HotWord> list = new ArrayList<>();
        String a = new String();
        List<String> liststr = new ArrayList<>();
        liststr = hotwordService.getAllWord();
        String str = new String();
        for (int q = 0; q < liststr.size(); q++) {

            a = liststr.get(q);//String
            if(a==null){
                continue;
            }
            str = a.replace("\"", "").replace("(","").replace(")","");

            String[] chars = new String[2000];
            chars = str.split(",");

            for (int j = 0; j < chars.length; j++) {

                if(hashMap.containsKey(chars[j].toLowerCase())){
                    hashMap.put(chars[j].toLowerCase(),hashMap.get(chars[j].toLowerCase())+1);
                }
                else {
                    hashMap.put(chars[j].toLowerCase(),1);
                }
            }
        }
        List<HashMap.Entry<String, Integer>> sortedList = getSortedList(hashMap);
        HashMap<String,Integer> hashMap2 = new HashMap<String,Integer>();
        int cnt = 0;
        for(HashMap.Entry<String,Integer> entry:sortedList) {
            hashMap2.put(entry.getKey(),entry.getValue());
            cnt++;
            if (cnt >= 10)//仅需要输出前十位
                break;
        }
        ObjectMapper mapper = new ObjectMapper();
        String jsonlist = mapper.writeValueAsString(hashMap2).toString();
        hotwordService.insertHotword(jsonlist);
    }

    /**
     * 返回前端热门领域数据
     * @param type 顶会类型
     * @return
     */
    @GetMapping("/hot")
    @ResponseBody
    public String getWord(@RequestParam(value = "type") String type) {

        String str = hotwordService.getHotwordjson(type);
        return str;
    }

    /**
     * 对全部热词进行排序
     * @param hashMap 存放关键词-数量
     * @return
     */
    public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String, Integer> hashMap) {
        List<HashMap.Entry<String, Integer>> list1 =
                new ArrayList<HashMap.Entry<String, Integer>>(hashMap.entrySet());
        Collections.sort(list1, new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> hash1, Map.Entry<String,Integer> hash2){
                if(hash1.getValue().equals(hash2.getValue()))
                    return hash1.getKey().compareTo(hash2.getKey());
                return hash2.getValue().compareTo(hash1.getValue());
            }
        });
        return list1;
    }

    /**
     * 对关键词数量及年份分别排序
     * @param hashMap 存放关键词-年份
     * @return
     */
    public static List<HashMap.Entry<String, Integer>> getSortedList2(HashMap<String, Integer> hashMap) {
        List<HashMap.Entry<String, Integer>> list1 = new ArrayList<HashMap.Entry<String, Integer>>(hashMap.entrySet());
        Collections.sort(list1, new Comparator<Map.Entry<String,Integer>>() {
            public int compare(Map.Entry<String,Integer> hash1, Map.Entry<String,Integer> hash2){
                if(hash1.getKey().split(",")[1].equals(hash2.getKey().split(",")[1]))
                    return hash2.getValue().compareTo(hash1.getValue());
                return hash1.getKey().split(",")[1].compareTo(hash2.getKey().split(",")[1]);
            }
        });
        return list1;
    }


    @Autowired
    TrendService trendService;

    /**
     * 返回动态柱状图所需第一个参数
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("/json1")
    @ResponseBody
    public String getFirstJson1() throws JsonProcessingException {
        String str =  trendService.getjson1();
        return str;
    }

    /**
     * 预存入动态柱状图所需第一个参数数据
     * @throws JsonProcessingException
     */
    @GetMapping("/json11")
    @ResponseBody
    public void getFirstJson() throws JsonProcessingException {
        List<List<String>> json2 = new ArrayList<>();
        List<String> jsonson2 = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<NameAndYear> keyandyear = new ArrayList<>();
        keyandyear = trendService.getYear();

        List<String> keywords = new ArrayList<>();
        List<String> publicationYear = new ArrayList<>();

        for (int i = 0;i<keyandyear.size();i++){
            String key = keyandyear.get(i).getKeywords();
            if (key == null) continue;
            else key = key.toLowerCase();
            String year = keyandyear.get(i).getPublicationYear();
            keywords.add(key);
            publicationYear.add(year);

        }

        String a = new String();
        String numm = new String();
        String str = new String();
        for (int q = 0; q < keywords.size(); q++) {
            numm = publicationYear.get(q);
            a = keywords.get(q);
            if(a==null){
                continue;
            }

            str = a.replace("\"", "").replace("(","").replace(")","");

            String[] chars = new String[2000];
            chars = str.split(",");
            String strr = new String();
            for (int j = 0; j < chars.length; j++) {
                strr = chars[j]+","+numm;
                if(hashMap.containsKey(strr)){
                    hashMap.put(strr,hashMap.get(strr)+1);
                }
                else {
                    hashMap.put(strr,1);
                }
            }
        }

        jsonson2.add("Income");
        jsonson2.add("Life Expectancy");
        jsonson2.add("Population");
        jsonson2.add("Country");
        jsonson2.add("Year");
        json2.add(jsonson2);

        List<HashMap.Entry<String, Integer>> count = getSortedList(hashMap);
        List<HashMap.Entry<String, Integer>> count2 = new ArrayList<>();
        count = getSortedList2(hashMap);//
        int size = count.size();
        HashMap<String ,Integer> yearnumhash = new HashMap<>();
        for(int l = 0;l<size;l++){
            String year = count.get(l).getKey().replace("\"", "").replace("(","").replace(")","").split(",")[1];
            if(yearnumhash.containsKey(year)){
                yearnumhash.put(year,yearnumhash.get(year)+1);
            }else {
                yearnumhash.put(year,1);
            }
            if(yearnumhash.get(year)<20){
                count2.add(count.get(l));
            }else {
                continue;
            }
        }
        List<Worditem>json11 = new ArrayList<>();
        HashMap<String,Integer> samehash = new HashMap<>();
        for(int y = 0;y<count2.size();y++){
            String name = count2.get(y).getKey().replace("\"", "").replace("(","").replace(")","").split(",")[0];
            if (samehash.containsKey(name)){
                continue;
            }else {
                String s = "*";
                Worditem w = new Worditem(s,s,"",name,"","");
                json11.add(w);
                samehash.put(name,1);
            }

        }
        ObjectMapper mapper2 = new ObjectMapper();
        String jsonlist = mapper2.writeValueAsString(json11).toString();
        trendService.insertTrend(jsonlist);
    }

    /**
     * 返回动态柱状图所需第二个参数
     * @return
     */
    @GetMapping("/json2")
    @ResponseBody
    public String getSecondJson(){
        String str =  trendService.getjson2();
        return str;
    }

    /**
     * 预存入动态柱状图所需第二个参数
     * @throws JsonProcessingException
     */
    @GetMapping("/json22")
    @ResponseBody
    public void getSecondJson2() throws JsonProcessingException {
        List<List<String>> json2 = new ArrayList<>();
        List<String> jsonson2 = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<NameAndYear> keyandyear = new ArrayList<>();
        keyandyear = trendService.getYear();

        List<String> keywords = new ArrayList<>();
        List<String> publicationYear = new ArrayList<>();

        for (int i = 0;i<keyandyear.size();i++){
            String key = keyandyear.get(i).getKeywords();
            if (key == null) continue;
            else key = key.toLowerCase();
            String year = keyandyear.get(i).getPublicationYear();
            keywords.add(key);
            publicationYear.add(year);

        }

        String a = new String();
        String numm = new String();
        String str = new String();
        for (int q = 0; q < keywords.size(); q++) {
            numm = publicationYear.get(q);
            a = keywords.get(q);
            if(a==null){
                continue;
            }

            str = a.replace("\"", "").replace("(","").replace(")","");

            String[] chars = new String[2000];
            chars = str.split(",");
            String strr = new String();
            for (int j = 0; j < chars.length; j++) {
                strr = chars[j]+","+numm;
                if(hashMap.containsKey(strr)){
                    hashMap.put(strr,hashMap.get(strr)+1);
                }
                else {
                    hashMap.put(strr,1);
                }
            }
        }

        jsonson2.add("Income");
        jsonson2.add("Life Expectancy");
        jsonson2.add("Population");
        jsonson2.add("Country");
        jsonson2.add("Year");
        json2.add(jsonson2);

        List<HashMap.Entry<String, Integer>> count = getSortedList(hashMap);
        List<HashMap.Entry<String, Integer>> count2 = new ArrayList<>();
        count = getSortedList2(hashMap);//
        int size = count.size();
        HashMap<String ,Integer> yearnumhash = new HashMap<>();
        for(int l = 0;l<size;l++){
            String year = count.get(l).getKey().replace("\"", "").replace("(","").replace(")","").split(",")[1];
            if(yearnumhash.containsKey(year)){
                yearnumhash.put(year,yearnumhash.get(year)+1);
            }else {
                yearnumhash.put(year,1);
            }
            if(yearnumhash.get(year)<21){
                count2.add(count.get(l));
            }else {
                continue;
            }
        }
        for(int y = 0;y<count2.size();y++){
            List<String> jsonson = new ArrayList<>();
            String name = count2.get(y).getKey().replace("\"", "").replace("(","").replace(")","").split(",")[0];
            String year = count2.get(y).getKey().replace("\"", "").replace("(","").replace(")","").split(",")[1];

            jsonson.add(String.valueOf(count2.get(y).getValue()));
            jsonson.add("");
            jsonson.add("");
            jsonson.add(name);
            jsonson.add(year);
            json2.add(jsonson);
        }
        ObjectMapper mapper3 = new ObjectMapper();
        String jsonlist = mapper3.writeValueAsString(json2).toString();
        trendService.insertTrend2(jsonlist);
    }
}
