package Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Dao.EssayCollectDaoImpl;
import Dao.EssayDaoImpl;
import Dao.EssayDeleteDaoImpl;
import Entity.Essay;
import Entity.Keywords;


public class EssayService {
    
   public static EssayDaoImpl e = new EssayDaoImpl();//封装了对数据库有关论文的操作
   
   //论文收藏
   public static String essayCollect(String userName,String essayMeeting,String essayName) {
       EssayCollectDaoImpl e = new EssayCollectDaoImpl();
       String answer = e.collect(essayName, essayMeeting, userName);
       return answer;
   }
   
   //将论文从论文列表中删除
   public static String essayDelete(String userName,String essayMeeting,String essayName) {
       EssayDeleteDaoImpl e = new EssayDeleteDaoImpl();
       String answer = e.delete(userName, essayName, essayMeeting);
       return answer;
   }
   
   //获取不同年份，不同顶会的top10关键词
   public static List<Keywords> keywordsGet(String keywordsType,String year) {
       ArrayList<Keywords> k = new ArrayList<>();
       switch (keywordsType) {
           case "cvpr":
               if (year.equals("all")) {
                   k = (ArrayList<Keywords>) e.keywordsGetCVPRALL();
               }else {
                   k = (ArrayList<Keywords>) e.keywordsGetCVPR(year);
               }
               break;
           case "eccv":
               if (year.endsWith("all")) {
                   k = (ArrayList<Keywords>) e.keywordsGetECCVALL();
               }else {
                   k = (ArrayList<Keywords>) e.keywordsGetECCV(year);
               }
               break;
           case "iccv":
               if (year.equals("all")) {
                   k = (ArrayList<Keywords>) e.keywordsGetICCVALL();
               }else {
                   k = (ArrayList<Keywords>) e.keywordsGetICCV(year);
               }
               break;
           default :
               k = (ArrayList<Keywords>) e.keywordsGetMY(keywordsType);
               break;
       }
       String str = "";
       for (int i = 0;i<k.size();i++) {
           str = str + k.get(i).getKeywords();
       }
       //引用wordcount代码稍加修改
       Map<String,Integer> wordsMap = new TreeMap<>();
       String ragex = "([A-Za-z]+(\\s)*)+";
       Pattern p = Pattern.compile(ragex);
       Matcher m = p.matcher(str);
       while (m.find()) {
            String s = m.group();
            if (wordsMap.containsKey(s)) {
                int num = wordsMap.get(s);
                wordsMap.put(s, num + 1);
            }else {
                wordsMap.put(s, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet()); 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });    
        ArrayList<Keywords> temp = new ArrayList<>();
        for (int i = 0;i < 10&&i<list.size();i ++) {
            Keywords key = new Keywords();
            key.setFrequency(list.get(i).getValue());
            key.setKeywords(list.get(i).getKey());
            temp.add(key);
        }
       return temp;
   }
   
   //论文标签搜索
   public static List<Essay> labelSearch(String serachStr,String searchLabel,String username){
       ArrayList<Essay> essays = new ArrayList<>();
       switch (searchLabel) {
           case "title":
               essays = (ArrayList<Essay>) e.labelSearchByTitle(serachStr, username);
               break;
           case "keywords":
               essays = (ArrayList<Essay>) e.labelSearchByKeywords(serachStr, username);
               break;
           case "articlenum":
               essays = (ArrayList<Essay>) e.labelSearchByNum(serachStr, username);
               break;
           default:
               break;
       }
       return essays;
   }
   
}
