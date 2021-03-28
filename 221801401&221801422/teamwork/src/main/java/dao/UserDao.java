package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserDao {
    private Connection conn = null;
    
    public void setConnection(Connection conn) {
        this.conn = conn;
    }
    
    /*
     * 核实用户名和密码
     * @param username password
     * @return Boolean类对象
     * */
    public boolean judge(String username, String password) {
        String sql = "select username, pass from user;";
        Statement statement;
        String user = "";
        String pass = "";
        try {
            statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                user = result.getString("username");
                pass = result.getString("pass");
            }
            statement.close();
            result.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(username.equals(user) && password.equals(pass)) {
            return true;
        }
        else return false;
    }
}
