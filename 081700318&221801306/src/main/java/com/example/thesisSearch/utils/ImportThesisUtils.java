package com.example.thesisSearch.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ImportThesisUtils {
    public  void ECCVImport()
    {
        File file=new File("D:\\论文数据\\ECCV（2016至2020，3033份）");
        File[] files=file.listFiles();
        try {
            Connection connection=DBUtil.getConnection();
            Connection c = DBUtil.getConnection();
            Statement s = c.createStatement();
            for(File temp:files)
            {
                System.out.println(temp.getName());
                String str= JsonDateProcessor.readJsonFile("D:\\论文数据\\ECCV（2016至2020，3033份）\\"+temp.getName());
                JSONObject job= JSON.parseObject(str);
                String sql = "INSERT INTO Thesis(title, thesisyear, keyword, publishdate, link, meeting,abstract)"
                        +"values("+"?,?,?,?,?,?,?)";
                //预编译
                PreparedStatement ptmt = connection.prepareStatement(sql); //预编译SQL，减少sql执行

                //传参
                String[] temp1=job.getString("会议和年份").split(" ");
                ptmt.setString(1, job.getString("论文名称"));
                ptmt.setInt(2, Integer.parseInt(temp1[1]));
                ptmt.setString(3, job.getString("关键词"));
                ptmt.setString(4, job.getString("发布时间"));
                ptmt.setString(5, job.getString("原文链接"));
                ptmt.setString(6, temp1[0]);
                ptmt.setString(7, job.getString("摘要"));

                ptmt.execute();
            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public  void ICCVImport()
    {
        File file=new File("D:\\论文数据\\ICCV（2001年至2019年，3196篇）");
        File[] files=file.listFiles();
        try {
            Connection connection=DBUtil.getConnection();
            Connection c = DBUtil.getConnection();
            Statement s = c.createStatement();
            for(File temp:files)
            {
                System.out.println(temp.getName());
                String str= JsonDateProcessor.readJsonFile("D:\\论文数据\\ICCV（2001年至2019年，3196篇）\\"+temp.getName());
                JSONObject job=JSON.parseObject(str.substring(0,str.length()-1));
                String sql = "INSERT INTO Thesis(title, thesisyear, keyword, publishdate, link, meeting,abstract)"
                        +"values("+"?,?,?,?,?,?,?)";
                //预编译
                PreparedStatement ptmt = connection.prepareStatement(sql); //预编译SQL，减少sql执行

                //传参
                ptmt.setString(1, job.getString("displayDocTitle"));
                ptmt.setInt(2, Integer.parseInt(job.getString("publicationYear")));
                if(job.getJSONArray("keywords")!=null) {
                    JSONObject job1 = (JSONObject) job.getJSONArray("keywords").get(0);
                    ptmt.setString(3, job1.getString("kwd"));
                }
                else
                {
                    ptmt.setString(3, "[]");
                }
                ptmt.setString(4, job.getString("dateOfInsertion"));
                ptmt.setString(5, job.getString("doiLink"));
                ptmt.setString(6, "ICCV");
                ptmt.setString(7, job.getString("abstract"));

                ptmt.execute();
            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public  void CVPRImport()
    {
        File file=new File("D:\\论文数据\\CVPR（2000年至2020年，6916篇");
        File[] files=file.listFiles();
        try {
            Connection connection=DBUtil.getConnection();
            Connection c = DBUtil.getConnection();
            Statement s = c.createStatement();
            for(File temp:files)
            {
                System.out.println(temp.getName());
                String str= JsonDateProcessor.readJsonFile("D:\\论文数据\\CVPR（2000年至2020年，6916篇\\"+temp.getName());
                JSONObject job=JSON.parseObject(str.substring(0,str.length()-1));
                String sql = "INSERT INTO Thesis(title, thesisyear, keyword, publishdate, link, meeting,abstract)"
                        +"values("+"?,?,?,?,?,?,?)";
                //预编译
                PreparedStatement ptmt = connection.prepareStatement(sql); //预编译SQL，减少sql执行

                //传参
                ptmt.setString(1, job.getString("displayDocTitle"));
                ptmt.setInt(2, Integer.parseInt(job.getString("publicationYear")));
                if(job.getJSONArray("keywords")!=null) {
                    JSONObject job1 = (JSONObject) job.getJSONArray("keywords").get(0);
                    ptmt.setString(3, job1.getString("kwd"));
                }
                else
                {
                    ptmt.setString(3, "[]");
                }
                ptmt.setString(4, job.getString("dateOfInsertion"));
                ptmt.setString(5, job.getString("doiLink"));
                ptmt.setString(6, "CVPR");
                ptmt.setString(7, job.getString("abstract"));

                ptmt.execute();
            }

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
