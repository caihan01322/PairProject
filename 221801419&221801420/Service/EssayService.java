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
import Entity.Keywords;


public class EssayService {
    
   public static EssayDaoImpl e = new EssayDaoImpl();//封装了对数据库有关论文的操作
   
   //论文收藏
   public static String essayCollect(String userName,String essayMeeting,String essayName) {
       EssayCollectDaoImpl e = new EssayCollectDaoImpl();
       String answer = e.collect(essayName, essayMeeting, userName);
       return answer;
   }

   
}
