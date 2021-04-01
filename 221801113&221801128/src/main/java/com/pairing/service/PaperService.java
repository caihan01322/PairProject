package com.pairing.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pairing.bean.Paper;
import com.pairing.mapper.PaperMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperService {

    @Autowired
    PaperMapper paperMapper;

    /**
     * 获取paper的list和总数
     * @param searchInfo
     * @param pageNum
     * @return
     */
    public Map<List<Paper>, Integer> getPaper(String searchInfo, int pageNum) {
        Map<List<Paper>, Integer> map= new HashMap<>();
        map.put(paperMapper.getPaper(searchInfo, Integer.valueOf(pageNum * 4)), paperMapper.getPaperCount(searchInfo));
        return map;
    }

    /**
     * 获取收藏夹的paper的list和总数
     * @param searchInfo
     * @param pageNum
     * @param userName
     * @return
     */
    public Map<List<Paper>, Integer> getCollectPaper(String searchInfo, int pageNum, String userName) {
        Map<List<Paper>, Integer> map= new HashMap<>();
        map.put(paperMapper.getCollectPaper(searchInfo, Integer.valueOf(pageNum * 4), userName)
                , paperMapper.getCollectPaperCount(searchInfo, userName));
        return map;
    }

    /**
     * 获取爬取的论文总数和list
     * @param searchInfo
     * @return
     */
    public Map<List<Paper>, Integer> getCrawlPaper(String searchInfo) {
        return getMap(getJsonStr(searchInfo));
    }

    /**
     * 获取爬取论文的json字符串
     * @param searchInfo
     * @return
     */
    public String getJsonStr(String searchInfo) {
        String params = "{\"newsearch\":true,\"queryText\":\""+searchInfo+"\",\"highlight\":true" +
                ",\"returnFacets\":[\"ALL\"],\"returnType\":\"SEARCH\",\"matchPubs\":true}";
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;
        OutputStream out = null;
        String res = "";
        try {
            httpURLConnection=setConnection();
            httpURLConnection.connect();
            out = httpURLConnection.getOutputStream();
            out.write(params.getBytes());
            if (httpURLConnection.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream()));
                res = getResStr(reader);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }



    /**
     * 获取输入流的json字符串
     * @param reader
     * @return
     * @throws IOException
     */
    public String getResStr(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String str = "";
        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 设置请求头参数
     * @return
     */
    public HttpURLConnection setConnection() {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL("https://ieeexplore.ieee.org/rest/search");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Accept", "application/json, text/plain, */*");
            httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Referer", "https://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText=computer");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }

    /**
     * 将json字符串转变为json对象放入map里
     * @param jsonStr
     * @return
     */
    public Map<List<Paper>, Integer> getMap(String jsonStr) {
        Map<List<Paper>, Integer> map= new HashMap<>();
        List<Paper> paperList=new ArrayList<>();
        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("records");
        if (jsonArray == null) {
            map.put(paperList, new Integer(0));
        } else {
            for (int i = 0; i < jsonArray.size(); i++) {
                Paper paper = new Paper();
                JSONObject jo = jsonArray.getJSONObject(i);
                paper.setAbstrac(jo.getString("abstract"));
                paper.setId(jo.getString("articleNumber"));
                paper.setPersistentLink("https://ieeexplore.ieee.org"+jo.getString("documentLink"));
                paper.setPublicationTitle(jo.getString("articleTitle"));
                paperList.add(paper);
            }
            map.put(paperList, new Integer(jsonArray.size()));
        }
        return map;
    }


    /**
     * 收藏
     * @param uid
     * @param did
     * @param keywords
     * @param abstrac
     * @param publicationTitle
     * @param persistentLink
     * @return
     */
    public String insertPaperToCollection(String uid, String did, String keywords, String abstrac
            , String publicationTitle, String persistentLink) {
        Integer integer = new Integer(0);
        try{
            integer = paperMapper.insertPaperToCollection(uid, did, keywords
                    , abstrac, publicationTitle, persistentLink);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);

        return (integer.intValue() == 0) ? "收藏失败！(可能原因：该论文已被收藏)" : "收藏成功！";
    }

    /**
     * 更新
     * @param uid
     * @param did
     * @param keywords
     * @param abstrac
     * @param publicationTitle
     * @param persistentLink
     * @return
     */
    public String updatePaperToCollection(String uid, String did, String keywords, String abstrac
            , String publicationTitle, String persistentLink) {
        Integer integer = new Integer(0);
        try{
            integer = paperMapper.updatePaperToCollection(uid, did, keywords
                    , abstrac, publicationTitle, persistentLink);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);

        return (integer.intValue() == 0) ? "修改失败！" : "修改成功！";
    }

    /**
     * 删除
     * @param uid
     * @param did
     * @return
     */
    public String deletePaperFromCollection(String uid, String did) {
        Integer integer = new Integer(0);
        try{
            integer = paperMapper.deletePaperFromCollection(uid, did);
        } catch (Exception e) {
            integer = new Integer(0);
        }
        if (integer == null) integer = new Integer(0);

        return (integer.intValue() == 0) ? "删除失败！" : "删除成功！";
    }
}
