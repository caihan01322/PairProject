package com.example.thesisSearch.utils;

import java.io.*;

public class JsonDateProcessor {

    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            Reader reader;
            StringBuffer sb;
            try (FileReader fileReader = new FileReader(jsonFile)) {

                reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
                int ch = 0;
                sb = new StringBuffer();
                while ((ch = reader.read()) != -1) {
                    sb.append((char) ch);
                }
                fileReader.close();
            }
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
