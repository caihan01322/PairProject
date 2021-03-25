package com.example.thesisSearch.servlets;


import com.example.thesisSearch.dao.ThesisDAO;
import com.example.thesisSearch.pojo.Thesis;
import com.example.thesisSearch.service.SearchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;


@WebServlet(name = "SearchServlet", value = "/Search")
public class SearchServlet extends HttpServlet {
    private ThesisDAO SearchThesisDAO=new ThesisDAO();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = (String) request.getParameter("searchtype");
        String input =(String) request.getParameter("input");
        List<Thesis> SearchResults=SearchService.search(type,input,SearchThesisDAO);
        request.setAttribute("result",SearchResults);
        request.setAttribute("searchtype",type);
        try {
            request.getRequestDispatcher("/WEB-INF/views/SearchResult.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    public void destroy() {
    }
}