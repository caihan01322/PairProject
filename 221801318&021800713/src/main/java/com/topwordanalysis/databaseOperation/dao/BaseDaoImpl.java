package com.topwordanalysis.databaseOperation.dao;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseDaoImpl
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/23
 */
public class BaseDaoImpl implements BaseDao{
    /**
     * 实现数据库更新、获得自增主键数值
     *
     * @param sql 需要执行的SQL语句
     * @param paramsValue 需要填入sql语句中？的值数组
     * @return 当前自增主键数值
     */
    public int update(String sql, Object[] paramsValue) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            conn= DruidUtil.getConnection();//进行数据库连接
            //获得PreparedStatement对象，获得主键声明
            pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //获得SQL中？的个数
            int count=pstmt.getParameterMetaData().getParameterCount();
            //将paramsValue数组的值填入？
            if (paramsValue!=null&&paramsValue.length>0) {
                for(int i=0;i<count;i++) {
                    pstmt.setObject(i+1,paramsValue[i]);
                }
            }
            //执行SQL语句
            pstmt.executeUpdate();

            //获取自增主键
            rs=pstmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);//返回主键值
            } else {
                return -1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {//关闭连接
            DruidUtil.close(conn,pstmt,rs);
        }
        return -1;
    }

    /**
     * 用于进行数据库查询操作
     *
     * @param sql 需要执行的sql语句
     * @param paramsValue 需要填入sql语句中？的值数组
     * @param cla 查询目标的类型
     * @param <T> 模板
     * @return 查询目标的列表
     */
    public <T> List<T> query(String sql, Object[] paramsValue, Class<T> cla) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet res=null;
        List<T> objList=new ArrayList<T>();
        try {
            conn=DruidUtil.getConnection();//进行数据库连接
            pstmt=conn.prepareStatement(sql);

            if (paramsValue!=null&&paramsValue.length>0) {
                for (int i=0;i<paramsValue.length;i++) {
                    pstmt.setObject(i+1, paramsValue[i]);
                }
            }

            res=pstmt.executeQuery();

            ResultSetMetaData rsmd=res.getMetaData();//获得结果矩阵
            int columnCount=rsmd.getColumnCount();//获得结果的列数
            T t;
            while (res.next()) {
                t=cla.newInstance();
                //填入类中每个属性的值
                for (int i=0;i<columnCount;i++) {
                    String columnName=rsmd.getColumnName(i+1);
                    Object value=res.getObject(columnName);
                    BeanUtils.copyProperty(t,columnName,value);
                }
                objList.add(t);//将类加入列表
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(conn,pstmt,res);
        }
        return objList;
    }
}
