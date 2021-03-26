package com.example.thesisSearch.dao;

import com.example.thesisSearch.pojo.Thesis;
import com.example.thesisSearch.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThesisDAO {
    public int getNumBytitle(String title)
    {
        int result=0;
        try {
            Connection ThesisConnection =DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select count(*) from Thesis where title like ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setString(1, "%" + title + "%");
            ResultSet Rs = Ptmt.executeQuery();
            if(Rs.next()) {
                result=Rs.getInt(1);
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  result;

    }
    public List<Thesis> getLimitBytitile(int start,int length,String title)
    {
        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from Thesis where title like ? limit ?,?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setString(1, "%" + title + "%");
            Ptmt.setInt(2,start);
            Ptmt.setInt(3,length);
            ResultSet Rs = Ptmt.executeQuery();
            while (Rs.next()) {
                results.add(setThesis(Rs));
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
    public List<Thesis> getAllBytitle(String title)
    {
        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from Thesis where title like ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setString(1, "%" + title + "%");
            ResultSet Rs = Ptmt.executeQuery();
            while (Rs.next()) {
                results.add(setThesis(Rs));
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
    public void deleteBymeeting()
    {
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "delete  from Thesis where meeting = 'ICCV'";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.execute(sql);
            DBUtil.close(null,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Thesis> getAllByKey(String key)
    {
        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from Thesis where keyword like ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setString(1, "%" + key + "%");
            ResultSet Rs = Ptmt.executeQuery();
            while (Rs.next()) {
                results.add(setThesis(Rs));
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
    public Thesis  setThesis(ResultSet Rs )
    {
        Thesis result=new Thesis();
        try {
            result.setDate(Rs.getString("publishdate"));
            result.setAbstractContent(Rs.getString("abstract"));
            result.setId(Rs.getInt("id"));
            result.setLink(Rs.getString("link"));
            result.setMeeting(Rs.getString("meeting"));
            result.setYear(Rs.getInt("thesisyear"));
            result.setTitle(Rs.getString("title"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public List<Thesis> getAllByAbstract(String key)
    {
        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from Thesis where abstract like ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setString(1, "%" + key + "%");
            ResultSet Rs = Ptmt.executeQuery();
            while (Rs.next()) {
                results.add(setThesis(Rs));
            }
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return results;
    }
}
