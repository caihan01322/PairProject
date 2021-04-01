package com.example.demo11.service;

import com.example.demo11.model.*;

import java.util.List;
import java.util.Set;

public interface ArticalService
{
    List<Artical> searchArtical(String keywords);
    List<keywords> searchKeywords(int academicNum);
    List<authors> searchAuthors(int academicNum);
    int Collect(String username,int [] academicNum);
    List<Artical> getCollection(String username);
    Set<String> getAllkeywords();
    int deleteCollections(String username,int []academicNum);
    List<hotkey> top20();

}
