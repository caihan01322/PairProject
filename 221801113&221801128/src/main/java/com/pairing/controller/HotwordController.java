package com.pairing.controller;

import com.pairing.bean.HotWord;
import com.pairing.bean.NameAndYear;
import com.pairing.bean.Worditem;
import com.pairing.service.HotwordService;
import com.pairing.service.HotwordService;
import com.pairing.service.TrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class HotwordController {

    @Autowired
    HotwordService hotwordService;

    @GetMapping("/hot_areas")
    public String hot_areas() {
        return "/paperAnalyze/hot_areas";
    }

    @GetMapping("/trend_compare")
    public String trend_compare() {
        return "/paperAnalyze/trend_compare";
    }




    @GetMapping("/hot")
    @ResponseBody
    public HashMap<String,Integer> getword() {

        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<HotWord> list = new ArrayList<>();
        String a = new String();
        List<String> liststr = new ArrayList<>();
        liststr = hotwordService.getAllWord();
        String str = new String();
        for (int q = 0; q < liststr.size(); q++) {

            a = liststr.get(q);//String
            if(a==null){
                break;
            }
            str = a.replace("\"", "");

            String[] chars = new String[2000];
            chars = str.split(",");

            for (int j = 0; j < chars.length; j++) {
               if(hashMap.containsKey(chars[j])){
                   hashMap.put(chars[j],hashMap.get(chars[j])+1);
               }
               else {
                   hashMap.put(chars[j],1);
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
        return hashMap2;
    }


    //part2走势对比


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

    @GetMapping("/json1")
    @ResponseBody
    public List<Worditem> getfirstjson(){
        List<List<String>> json2 = new ArrayList<>();
        List<String> jsonson2 = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<NameAndYear> keyandyear = new ArrayList<>();
        keyandyear = trendService.getYear();

        List<String> keywords = new ArrayList<>();
        List<String> publicationYear = new ArrayList<>();

        for (int i = 0;i<keyandyear.size();i++){
            String key = keyandyear.get(i).getKeywords();
            String year = keyandyear.get(i).getPublicationYear();
            keywords.add(key);
            publicationYear.add(year);

        }

        String a = new String();
        String numm = new String();
        String str = new String();
        for (int q = 0; q < keywords.size(); q++) {
            numm = publicationYear.get(q);
            //System.out.println(numm);
            a = keywords.get(q);//String
            if(a==null){
                continue;
            }

            str = a.replace("\"", "");

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
            String year = count.get(l).getKey().replace("\"", "").split(",")[1];
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
            String name = count2.get(y).getKey().replace("\"", "").split(",")[0];
            if (samehash.containsKey(name)){
                continue;
            }else {
                String s = "*";
                Worditem w = new Worditem(s,s,"",name,"","");
                json11.add(w);
                samehash.put(name,1);
            }

        }
        return json11;//可以输出

    }
    //第二个json文件的获取
    @GetMapping("/json2")
    @ResponseBody
    public List<List<String>> getsecondjson(){
        List<List<String>> json2 = new ArrayList<>();
        List<String> jsonson2 = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<NameAndYear> keyandyear = new ArrayList<>();
        keyandyear = trendService.getYear();

        List<String> keywords = new ArrayList<>();
        List<String> publicationYear = new ArrayList<>();

        for (int i = 0;i<keyandyear.size();i++){
            String key = keyandyear.get(i).getKeywords();
            String year = keyandyear.get(i).getPublicationYear();
            keywords.add(key);
            publicationYear.add(year);

        }

        String a = new String();
        String numm = new String();
        String str = new String();
        for (int q = 0; q < keywords.size(); q++) {
            numm = publicationYear.get(q);
            //System.out.println(numm);
            a = keywords.get(q);//String
            if(a==null){
                continue;
            }

            str = a.replace("\"", "");

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
            String year = count.get(l).getKey().replace("\"", "").split(",")[1];
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
            String name = count2.get(y).getKey().replace("\"", "").split(",")[0];
            String year = count2.get(y).getKey().replace("\"", "").split(",")[1];

            jsonson.add(String.valueOf(count2.get(y).getValue()));
            jsonson.add("");
            jsonson.add("");
            jsonson.add(name);
            jsonson.add(year);
            json2.add(jsonson);
        }
        return json2;
    }


}
