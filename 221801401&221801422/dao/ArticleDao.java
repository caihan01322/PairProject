package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Article;


public class ArticleDao {
    private Connection conn = null;
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    /*
     * 读取所有的论文
     * @param 无
     * @return article类的List
     * */
    public List<Article> queryAll() {
        String sql = "select * from cvpr union select * from eccv union select * from iccv;";
        List<Article> list = new ArrayList();
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                String authors = result.getString("authors").toString();
                String title = result.getString("formulastrippedarticletitle").toString();
                String articleNumber = result.getString("articlenumber").toString();
                String doiLink = result.getString("doilink").toString();
                String abs = result.getString("abstract");
                String kwds = result.getString("kwds").toString();
                String year = result.getString("year").toString();
                Article art = new Article();
                art.setAuthors(authors);
                art.setTitle(title);
                art.setArticleNumber(articleNumber);
                art.setDoiLink(doiLink);
                art.setKwds(kwds);
                art.setAbs(abs);
                art.setYear(year);
                list.add(art);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return list;
    }
    
    /*
     * 按照标题查找对应论文
     * @param title
     * @return article类的List
     * */
    public List<Article> queryByTitle(String title) {
        String sql = "select * from cvpr where formulastrippedarticletitle like ? union select "
                + "* from eccv where formulastrippedarticletitle like ? union select * from "
                + "iccv where formulastrippedarticletitle like ?;";
        List<Article> list = new ArrayList();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + title + "%");
            pre.setString(2, "%" + title + "%");
            pre.setString(3, "%" + title + "%");
            ResultSet result = pre.executeQuery();
            while(result.next()) {
                String authors = result.getString("authors").toString();
                String titleName = result.getString("formulastrippedarticletitle").toString();
                String articleNumber = result.getString("articlenumber").toString();
                String doiLink = result.getString("doilink").toString();
                String abs = result.getString("abstract").toString();
                String kwds = result.getString("kwds").toString();
                String year = result.getString("year").toString();
                Article art = new Article();
                art.setAuthors(authors);
                art.setTitle(titleName);
                art.setArticleNumber(articleNumber);
                art.setDoiLink(doiLink);
                art.setKwds(kwds);
                art.setAbs(abs);
                art.setYear(year);
                list.add(art);
            }
            result.close();
            pre.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
        
    }

    
}
