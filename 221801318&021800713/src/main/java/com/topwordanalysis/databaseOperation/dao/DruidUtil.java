package com.topwordanalysis.databaseOperation.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库连接池（Druid）
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class DruidUtil {

    private static DataSource dataSource;

    /**
     * 载入数据库配置文件
     */
    static {
        try {
            Properties properties=new Properties();
            InputStream in=DruidUtil.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
            properties.load(in);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于获取数据库连接
     *
     * @return Connection对象
     */
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用于关闭数据库连接
     *
     * @param conn Connection对象
     * @param state Statement对象
     * @param result ResultSet对象
     */
    public static void close(Connection conn, Statement state, ResultSet result) {
        try {
            if (result!=null)
                result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (state!=null)
                        state.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
