package cn.zcx.pa.PaperImport.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class ReadJsonUtil {
    //读取文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            FileInputStream Stream = new FileInputStream(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Stream,"utf-8"));
            StringBuilder sb = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            reader.close();
            jsonStr = sb.toString();
            if(jsonStr.charAt(jsonStr.length() - 1) == ';'){
                jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
            } else {
                jsonStr = jsonStr.substring(0, jsonStr.length());
            }
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //将字符串转换成JSON
    public static JSONObject getJson(String fileName) {
        return JSON.parseObject(readJsonFile(fileName));
    }
}
