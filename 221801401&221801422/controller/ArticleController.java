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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.log.Log;

import dao.ArticleDao;
import entity.Article;
import utils.DatabaseUtils;

@Controller
public class ArticleController {
    
    @RequestMapping("/articles")
    @ResponseBody
    public List<Article> queryAll() {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());

        List<Article> a = dao.queryAll();
        return a;
    }
    
    @RequestMapping("/articles/bytitle")
    @ResponseBody
    public List<Article> queryByTitle(String title) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByTitle(title);
        return a;
    }
    


    @RequestMapping("/articles/bynumber")
    @ResponseBody
    public List<Article> queryByNumber(String number) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByNumber(number);
        return a;
    }
    
    @RequestMapping("/articles/bykwds")
    @ResponseBody
    public List<Article> queryByKwds(String kwd) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByKwds(kwd);
        return a;
    }
    
    @RequestMapping("/articles/byyear")
    @ResponseBody
    public List<Article> queryByYear(String year) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
        List<Article> a = dao.queryByYear(year);
        return a;
    }
    
    @RequestMapping("/articles/delete")
    @ResponseBody
    public void deleteArt(String title) {
        ArticleDao dao = new ArticleDao();
        dao.setConnection(DatabaseUtils.connectToArticles());
    }
}
