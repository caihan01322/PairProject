package com.example.thesisSearch.dao;

import com.example.thesisSearch.pojo.Thesis;
import com.example.thesisSearch.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.thesisSearch.dao.ThesisDAO.setThesis;

//收藏列表dao层
public class LikeListDao {

    public boolean add(int id)
    {
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from LikeList where ThesisID = ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setInt(1,  id );
            ResultSet Rs = Ptmt.executeQuery();
            if (Rs.next()) {
                DBUtil.close(Rs,ThesisStatement,ThesisConnection);
                return false;
            }
            else
            {
                String InertSql = "Insert  into LikeList (ThesisID) VALUES (?)";
                Ptmt = ThesisConnection.prepareStatement(InertSql);
                Ptmt.setInt(1,id);
                Ptmt.execute();
                System.out.println(1);
                return  true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean remove(int id)
    {
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from LikeList where ThesisID = ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setInt(1,  id );
            ResultSet Rs = Ptmt.executeQuery();
            if (!Rs.next()) {
                DBUtil.close(Rs,ThesisStatement,ThesisConnection);
                return false;
            }
            else
            {
                String InertSql = "Delete  from LikeList where ThesisId = ?";
                Ptmt = ThesisConnection.prepareStatement(InertSql);
                Ptmt.setInt(1,id);
                Ptmt.execute();
                System.out.println(1);
                DBUtil.close(Rs,ThesisStatement,ThesisConnection);
                return  true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public boolean isliked(int id)
    {
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
        Statement ThesisStatement = ThesisConnection.createStatement();
        String sql = "select * from LikeList where ThesisID = ?";
        PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
        Ptmt.setInt(1,  id );
        ResultSet Rs = Ptmt.executeQuery();
        if (Rs.next()) {
            DBUtil.close(Rs,ThesisStatement,ThesisConnection);
            return true;
        }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  false;
    }
    public List<Thesis> getLikeList()
    {

        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from LikeList left join Thesis on LikeList.ThesisID=Thesis.ID";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
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
    public List<Thesis> getLikeListByTitle(String input)
    {

        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from LikeList left join Thesis on LikeList.ThesisID=Thesis.ID Where title like ?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setString(1, "%" + input + "%");
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

    public List<Thesis> getLimit(int start,int length,String input,String type)
    {
        List<Thesis> results=new ArrayList<>();
        Connection ThesisConnection = null;
        try {
            ThesisConnection = DBUtil.getConnection();
            Statement ThesisStatement = ThesisConnection.createStatement();
            String sql = "select * from LikeList left join Thesis on LikeList.ThesisID=Thesis.ID limit ?,?";
            PreparedStatement Ptmt = ThesisConnection.prepareStatement(sql);
            Ptmt.setInt(1, start);
            Ptmt.setInt(2, length);
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
