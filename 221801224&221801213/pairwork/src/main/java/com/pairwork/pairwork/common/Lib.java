package com.pairwork.pairwork.common;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lib {

    //private File file;
    private String content;
    private int charNum;
    private int wordNum;
    private int lineNum;
    private List<String> list = new ArrayList<String>();
    private Map<String,Integer> map = new TreeMap<String, Integer>();//利用 TreeMap进行统计并且排序
    private String[] wordSet = new String[11];
//    private int[] freSet = new int[11];
    private  List<Integer> freSet = new ArrayList<Integer>();
    public Lib(String con){
        content = con;
        charNum = 0;
        lineNum = 0;
        wordNum = 0;
        calculate();
        addMap();
        mapSort(map);
    }

    public void addList(String srl) {//string from readline 将判定为的单词放入List中
        srl = srl.trim();
        //String[] wordArray = srl.split("[a-zA-Z]{4}([a-zA-Z0-9])*");
        //String[] wordArray = srl.split("[0-9A-Za-z]+");

        String [] wordArray  = srl.split("\\W+");//分割单词
        Pattern p = Pattern.compile("[a-zA-Z]{4}([a-zA-Z0-9])*");//匹配以>=4位字母开头
        for (String listWord : wordArray) {
            Matcher m = p.matcher(listWord);
            if (listWord.length() != 0 && m.find()) {
                list.add(listWord);
                wordNum++;
            }
        }
    }

    public void addMap() {//将筛选好的单词放入map中，若存在则value+1，不存在则设value=1
        for (String mapWord : list) {
            if(map.get(mapWord) != null) {
                map.put(mapWord, map.get(mapWord) + 1);
            }else{
                map.put(mapWord,1);
            }
            //System.ot.println(mapWord);
        }
        //mapSort(map);
    }

    public  void mapSort(Map<String,Integer> oldmap){//定义比较器根据value的值降序排列
        ArrayList<Map.Entry<String, Integer>> newList = new ArrayList<>(oldmap.entrySet());
        Collections.sort(newList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();//降序
            }
        });

            for (int i = 0; i < (newList.size() > 10 ? 10 : newList.size()); i++) {
                wordSet[i] = newList.get(i).getKey();
//                freSet[i] = newList.get(i).getValue();
                freSet.add(newList.get(i).getValue());
            }

    }

    public  void calculate(){//计算行数，并将每行的字符串送去判定
        //List<String> list = new ArrayList<String>();
        int lineCount = 0;
        String result = " ";
//            BufferedReader br = new BufferedReader(new FileReader(content));//构造一个BufferedReader类来读取文件
            String s = null;
            String []ss = content.split("\n");
            for(int i= 0;i < ss.length;i++) {//使用readLine方法，一次读一行
                s = ss[i];
                addList(s);
                s.toLowerCase();
                //charNum+= s.length();这样读取导致错误
                if (s.trim().length() == 0) continue;
                else {
                    result = result + "\n" + s;
                    lineCount++;
                }
            }
        lineNum = lineCount;
    }


    public int getChars(){//返回字符数

        return content.getBytes(StandardCharsets.UTF_8).length;
    }

    public int getLines(){//返回行数
        return lineNum;
    }
    public int getWords(){//返回单词数
        return wordNum;
    }
    public String[] getWordFreK(){//返回词频前十的键
        return  wordSet;
    }
    public List<Integer> getWordFreV(){//返回词频前十的值
        return freSet;
    }
}
