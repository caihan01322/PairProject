package com.eepractice.webcrawller.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eepractice.webcrawller.bean.Item;
import com.eepractice.webcrawller.bean.ResultItem;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class DataUtils {

    static int  i = 0;

    private static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder sb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> readAuthors(JSONArray authors){

        List<String> authorList = new ArrayList<>();
        for (int i = 0 ; i < authors.size();i++){
            JSONObject author = authors.getJSONObject(i);
            String authorName = author.getString("name");
            authorList.add(authorName);
        }
        return authorList;
    }
    private static List<String> readKeywords(JSONArray keywords){

        List<String> kwdList = new ArrayList<>();
        for (int i = 0 ; i < keywords.size();i++){
            JSONObject author = keywords.getJSONObject(i);
            String[] kwds = author.getObject("kwd",String[].class);
            kwdList.addAll(Arrays.asList(kwds));
        }
        return kwdList;
    }


    private static Item mapToItem(JSONObject jobj){
        Item item = new Item();
        // 读取作者
        JSONArray authors = jobj.getJSONArray("authors");
        List<String> authorList = readAuthors(authors);

        // 读取ISBN
        String isbn = jobj.getString("articleNumber");

        // 读取Title
        String title = jobj.getString("formulaStrippedArticleTitle");

        // 读取摘要
        String note = "论文摘要 : "+jobj.getString("abstract");

        // 读取会议名，日期
        String publisher = jobj.getString("publisher");
        String conferenceDate = jobj.getString("conferenceDate");

        // 读取连接
        String link = jobj.getString("doiLink");

        // 读取关键字
        JSONArray keywordsArr = jobj.getJSONArray("keywords");
        List<String> keywords = new ArrayList<>();
        if (keywordsArr != null){
            keywords = readKeywords(keywordsArr);
        }

        item.setAuthor(authorList)
            .setConferenceDate(conferenceDate)
            .setKeyword(keywords)
            .setId(isbn)
            .setPublisher(publisher)
            .setLink(link)
            .setTitle(title)
            .setNote(note);

        return item;
    }


        public static List<Item> readData(String targetFile){

        List<Item> itemList = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\src\\main\\resources\\static\\" + targetFile;
        File file = new File(filePath);
        File[] jsonFiles = file.listFiles();
        int err = 0;
        for(int i = 0; i < jsonFiles.length ; i ++){
            File f = null;
            try{
                f = jsonFiles[i];
                String s = readJsonFile(f.getPath());
                JSONObject jobj = JSON.parseObject(s.substring(0,s.length() -1));
                Item item = mapToItem(jobj);
                itemList.add(item);
            }catch (Exception e ){
                System.out.println(jsonFiles[i].getPath());
                e.printStackTrace();
                err ++ ;
            }
        }
        return itemList;
    }

    public static Map<String,Integer> countKeyWord(Collection<Item> itemList) {
        Map<String,Integer> result = new HashMap<>();
        for (Item item : itemList) {
            List<String> keywords = item.getKeyword();
            for (String k : keywords){
                if (!k.trim().equals("暂无")){
                    result.merge(k.toLowerCase(), 1, Integer::sum);
                }
            }
        }
        return sortMapByNum(result);
    }

    private static Map<String, Integer> sortMapByNum(Map<String, Integer> wordsToNumMap) {
        HashMap<String, Integer> sortedWordsToNumMap = new LinkedHashMap<>();
        wordsToNumMap.entrySet()
                .stream()
                .sorted((p1, p2) -> {
                    if (p1 == null && p2 == null) {
                        return 0;
                    }
                    if (p1 == null) {
                        return 1;
                    }
                    if (p2 == null) {
                        return -1;
                    }
                    // 单词数不一样则按单词数目排序
                    return p2.getValue().compareTo(p1.getValue());
                })
                .collect(Collectors.toList())
                .forEach(
                        ele ->
                                sortedWordsToNumMap.put(ele.getKey(), ele.getValue())
                );
        return sortedWordsToNumMap;
    }

    private static Item mapToItemForECCV(JSONObject jsonObj){
        Item item = new Item();
        List<String> kwdList = new ArrayList<>();
        String note = jsonObj.getString("摘要");
        String publisher = jsonObj.getString("会议和年份").split("\\s+")[0];
        String[] kwds = jsonObj.getObject("关键词",String[].class);
        kwdList.addAll(Arrays.asList(kwds));
        String publishDate = jsonObj.getString("发布时间");
        String title = jsonObj.getString("论文名称");
        String link = jsonObj.getString("原文链接");

        item.setNote(note).setAuthor(Collections.singletonList("暂无"))
                .setTitle(title).setLink(link).setPublisher(publisher)
                .setConferenceDate(publishDate)
                .setKeyword(kwdList).setId("暂无|"+(i++));
        return item;
    }
    public static List<Item> readDataForECCV(String eccv) {
        List<Item> itemList = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "\\src\\main\\resources\\static\\" + eccv;
        File file = new File(filePath);
        File[] jsonFiles = file.listFiles();
        int err = 0;
        for (int i = 0; i < jsonFiles.length; i++) {
            File f = null;
            try {
                f = jsonFiles[i];
                String s = readJsonFile(f.getPath());
                JSONObject jobj = JSON.parseObject(s);
                Item item = mapToItemForECCV(jobj);
                itemList.add(item);
            } catch (Exception e) {
                err ++ ;
                e.printStackTrace();
            }
        }
        return itemList;
    }

    public static String List2Str(List<String> list){
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String s : list) {
            if (first){
                builder.append(s).append("|");
                first = false;
            }else{
                builder.append("|").append(s);
            }
        }
        return builder.toString();
    }

    public static List<String> Str2List(String str){
        List<String> list = new ArrayList<>();
        String[] split = str.split("\\|");
        for (String k : split){
            if (!k.trim().equals("")) list.add(k.trim());
        }
        return list;
    }

    private static String verifyId(String origin){
        return origin.split("\\|").length > 1 ?
                "暂无" : origin;
    }


    public static Item ResultItem2Item(ResultItem resultItem){
        Item item = new Item();
        item.setAuthor(Str2List(resultItem.getAuthors()))
                .setConferenceDate(resultItem.getConferenceDate())
                .setKeyword(Str2List(resultItem.getKeywords()))
                .setId(verifyId(resultItem.getId()))
                .setPublisher(resultItem.getPublisher())
                .setLink(resultItem.getLink())
                .setTitle(resultItem.getTitle())
                .setNote(resultItem.getNote());
        return item;
    }
}
