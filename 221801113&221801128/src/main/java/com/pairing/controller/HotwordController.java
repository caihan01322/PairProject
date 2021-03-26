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
//

        return hashMap2;
    }

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


    //part2走势对比

    @Autowired
    TrendService trendService;

    @GetMapping("/json1")
    @ResponseBody
    public List<Worditem> getfirstjson(){
        List<Worditem>json11 = new ArrayList<>();
        List<List<String>> json1 = new ArrayList<>();
        List<String> code = new ArrayList<>();
        List<String> emoji = new ArrayList<>();
        List<String> unicode = new ArrayList<>();
        List<String> title = new ArrayList<>();
        List<String> dialCode = new ArrayList<>();
        List<String> keywords = new ArrayList<>();
        List<String> name = new ArrayList<>();
        HashMap<String,Integer> hashMap = new HashMap<String,Integer>();
        List<String> publicationYear = new ArrayList<>();
        List<NameAndYear> keyandyear = new ArrayList<>();
        keyandyear = trendService.getYear();
        //添加两个key和year
        for (int i = 0;i<keyandyear.size();i++){
            String key = keyandyear.get(i).getKeywords();
            String year = keyandyear.get(i).getPublicationYear();
            keywords.add(key);//在这里添加第二个json!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            publicationYear.add(year);
        }
        //取到每个词
        String a = new String();
        String str = new String();
        for (int q = 0; q < keywords.size(); q++) {
            a = keywords.get(q);//String
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
        String strr = new String();
        for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
            strr = entry.getKey();
            name.add(strr);
        }
        for(int i = 0;i<name.size();i++){
            Worditem w = new Worditem("","","",name.get(i),"","");
            json11.add(w);
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
        String strr2 = new String();
        int number = 0;
        jsonson2.add("Income");
        jsonson2.add("Life Expectancy");
        jsonson2.add("Population");
        jsonson2.add("Country");
        jsonson2.add("Year");
        json2.add(jsonson2);
        for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
            List<String> jsonson = new ArrayList<>();
            number = entry.getValue();
            String strnum = new String();
            strnum = String.valueOf(number);
            strr2 = entry.getKey().replace("\"", "");;
            String[] char2 = new String[2];
            char2 = strr2.split(",");
            jsonson.add(strnum);
            jsonson.add("");
            jsonson.add("");
            jsonson.add(char2[0]);
            jsonson.add(char2[1]);
            json2.add(jsonson);

        }
        return json2;
    }


}
