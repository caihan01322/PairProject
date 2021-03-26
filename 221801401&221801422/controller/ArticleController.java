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
    

}
