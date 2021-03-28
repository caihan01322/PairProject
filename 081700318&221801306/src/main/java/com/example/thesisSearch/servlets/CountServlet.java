package com.example.thesisSearch.servlets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.thesisSearch.dao.KeywordsDAO;
import com.example.thesisSearch.pojo.HotWord;
import com.mysql.cj.xdevapi.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CountServlet", value = "/Count")
public class CountServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)  {
        response.setContentType("application/json;charset=utf-8");// 指定返回的格式为JSON格式
        response.setCharacterEncoding("UTF-8");// setContentType与setCharacterEncoding的顺序不能调换，否则无法解决中文乱码的问题
        response.setHeader("Access-Control-Allow-Origin", "*");//跨域
        PrintWriter out = null;
        try {
            out = response.getWriter();
            KeywordsDAO Kd = new KeywordsDAO();
            List<HotWord> list= Kd.getHotkey();
            JSONArray Jarray = (JSONArray) JSON.toJSON(list);
            out.write(Jarray.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
