package com.fzu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fzu.Util.Result;
import com.fzu.pojo.Paper;
import com.fzu.service.PaperService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin(origins = "*")
public class PaperController {
    @Autowired
    PaperService paperService;

    /**
     * showdoc
     * @catalog 论文相关
     * @title 论文按照关键词模糊查询
     * @description 论文按照关键词分页查询的接口
     * @method post
     * @url https://ip:8080/queryByKeyword
     * @param keyword 必选 String 关键词
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @remark 这里是备注信息
     */
    @ResponseBody
    @RequestMapping("/queryByKeyword")
    public List<JSONObject> queryByKeyword(@RequestParam("userId")Integer userId, @RequestParam("word")String keyword,
                                      @RequestParam("start")Integer start,
                                      @RequestParam("rows")Integer rows){
        if(keyword=="")
            return paperService.queryPaperByPage(userId,start,rows);
        else
        return  paperService.queryPaperByKeyword(userId,keyword,start,rows);
    }
    /**
     * showdoc
     * @catalog 论文相关
     * @title 论文按照关键词模糊查询
     * @description 论文按照关键词分页查询的接口
     * @method post
     * @url https://ip:8080/queryByAuthor
     * @param author 必选 String 作者姓名
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @remark 这里是备注信息
     */
    @ResponseBody
    @RequestMapping("/queryByAuthor")
    public List<JSONObject> queryByAuthor(@RequestParam("userId")Integer userId,
                                          @RequestParam("word")String author,
                                          @RequestParam("start")Integer start,
                                          @RequestParam("rows")Integer rows){
        if(author=="")
            return paperService.queryPaperByPage(userId,start,rows);
        else
            return  paperService.queryPaperByAuthor(userId,author,start,rows);
    }
    /**
     * showdoc
     * @catalog 论文相关
     * @title 论文按照关键词模糊查询
     * @description 论文按照关键词分页查询的接口
     * @method post
     * @url https://ip:8080/queryByAuthor
     * @param author 必选 String 作者姓名
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @remark 这里是备注信息
     */
    @ResponseBody
    @RequestMapping("/queryByTitle")
    public List<JSONObject> queryByTitle(@RequestParam("userId")Integer userId,
                                          @RequestParam("word")String title,
                                          @RequestParam("start")Integer start,
                                          @RequestParam("rows")Integer rows){
        if(title=="")
            return paperService.queryPaperByPage(userId,start,rows);
        else
            return  paperService.queryPaperByTitle(userId,title,start,rows);
    }
    /**
     * showdoc
     * @catalog 论文相关
     * @title 关键词统计
     * @description 按年份、会议统计排名前10的关键词
     * @method post
     * @url https://ip:8080/queryTop10ByYear
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @remark 返回js数据
     */
    @ResponseBody
    @RequestMapping("/queryTop10ByYear")
    public List<JSONObject> queryTop10ByYear(){
  /*      String data=JSON.toJSONString(paperService.queryTop10ByYear());
        data.replace("\"","\'");*/
        return  paperService.queryTop10ByYear();
    }
    @ResponseBody
    @RequestMapping("/register")
    public void register(@RequestBody JSONObject jsonObject){
        String username=jsonObject.getString("username");
        String password=jsonObject.getString("password");
        paperService.register(username,password);
    }

    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Integer> login(@RequestBody JSONObject jsonObject){
        String username=jsonObject.getString("username");
        String password=jsonObject.getString("password");
        return paperService.login(username,password);
    }

    @ResponseBody
    @RequestMapping("/addLike")
    public String addLike(@RequestParam("userId") Integer userId,@RequestParam("paperId") Integer paperId){
        Map<String,Integer> result=new HashMap<>();
        paperService.addLike(userId,paperId);
        result.put("status",1);
        return JSON.toJSONString(result);
    }

    @ResponseBody
    @RequestMapping("/queryLike")
    public List<Paper>  queryLike(@RequestParam("userId")Integer userId, @RequestParam("start")Integer start,@RequestParam("rows")Integer rows){
        List<Paper> paperList=paperService.queryLikes(userId,start,rows);
        return paperList;
    }
    @ResponseBody
    @RequestMapping("/deleteLike")
    public String deleteLike(@RequestParam("userId") Integer userId,@RequestParam("paperId") Integer paperId){
        Map<String,Integer> result=new HashMap<>();
        paperService.deleteLike(userId,paperId);
        result.put("status",1);
        return JSON.toJSONString(result);
    }

}
