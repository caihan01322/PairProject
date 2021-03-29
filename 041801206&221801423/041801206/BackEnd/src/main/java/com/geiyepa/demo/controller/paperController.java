package com.geiyepa.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geiyepa.demo.entity.paper;
import com.geiyepa.demo.service.paperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping(value = "/", produces = "application/json; charset=utf-8")
public class paperController {

    @Autowired
    private paperService paperService;

    @ResponseBody
    @RequestMapping(value = "/searchPaperByTitle" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray searchPaperByTitle(@RequestBody String JSONBody){

        JSONObject object = JSONObject.parseObject(JSONBody);
        String searchWord = (String) object.get("searchWord");
        List<paper> paperList = paperService.selectLikeWord("%"+searchWord+"%");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(paperList));
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索文章 ##搜索标题词：" + searchWord + "  ##搜索结果数：" + paperList.size());

        return array;

    }

    @ResponseBody
    @RequestMapping(value = "/searchPaperByKeyword" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray searchPaperByKeyword(@RequestBody String JSONBody){

        JSONObject object = JSONObject.parseObject(JSONBody);
        String searchKeyword = (String) object.get("searchKeyword");
        List<paper> paperList = paperService.selectLikeKeyword("%"+ searchKeyword +"%");
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(paperList));
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索文章 ##搜索关键词：" + searchKeyword + "  ##搜索结果数：" + paperList.size());

        return array;
    }

    @ResponseBody
    @RequestMapping(value = "/searchPaper" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray search(@RequestBody String JSONBody){
        JSONObject object = JSONObject.parseObject(JSONBody);
        String searchKeyword = (String) object.get("searchKeyword");
        List<paper> paperList1 = paperService.selectLikeKeyword("%"+ searchKeyword +"%");
        List<paper> paperList2 =paperService.selectLikeWord("%"+ searchKeyword +"%");
        for (paper p2:paperList2
             ) {
            int flag = 0;
            for (paper p1:paperList1
                 ) {
                 flag = 0;
                if(p1.equals(p2))  flag = 1;
            }
            if(flag ==0 ) paperList1.add(p2);
        }
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(paperList1));
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索文章 ##搜索关键词：" + searchKeyword + "  ##搜索结果数：" + paperList1.size());

        return array;
    }

    @ResponseBody
    @RequestMapping(value = "/getDownLoadPaper" , method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public JSONArray getDownLoadPaper(@RequestBody String JSONBody){
        ArrayList<String> files ;
        JSONArray array= new JSONArray();
        String filepath="/root/pairproject/paperpdf";
        files = new ArrayList<String>();
        File file = new File(filepath);
        File[] tempLists = file.listFiles();
        for (int i = 0; i < tempLists.length; i ++) {
            if (tempLists[i].isFile()) {
                files.add(tempLists[i].getName());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("fileName",tempLists[i].getName());
                jsonObject.put("size",getPrintSize((long)tempLists[i].length()));
                array.add(jsonObject);
            }
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date) + " ====> 搜索文章 ##搜索关键词：无"+ " ##获取下载列表");

        return array;
    }

    public static String getPrintSize(long size) {
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }

        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {

            size = size * 100 / 1024;
          return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

    @RequestMapping("/downloadFile")
    public JSONObject download(@RequestBody String JSONBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject o = JSONObject.parseObject(JSONBody);
        String fileName =(String) o.get("dlFileName");

        String filepath="/root/pairproject/paperpdf";

        File file1 = new File(filepath);
        File[] tempLists = file1.listFiles();
        for (int i = 0; i < tempLists.length; i ++) {
            if (tempLists[i].isFile() && tempLists[i].getName().equals(fileName)) {
                fileName = tempLists[i].getAbsolutePath();
            }
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date())+"  文件下载，文件名="+fileName);
        JSONObject object=new JSONObject();
        //获取文件的绝对路径
        try {
            File file = new File(fileName);
            //获取输入流对象（用于读文件）
            FileInputStream fis = new FileInputStream(file);
            //获取文件后缀（.txt）
            String extendFileName = file.getName().substring(file.getName().lastIndexOf('.'));
            //动态设置响应类型，根据前台传递文件类型设置响应类型
            response.setContentType(request.getSession().getServletContext().getMimeType(extendFileName));
            //设置响应头,attachment表示以附件的形式下载，inline表示在线打开
            response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            //获取输出流对象（用于写文件）
            ServletOutputStream os = response.getOutputStream();
            //下载文件,使用spring框架中的FileCopyUtils工具
            FileCopyUtils.copy(fis, os);
            object.fluentPut("status","下载成功！");
            return object;
        }catch (Exception e){
            System.out.println(e.getMessage());
            object .fluentPut("status","下载失败！");
            return object;
        }
    }


}
