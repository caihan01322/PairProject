package com.example.thesisSearch.utils;

import com.alibaba.fastjson.JSONArray;
import com.example.thesisSearch.pojo.Thesis;
import com.mysql.cj.xdevapi.JsonArray;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

import java.sql.*;

public class WordcountUtils {
    public void init()
    {
        Thesis result=null;
        try {
            Connection ThesisConnection =DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String SelectSql = "select keyword,thesisyear,meeting,id from Thesis ";
            String InsertSql = "insert into Keywords(keyword,ThesisID,thesisyear,meeting) value(?,?,?,?)  ";
            //预编译
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(SelectSql);
            ResultSet Rs = Ptmt.executeQuery();
            Ptmt = ThesisConnection.prepareStatement(InsertSql);
            while(Rs.next()) {
                String TempString=Rs.getString("keyword");
                Integer TempYear=Rs.getInt("thesisyear");
                String TempMeeting=Rs.getString("meeting");
                int TempId= Rs.getInt("id");

                JSONArray TheKeyWord = JSONArray.parseArray(TempString);
                for(int i=0;i<TheKeyWord.size();i++)
                {
                    System.out.println(TheKeyWord.get(i));
                    Ptmt.setString(1,TheKeyWord.get(i).toString());
                    Ptmt.setInt(2,TempId);
                    Ptmt.setInt(3,TempYear);
                    Ptmt.setString(4,TempMeeting);
                    Ptmt.execute();
                }

            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
