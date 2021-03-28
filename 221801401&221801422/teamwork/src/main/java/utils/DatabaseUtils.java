package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
    
    /*
     * 连接至articles数据库
     * @param 无
     * @return connection对象
     * */
    public static Connection connectToArticles() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");            
        }catch(Exception e) {
            System.out.println("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/articles?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","zhl19991023");
        }catch(Exception e) {
            System.out.print("get data error!");;
            e.printStackTrace();
        }
        return conn;
    }
    
    /*
     * 连接至user数据库
     * @param 无
     * @return connection对象
     * */
    public static Connection connectToUser() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");            
        }catch(Exception e) {
            System.out.println("Error loading Mysql Driver!");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","zhl19991023");
        }catch(Exception e) {
            System.out.print("get data error!");;
            e.printStackTrace();
        }
        return conn;
    }
    
    
}
