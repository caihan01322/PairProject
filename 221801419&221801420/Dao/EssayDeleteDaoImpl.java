package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Utills.DBUtills;

public class EssayDeleteDaoImpl implements essayDeleteDao{
    @Override
    public String delete(String userName, String essayName, String essayMeeting) {
        Connection co = null;
        PreparedStatement pre = null;
        ResultSet re =null;
        
        try {
            int id = 0;
            co = DBUtills.GetConnection();
            String sql = "select * from essay_" + essayMeeting + " where title = ?";
            pre = co.prepareStatement(sql);
            pre.setString(1, essayName);
            re = pre.executeQuery();
            while (re.next()) {
                id = re.getInt("id");
            }
            
            sql = "delete from collect where name = ? and essaytype = ? and essaynum = ?";
            pre = co.prepareStatement(sql);
            pre.setString(1, userName);
            pre.setString(2, essayMeeting);
            pre.setInt(3, id);
            pre.executeUpdate();
        }catch (Exception e) {
            // TODO: handle exception
        }finally {
            DBUtills.Close(re, null, co, pre);
        }
        
        return "success";
    }
}
