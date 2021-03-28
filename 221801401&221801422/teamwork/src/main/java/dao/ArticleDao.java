package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Article;
import utils.WordCountUtils;


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
    
    /*
     * 按照论文编号查找对应论文
     * @param number
     * @return article类的List
     * */
    public List<Article> queryByNumber(String number) {
        String sql = "select * from cvpr where articlenumber=? union select * from "
                + "eccv where articlenumber=? union select * from iccv where articlenumber=?;";
        List<Article> list = new ArrayList();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, number);
            pre.setString(2, number);
            pre.setString(3, number);
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
    
    /*
     * 按照关键词查找对应论文
     * @param kwd
     * @return article类的List
     * */
    public List<Article> queryByKwds(String kwd) {
        String sql = "select * from cvpr where kwds like ? union select * from eccv where kwds"
                + " like ? union select * from iccv where kwds like ?;";
        List<Article> list = new ArrayList();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + kwd + "%");
            pre.setString(2, "%" + kwd + "%");
            pre.setString(3, "%" + kwd + "%");
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
    
    /*
     * 按照年份查找对应论文
     * @param year
     * @return article类的List
     * */
    public List<Article> queryByYear(String year) {
        String sql = "select * from cvpr where year=? union select * from eccv where year=? "
                + "union select * from iccv where year=?;";
        System.out.println("s");
        List<Article> list = new ArrayList();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, year);
            pre.setString(2, year);
            pre.setString(3, year);
            ResultSet result = pre.executeQuery();
            while(result.next()) {
                String authors = result.getString("authors").toString();
                String titleName = result.getString("formulastrippedarticletitle").toString();
                String articleNumber = result.getString("articlenumber").toString();
                String doiLink = result.getString("doilink").toString();
                String abs = result.getString("abstract").toString();
                String kwds = result.getString("kwds").toString();
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
    
    /*
     * 删除指定论文
     * @param title
     * @return 无
     * */
    public void deleteArt(String title) {
        String sql1 = "delete from cvpr where "
                + "formulastrippedarticletitle='" + title + "';";
        String sql2 = "delete from eccv where "
                + "formulastrippedarticletitle='" + title + "';";
        String sql3 = "delete from iccv where "
                + "formulastrippedarticletitle='" + title + "';";
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql3);
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * 查询top10关键词
     * @param 无
     * @return list
     * */
    public List<String> findTop(){
        List<String> list = new ArrayList();
        String sql = "select * from cvpr union select * from eccv union select * from iccv;";
        String str = "";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                String kwds = result.getString("kwds").toString();
                str += kwds;
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        list = WordCountUtils.sortHashmap(str);
        return list;
    }
}
