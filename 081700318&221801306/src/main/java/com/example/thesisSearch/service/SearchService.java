package com.example.thesisSearch.service;

import com.example.thesisSearch.dao.ThesisDAO;
import com.example.thesisSearch.pojo.Thesis;

import java.util.List;

public class SearchService {

      static  public  List<Thesis> search(String type, String input,ThesisDAO SearchThesisDAO)
    {
        List<Thesis> SearchResults = null;
        if (type.equals("title"))
        {
            SearchResults = SearchThesisDAO.getAllBytitle(input);
        }
        else if(type.equals("keyword"))
        {
            SearchResults = SearchThesisDAO.getAllByKey(input);
        }
        else if(type.equals("content"))
        {
            SearchResults = SearchThesisDAO.getAllByAbstract(input);
        }
        else {
            SearchResults = SearchThesisDAO.getAllBytitle(input);
        }
        return  SearchResults;
    }
}
