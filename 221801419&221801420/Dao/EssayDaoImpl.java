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
 
}
