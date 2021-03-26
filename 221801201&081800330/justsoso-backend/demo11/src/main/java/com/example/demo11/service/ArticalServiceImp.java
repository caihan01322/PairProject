package com.example.demo11.service;

import com.example.demo11.dao.ArticalDaoImpl;
import com.example.demo11.model.Artical;
import com.example.demo11.model.PageModel;
import com.example.demo11.model.authors;
import com.example.demo11.model.keywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticalServiceImp implements ArticalService
{
    @Autowired
    private ArticalDaoImpl articalDao;
    @Override
    public List<Artical> searchArtical(String keywords)
    {
        return articalDao.searchArtical(keywords);
    }
    @Override
    public  List<keywords> searchKeywords(int academicNum)
    {
        return articalDao.searchKeywords(academicNum);
    }
    @Override
    public List<authors> searchAuthors(int academicNum)
    {
        return articalDao.searchAuthors(academicNum);
    }
    @Override
    public int Collect(String username,int [] academicNum)
    {
        return articalDao.Collect(username,academicNum);
    }
    @Override
    public List<Artical> getCollection(String username)
    {
        return articalDao.getCollection(username);
    }
    @Override
    public Set<String> getAllkeywords()
    {
        return articalDao.getAllkeywords();
    }
    @Override
    public int deleteCollections(String username,int []academicNum)
    {
        return articalDao.deleteCollections(username,academicNum);
    }
}
