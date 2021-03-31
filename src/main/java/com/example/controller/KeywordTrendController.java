package com.example.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPatch;
import com.example.dao.KeywordTrendDao;
import com.example.model.ConferenceKwd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("KeywordTrendController")
public class KeywordTrendController {

    @Autowired
    private KeywordTrendDao keywordTrendDao;

    @RequestMapping("/getTrend")
    public JSONObject getTrend(@RequestBody JSONObject request){
        String keyword = request.getString("keyword");

        int[] frequency = new int[8];
        for(int i = 0;i < frequency.length;i++){
            frequency[i] = 0;
        }
        JSONObject trend = new JSONObject();
        JSONArray horizontal = new JSONArray();
        JSONArray vertical = new JSONArray();

        for(int i = 0;i < 3;i++){
            List<ConferenceKwd> conferenceKwdList = null;
            if(i == 0){
                conferenceKwdList = keywordTrendDao.getCvprKwd(keyword);
            }
            else if(i == 1){
                conferenceKwdList = keywordTrendDao.getEccvKwd(keyword);
            }
            else if(i == 2){
                conferenceKwdList = keywordTrendDao.getIccvKwd(keyword);
            }
            for(int j = 0;j < conferenceKwdList.size();j++){
                ConferenceKwd conferenceKwd = conferenceKwdList.get(j);
                int number = conferenceKwd.getNumber();
                List<String> yearList = null;
                if(i == 0){
                    yearList = keywordTrendDao.getCvprYear(number);
                }
                else if(i == 1){
                    yearList = keywordTrendDao.getEccvYear(number);
                }
                else if(i == 2){
                    yearList = keywordTrendDao.getIccvYear(number);
                }
                String year = yearList.get(0);
                String lastCharacter = year.substring(year.length() - 1);
                int index = Integer.parseInt(lastCharacter);
                frequency[index]++;
            }
        }
        for(int i = 0;i < frequency.length;i++){
            horizontal.add(2000+i);
            vertical.add(frequency[i]);
        }
        trend.put("year",horizontal);
        trend.put("frequency",vertical);

        return trend;
    }
}
