package com.example.thesisSearch.servlets;

import com.example.thesisSearch.dao.LikeListDao;
import com.example.thesisSearch.javabean.PageBean;
import com.example.thesisSearch.pojo.Thesis;
import com.example.thesisSearch.service.SearchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LikeServlet", value = "/Like")
public class LikeServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LikeListDao lld=new LikeListDao();
        String input =req.getParameter("input");
        req.setAttribute("result",lld.getLikeList());
        if(input!=null)
            req.setAttribute("result",lld.getLikeListByTitle(input));
        try {
            req.getRequestDispatcher("/WEB-INF/views/LikeList.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String id = request.getParameter("id");
        String type =request.getParameter("type");
        System.out.println(type);
        if(type.equals("add")) {
            if (id != null) {
                PrintWriter out = response.getWriter();
                LikeListDao lld = new LikeListDao();
                if (lld.add(Integer.parseInt(id))) {
                    out.println("1");
                } else {
                    out.println("2");
                }
            }
        }
        else if(type.equals("remove"))
        {
            if (id != null) {
                PrintWriter out = response.getWriter();
                LikeListDao lld = new LikeListDao();
                if (lld.remove(Integer.parseInt(id))) {
                    out.println("1");
                } else {
                    out.println("2");
                }
            }
        }
    }
}
