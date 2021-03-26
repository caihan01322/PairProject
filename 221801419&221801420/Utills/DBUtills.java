package Utills;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBUtills {
	private static final String URL = "jdbc:mysql://localhost:3306/vc?allowPublicKeyRetrieval=true&userSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "zheng.100189022";
	private static  InputStream in = null;
	private static  SqlSession sqlSession = null;
	static {
		try {
			//ע������
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��ȡ����
	public static Connection GetConnection() {
		try {
			Connection con=DriverManager.getConnection( URL,USERNAME, PASSWORD );
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	//�ͷ���Դ
	public static void Close(ResultSet r,Statement s,Connection c,PreparedStatement pre) {
		try {
			if (r!=null) {
				r.close();
			}
			if (s!=null) {
				s.close();
			}
			if (c!=null) {
				c.close();
			}
			if (pre!=null) {
				pre.close();
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static SqlSession getSession() {
	    try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //创建SqlSessionFactory的构建者对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //使用构建者创建工厂对象SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //使用SqlSessionFactory产SqlSession对象生
        sqlSession = factory.openSession();
        //产SqlSession对象时可以传true代表自动提交事务
        //sqlSession = factory.openSession(true);
        //使用SqlSession创建映射接口的代理对象
        return sqlSession;
	}



}
