package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Entity.LoginReturn;
import Utills.DBUtills;

public class LoginDaoImpl implements LoginDao {

    public LoginReturn userLogin(String userName, String password) {
        // TODO Auto-generated method stub
        Connection co = null;
        PreparedStatement pre = null;
        ResultSet re = null;
        LoginReturn loginReturn = new LoginReturn();
        String pwd = null;
        int flag = 0;

        try {
            co = DBUtills.GetConnection();
            String sql = "select * from user where username = ?";
            pre = co.prepareStatement(sql);
            pre.setString(1, userName);
            re = pre.executeQuery();
            
            while (re.next()) {
                flag = 1;
                pwd = re.getString("password");
                if (password.equals(pwd)) {
                    flag = 2;
                }
                break;
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            DBUtills.Close(re, null, co, pre);
        }
        loginReturn.setLoginStatus(flag);
        if (flag == 2) {
            loginReturn.setName(userName);
        }else {
            loginReturn.setName("none");
        }
        return loginReturn;
    }

}
