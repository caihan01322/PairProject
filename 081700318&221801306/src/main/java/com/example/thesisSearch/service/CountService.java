package com.example.thesisSearch.service;

import com.example.thesisSearch.dao.KeywordsDAO;
import com.example.thesisSearch.pojo.HotWord;

import java.util.List;

public class CountService  {
    public  List<HotWord> count(int count)
    {
        KeywordsDAO Kd=new KeywordsDAO();
        return Kd.getHotkey(count);
    }
    public  List<HotWord> count(int year,String meeting)
    {
        KeywordsDAO Kd=new KeywordsDAO();
        return Kd.getHotkeyByYearAndMeeting(year,meeting);
    }
}
