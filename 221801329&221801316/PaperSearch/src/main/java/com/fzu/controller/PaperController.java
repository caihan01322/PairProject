package com.fzu.controller;

import com.alibaba.fastjson.JSON;
import com.fzu.Util.Result;
import com.fzu.pojo.Paper;
import com.fzu.service.PaperService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class PaperController {
    @Autowired
    PaperService paperService;

    /**
     * showdoc
     * @catalog 论文相关
     * @title 论文查询
     * @description 论文分页查询的接口
     * @method post
     * @url https://ip:8080/queryPage
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {"error_code":0,"data":{"uid":"1","username":"12154545","name":"吴系挂","groupid":2,"reg_time":"1436864169","last_login_time":"0"}}
     * @return_param groupid int 用户组id
     * @return_param name string 用户昵称
     * @remark 这里是备注信息
     */
    @ResponseBody
    @RequestMapping("/queryPage")
    public List<Paper>  queryByPage(@RequestParam("start")Integer start,@RequestParam("rows")Integer rows){
        List<Paper> paperList=paperService.queryPaperByPage(start,rows);
        return paperList;
    }
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
    public List<Paper> queryByKeyword(@RequestParam("keyword")String keyword,
                                      @RequestParam("start")Integer start,
                                      @RequestParam("rows")Integer rows){
        List<Paper> paperList=paperService.queryPaperByKeyword("digital",start,rows);
        return paperList;
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
    public List<Paper> queryByAuthor(@RequestParam("author")String author,
                                     @RequestParam("start")Integer start,
                                     @RequestParam("rows")Integer rows){
        List<Paper> paperList=paperService.queryPaperByAuthor(author,start,rows);
        return paperList;
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
    public String queryTop10ByYear(){
        String data=JSON.toJSONString(paperService.queryTop10ByYear());
        data.replace("\"","\'");
        return  data;
    }


}
