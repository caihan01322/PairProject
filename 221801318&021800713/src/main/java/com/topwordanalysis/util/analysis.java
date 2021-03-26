package com.topwordanalysis.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.topwordanalysis.databaseOperation.model.Paper;
import com.topwordanalysis.databaseOperation.model.TopWord;
import lombok.experimental.var;
import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析json数据的工具类
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
public class analysis {
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    static String ECCV_ROOT = "E://Paper/ECCV";
    static String ICCV_ROOT = "E://Paper/ICCV";
    static String CVPR_ROOT = "E://Paper/CVPR";


    public static List<Paper> readPaperOfECCV() {
        File root = new File(ECCV_ROOT);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(4096);
        for (File file : files) {
            JSONObject jsonObject = JSON.parseObject(readMMAP(file));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("论文名称"));
            paper.setPaperAbstract(jsonObject.getString("摘要"));
            paper.setLink(jsonObject.getString("原文链接"));
            paper.setType("ECCV");
            paper.setYear(jsonObject.getString("会议和年份").split(" ")[1]);
            papers.add(paper);
        }
        return papers;
    }

    public static List<TopWord> readTopWordOfECCV() {
        File root = new File(ECCV_ROOT);
        File[] files = root.listFiles();
        List<TopWord> topWords = new ArrayList<>(4096);
        for (File file : files) {
            JSONObject jsonObject = JSON.parseObject(readMMAP(file));
            JSONArray keys = null;
            keys = jsonObject.getJSONArray("关键词");
            for (Object key : keys) {
                TopWord topWord = new TopWord();
                topWord.setYear(jsonObject.getString("会议和年份").split(" ")[1]);
                topWord.setType("ECCV");
                topWord.setTopWord(key.toString());
                topWords.add(topWord);
            }
        }
        return topWords;
    }


    public static List<Paper> readPaperOfICCV() {
        File root = new File(ICCV_ROOT);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(4096);
        for (File file : files) {
            String ori = readMMAP(file);
            JSONObject jsonObject = JSON.parseObject(ori.substring(0, ori.length()-1));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("title"));
            paper.setPaperAbstract(jsonObject.getString("abstract"));
            paper.setLink(jsonObject.getString("doiLink"));
            paper.setType("ICCV");
            paper.setYear(jsonObject.getString("publicationYear"));
            papers.add(paper);
        }
        return papers;
    }

    public static List<TopWord> readTopWordOfICCV() {
        File root = new File(ICCV_ROOT);
        File[] files = root.listFiles();
        List<TopWord> topWords = new ArrayList<>(4096);
        for (File file : files) {
            String ori = readMMAP(file);
            JSONObject jsonObject = JSON.parseObject(ori.substring(0, ori.length()-1));

            if (jsonObject.getJSONArray("keywords")==null){
                continue;
            }
            if(jsonObject.getJSONArray("keywords").getJSONObject(0)==null){
                continue;
            }
            if (jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd")==null){
                continue;
            }

            JSONArray keys = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            for (Object key : keys) {
                TopWord topWord = new TopWord();
                topWord.setYear(jsonObject.getString("publicationYear"));
                topWord.setType("ICCV");
                topWord.setTopWord(key.toString());
                topWords.add(topWord);
            }
        }
        return topWords;
    }


    public static List<Paper> readPaperOfCVPR() {
        File root = new File(CVPR_ROOT);
        File[] files = root.listFiles();
        List<Paper> papers = new ArrayList<>(7000);
        for (File file : files) {
            String ori = readMMAP(file);
            JSONObject jsonObject = JSON.parseObject(ori.substring(0, ori.length()-1));
            Paper paper = new Paper();
            paper.setTitle(jsonObject.getString("title"));
            paper.setPaperAbstract(jsonObject.getString("abstract"));
            paper.setLink(jsonObject.getString("doiLink"));
            paper.setType("CVPR");
            paper.setYear(jsonObject.getString("publicationYear"));
            papers.add(paper);
        }
        return papers;
    }

    public static List<TopWord> readTopWordOfCVPR() {
        File root = new File(CVPR_ROOT);
        File[] files = root.listFiles();
        List<TopWord> topWords = new ArrayList<>(7000);
        for (File file : files) {
            String ori = readMMAP(file);
            JSONObject jsonObject = JSON.parseObject(ori.substring(0, ori.length()-1));

            if (jsonObject.getJSONArray("keywords")==null){
                continue;
            }
            if(jsonObject.getJSONArray("keywords").getJSONObject(0)==null){
                continue;
            }
            if (jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd")==null){
                continue;
            }
            JSONArray keys = jsonObject.getJSONArray("keywords").getJSONObject(0).getJSONArray("kwd");
            for (Object key : keys) {
                TopWord topWord = new TopWord();
                topWord.setType("CVPR");
                topWord.setYear(jsonObject.getString("publicationYear"));
                topWord.setTopWord(key.toString());
                topWords.add(topWord);
            }
        }
        return topWords;
    }



    /**
     * 文件读入，使用mmap
     *
     * @param file 输入文件
     * @return 文件内容，如果空则为""
     */
    public static String readMMAP(File file){
        RandomAccessFile raf = null;
        MappedByteBuffer mbb = null;
        try {
            raf = new RandomAccessFile(file, "r");
            mbb = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            if (mbb != null){
                return ENCODING.decode(mbb).toString();
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mbb != null){
                Cleaner var1 = ((DirectBuffer)mbb).cleaner();
                if (var1 != null) {
                    var1.clean();
                }
            }
            if (raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
