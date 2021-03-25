package com.pairing.controller;

import com.pairing.bean.HotWord;
import com.pairing.service.HotwordService;
import com.pairing.service.HotwordService;
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

//    @GetMapping("/hot_areas.html")
//    public String main(){
//        return "hot_areas";
//    }


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
            System.out.println("+$$$$$$$"+liststr.get(q));
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

//    @GetMapping("/json1")
//    @ResponseBody
//    public List<Worditem> getfirstjson(){
//
//    }



}
