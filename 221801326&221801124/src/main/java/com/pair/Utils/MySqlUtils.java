package com.pair.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pair.dao.KeywordMapper;
import com.pair.dao.PaperKeywordMapper;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Keyword;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author WangWeijia
 * @description MySqlUtils类用于对MySql输入数据，将JSON格式的文本转换成单条数据，并且输入表中。
 */
public class MySqlUtils {
    @Autowired
    PaperMapper paperMapper;

    @Autowired
    KeywordMapper keywordMapper;

    @Autowired
    PaperKeywordMapper paperKeywordMapper;

    /**
     * @return void
     * @author WangWeijia
     * @name putPapers
     * @params [publisher]
     * @description 将文件夹中的每一个文件使用putDataIntoMysql进行处理
     */
    public void putPapers(String publisher) throws IOException {
        File f = new File("C:\\Users\\WWJ20\\Desktop\\data\\" + publisher);
        File[] file = f.listFiles();
        for (File file1 : file) {//对文件夹中的每一个文件
            String path = "C:\\Users\\WWJ20\\Desktop\\data\\" + publisher + "\\" + file1.getName();//得到文件的路径
            if (publisher.equals("ECCV")) {//ECCV
                putDataIntoMysql2(path, publisher);
            } else {//CVPR&ICCV
                putDataIntoMysql1(path, publisher);
            }
            file1.delete();//将文件删除，加快删除速度和插入数据库失败之后可以从失败的点继续
        }
    }

    /**
     * @return void
     * @author WangWeijia
     * @name putDataIntoMysql1
     * @params [path, publisher]
     * @description 对ECCV进行解析
     */
    public void putDataIntoMysql1(String path, String publisher) throws IOException {
        Set<String> kwdList = new HashSet<>();
        FileReader fileReader = new FileReader(path);
        int temp = 0;
        String data = "";//json数据
        while ((temp = fileReader.read()) != -1) {
            data += (char) temp;
        }
        data = data.substring(0, data.length() - 1);//截断JSON中最后一个;
        fileReader.close();
        JSONObject objects = JSON.parseObject(data);//解析JSON
        JSONArray keywords = objects.getJSONArray("keywords");
        if (keywords != null) {
            for (int i = 0; i < keywords.size(); i++) {
                JSONObject jsonObject = keywords.getJSONObject(i);
                JSONArray kwds = jsonObject.getJSONArray("kwd");
                for (Object kwd : kwds) {
                    kwdList.add(kwd.toString());
                }
            }
        } else {
        }
        String title = objects.getString("title");
        String abs = objects.getString("abstract");
        String publicationYear = objects.getString("publicationYear");
        String doiLink = objects.getString("doiLink");
        String pid = "pid_" + new Date().getTime();//根据时间戳生成唯一的pid，也可使用uuid
        paperMapper.insertPaper(new Paper(pid, title, abs, publisher, publicationYear, doiLink, null));
        if (keywords != null) {
            for (String s : kwdList) {
                String kid;
                if (keywordMapper.selectKeyword(s, publisher) == 0) {//如果关键词和年份的组合在keyword表中不存在，则插入一条新的记录
                    kid = "kid_" + new Date().getTime();
                    keywordMapper.insertKeyword(kid, s, publisher, 1);
                } else {//如果存在，则将num+1；
                    kid = keywordMapper.selectKid(s, publisher);
                    keywordMapper.updateKeyword(new Keyword(kid, s, publisher, keywordMapper.selectNum(s, publisher) + 1));
                }
                paperKeywordMapper.insertPK(pid, kid);//在pk表中插入记录
            }
        }
    }

    /**
     * @return void
     * @author WangWeijia
     * @name putDataIntoMysql2
     * @params [path, publisher]
     * @description 对CVPR和ICCV进行解析
     */
    public void putDataIntoMysql2(String path, String publisher) throws IOException {
        Set<String> kwdList = new HashSet<>();
        FileReader fileReader = new FileReader(path);
        int temp = 0;
        String data = "";//json数据
        while ((temp = fileReader.read()) != -1) {
            data += (char) temp;
        }
        fileReader.close();
        JSONObject objects = JSON.parseObject(data);
        JSONArray keywords = objects.getJSONArray("关键词");
        if (keywords != null) {
            for (int i = 0; i < keywords.size(); i++) {
                kwdList.add(keywords.getString(i));
            }
        } else {
        }
        String title = objects.getString("论文名称");
        String abs = objects.getString("摘要");
        String publicationYear = objects.getString("会议和年份").split(" ")[1];
        String doiLink = objects.getString("原文链接");
        String pid = "pid_" + new Date().getTime();
        paperMapper.insertPaper(new Paper(pid, title, abs, publisher, publicationYear, doiLink, null));
        if (keywords != null) {
            for (String s : kwdList) {
                String kid;
                if (keywordMapper.selectKeyword(s, publisher) == 0) {
                    kid = "kid_" + new Date().getTime();
                    keywordMapper.insertKeyword(kid, s, publisher, 1);
                } else {
                    kid = keywordMapper.selectKid(s, publisher);
                    keywordMapper.updateKeyword(new Keyword(kid, s, publisher, keywordMapper.selectNum(s, publisher) + 1));
                }
                paperKeywordMapper.insertPK(pid, kid);
            }
        }
    }
}
