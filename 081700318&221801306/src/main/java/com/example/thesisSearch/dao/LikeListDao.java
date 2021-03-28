package com.example.thesisSearch.dao;

import com.example.thesisSearch.utils.DBUtil;

import java.sql.*;

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
}
