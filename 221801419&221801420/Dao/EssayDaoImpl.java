package Dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;

import Entity.Essay;
import Entity.Keywords;
import Utills.DBUtills;

public class EssayDaoImpl implements essayDao {
    private SqlSession session = DBUtills.getSession();
    private essayDao e = session.getMapper(essayDao.class);
    public Essay getInfo(String essayName) {
        Essay essay = e.getInfo(essayName);
        System.out.println(essay.getAuthors());
        return essay;
    }
    
    public void editcvpr(Essay essay) {
        e.editcvpr(essay);
        session.commit();
        session.close();
    }
    
   
    public void editeccv(Essay essay) {
        e.editeccv(essay);
        session.commit();
        session.close();
    }
    @Override
    public void editiccv(Essay essay) {
        e.editiccv(essay);
        session.commit();
        session.close();
        
    }
    @Override
    public List<Essay> singleSearch(String searchString) {
        ArrayList<Essay> array = new ArrayList<>();
        array = (ArrayList<Essay>) e.singleSearch(searchString);
        return array;
    }
    @Override
    public List<Keywords> keywordsGetCVPR(String year) {
       return e.keywordsGetCVPR(year);
    }
    @Override
    public List<Keywords> keywordsGetECCV(String year) {
        return e.keywordsGetECCV(year);
    }
    @Override
    public List<Keywords> keywordsGetICCV(String year) {
        return e.keywordsGetICCV(year);
    }
    @Override
    public List<Keywords> keywordsGetMY(String name) {
        return e.keywordsGetMY(name);
    }

    @Override
    public List<Keywords> keywordsGetCVPRALL() {
        return e.keywordsGetCVPRALL();
    }
    @Override
    public List<Keywords> keywordsGetECCVALL() {
        return e.keywordsGetECCVALL();
    }
    @Override
    public List<Keywords> keywordsGetICCVALL() {
        return e.keywordsGetICCVALL();
    }
    @Override
    public List<Essay> labelSearchByKeywords(String words,String user) {
        return e.labelSearchByKeywords(words,user);
    }
    @Override
    public List<Essay> labelSearchByNum(String num,String user) {
        return e.labelSearchByNum(num,user);
    }
    @Override
    public List<Essay> labelSearchByTitle(String title,String user) {
        return e.labelSearchByTitle(title,user);
    }
    @Override
    public int selectCheckCVPR(String essayName, String username) {

        return e.selectCheckCVPR(essayName, username);
    }@Override
    public int selectCheckECCV(String essayName, String username) {
        
        return e.selectCheckECCV(essayName, username);
    }@Override
    public int selectCheckICCV(String essayName, String username) {
        return e.selectCheckICCV(essayName, username);
    }
    
}
