package com.example.thesisSearch.dao;

import com.example.thesisSearch.pojo.HotWord;
import com.example.thesisSearch.pojo.Thesis;
import com.example.thesisSearch.utils.DBUtil;

import javax.xml.ws.Holder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KeywordsDAO {
    public List<HotWord> getHotkey()
    {
        List<HotWord> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "SELECT keyword, count( * ) AS count " +
                    "from Keywords " +
                    "GROUP BY keyword " +
                    "ORDER BY count DESC " +
                    "LIMIT 20";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            ResultSet Rs = Ptmt.executeQuery();
            while (Rs.next()) {
                results.add(setHotWord(Rs));
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
    public List<HotWord> getHotkeyByYearAndMeeting(int year,String meeting)
    {
        List<HotWord> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "SELECT keyword, count( * ) AS count " +
                    "from Keywords where Thesisyear = ? AND meeting=? " +
                    "GROUP BY keyword " +
                    "ORDER BY count DESC " +
                    "LIMIT 20";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setInt(1,year);
            Ptmt.setString(2, meeting);
            ResultSet Rs = Ptmt.executeQuery();
            while (Rs.next()) {
                results.add(setHotWord(Rs));
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
    public HotWord setHotWord(ResultSet Rs) throws SQLException
    {
        HotWord temp=new HotWord();
        temp.setKeyword(Rs.getString("keyword"));
        temp.setNums(Rs.getInt("count"));
        return  temp;
    }

}
