package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.LoginReturn;
import Utills.DBUtills;

public class RegisterDaoImpl implements RegisterDao {

    public LoginReturn userRegister(String username, String pwd) {
        // TODO Auto-generated method stub
        Connection co = null;
        PreparedStatement pre = null;
        ResultSet re = null;
        LoginReturn loginReturn = new LoginReturn();
        int flag = 0;
        int exits = 0;
        
        try {
            co = DBUtills.GetConnection();
            
            //检查用户名是否存在
            String sql = "select * from user where username = ?";
            pre = co.prepareStatement(sql);
            pre.setString(1, username);
            re = pre.executeQuery();
            
            while (re.next()) {
                exits = 1;
                break;
            }
            
            //若用户名不存在则注册该用户
            if (exits == 0) {
                sql = "insert into user values (?,?)";
                pre = co.prepareStatement(sql);
                pre.setString(1,username);
                pre.setString(2, pwd);
                pre.executeUpdate();
                flag = 1;
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            DBUtills.Close(re, null, co, pre);
        }
        loginReturn.setLoginStatus(flag);
        if (flag == 0) {
            loginReturn.setName("none");
        }else {
            loginReturn.setName(username);
        }
        
        return loginReturn;
    }

}
