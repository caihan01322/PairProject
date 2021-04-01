package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.log.Log;

import dao.ArticleDao;
import entity.Article;
import utils.DatabaseUtils;

@Controller
public class ArticleController {
    
    @RequestMapping("/articles")
    @CrossOrigin
    @ResponseBody
    public List<Article> queryAll() {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryAll();
        return a;
    }
    
    @RequestMapping("/articles/bytitle")
    @CrossOrigin
    @ResponseBody
    public List<Article> queryByTitle(String title) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByTitle(title);
        return a;
    }
    


    @RequestMapping("/articles/bynumber")
    @CrossOrigin
    @ResponseBody
    public List<Article> queryByNumber(String number) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByNumber(number);
        return a;
    }
    
    @RequestMapping("/articles/bykwds")
    @CrossOrigin
    @ResponseBody
    public List<Article> queryByKwds(String kwd) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByKwds(kwd);
        return a;
    }
    
    @RequestMapping("/articles/delete")
    @CrossOrigin
    @ResponseBody
    public void deleteArt(String title) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        dao.deleteArt(title);
    }
    
    @RequestMapping("/kwds/top10")
    @CrossOrigin
    @ResponseBody
    public List<String> findTop() {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<String> a = dao.findTop();
        return a;
    }
    
    @RequestMapping("kwds/yearname")
    @CrossOrigin
    @ResponseBody
    public List<String> findKwds(String name, String year) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<String> a = dao.findKwds(name, year);
        return a;
    }
    
  /*  @RequestMapping("kwds/year")
    @ResponseBody
    public List<String> findYear(String year) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<String> a = dao.findYear(year);
        return a;
    }*/
}
