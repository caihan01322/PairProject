package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordCountUtils {
    
    /*
     * 用比较器实现单词排序
     * @param String
     * @return List
     * */
    public static List<String> sortHashmap(String str) {
        Map<String, Integer> wordsMap = new HashMap<String, Integer>();
        String[] strs = str.split(",");
        for(int i = 0; i < strs.length; i++) {
            String temp = strs[i].toLowerCase();
            if(wordsMap.containsKey(temp)) {
                int num = wordsMap.get(temp);
                wordsMap.put(temp, 1 + num);
                }
            else {
                wordsMap.put(temp, 1);
                }
            }
        //将words.entrySet()转换为list
        List<Map.Entry<String, Integer>> list;
        list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        //通过比较器实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> m1, Entry<String, Integer> m2) {
                //按照字典序以及value的值排序
                if(m1.getValue().equals(m2.getValue())) {
                    return m1.getKey().compareTo(m2.getKey());
                    }else return m2.getValue()-m1.getValue();
                }
            }); 
        int  i = 0;
        List<String> kwdsList = new ArrayList();
        for(Map.Entry<String, Integer> map : list) {
            if(i < 10) {
                kwdsList.add(map.getKey());
                i++;
                }else break;
            }
        return kwdsList;
    }
}
