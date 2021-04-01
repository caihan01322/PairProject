package com.fzu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
     * @url https://120.77.84.235:8080/queryByKeyword
     * @param userId 必选 Integer 用户id
     * @param word 必选 String 关键词
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {"total": 189468,"paper": [{"data": {"id": 1,"title": "3-D model construction using range and image data","abstractContent": "This paper deals with the automated creation of geometric and photometric"meet": "CVPR","year": 2000,"link": "https://doi.org/10.1109/CVPR.2000.855865","keywords": ["Bismuth",..."photorealistic models"],"author": ["I. Stamos","P.E. Allen"]},"isLike": 1},...]}
     * @return_param data 数据
     * @return_param isLike int 是否被收藏
     */
    @ResponseBody
    @RequestMapping("/queryByKeyword")
    public JSONObject queryByKeyword(@RequestParam("userId")Integer userId, @RequestParam("word")String keyword,
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
     * @title 论文按照作者模糊查询
     * @description 论文按照作者分页查询的接口
     * @method post
     * @url https://120.77.84.235:8080/queryByAuthor
     * @param userId 必选 Integer 用户id
     * @param word 必选 String 作者
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {
     *     "total": 189468,
     *     "paper": [
     *         {
     *             "data": {
     *                 "id": 1,
     *                 "title": "3-D model construction using range and image data",
     *                 "abstractContent": "This paper deals with the automated creation of geometric and photometric
     *                 "meet": "CVPR",
     *                 "year": 2000,
     *                 "link": "https://doi.org/10.1109/CVPR.2000.855865",
     *                 "keywords": [
     *                     "Bismuth",
     *                      ...
     *                     "segmentation algorithms",
     *                     "photorealistic models"
     *                 ],
     *                 "author": [
     *                     "I. Stamos",
     *                     "P.E. Allen"
     *                 ]
     *             },
     *             "isLike": 1
     *         },
     *         ...
     *         ]
     * }
     * @return_param data 数据
     * @return_param isLike int 是否被收藏
     */
    @ResponseBody
    @RequestMapping("/queryByAuthor")
    public JSONObject queryByAuthor(@RequestParam("userId")Integer userId,
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
     * @title 论文按照标题模糊查询
     * @description 论文按照标题分页查询的接口
     * @method post
     * @url https://120.77.84.235:8080/queryByTitle
     * @param userId 必选 Integer 用户id
     * @param word 必选 String 标题
     * @param start 必选 Integer 初始偏移
     * @param rows 必选 Integer 页面大小
     * @return {
     *     "total": 189468,
     *     "paper": [
     *         {
     *             "data": {
     *                 "id": 1,
     *                 "title": "3-D model construction using range and image data",
     *                 "abstractContent": "This paper deals with the automated creation of geometric and photometric
     *                 "meet": "CVPR",
     *                 "year": 2000,
     *                 "link": "https://doi.org/10.1109/CVPR.2000.855865",
     *                 "keywords": [
     *                     "Bismuth",
     *                     ...
     *                     "photorealistic models"
     *                 ],
     *                 "author": [
     *                     "I. Stamos",
     *                     "P.E. Allen"
     *                 ]
     *             },
     *             "isLike": 1
     *         },
     *         ...
     *         ]
     * }
     * @return_param data 数据
     * @return_param isLike int 是否被收藏
     */
    @ResponseBody
    @RequestMapping("/queryByTitle")
    public JSONObject queryByTitle(@RequestParam("userId")Integer userId,
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
     * @url https://120.77.84.235:8080/queryTop10ByYear
     * @return [
     *     {
     *         "parent": "",
     *         "name": "顶会五年总计",
     *         "id": "0.0"
     *     },
     *     ...
     *     {
     *         "parent": "",
     *         "name": "顶会五年总计",
     *         "id": "0.0"
     *         "value": 100
     *     }
     *     ...
     * ]
     * @return_param 数据
     */
    @ResponseBody
    @RequestMapping("/queryTop10ByYear")
    public List<JSONObject> queryTop10ByYear(){
        return  paperService.queryTop10ByYear();
    }
    /**
     * showdoc
     * @catalog 用户相关
     * @title 注册
     * @description 注册
     * @method post
     * @url https://120.77.84.235:8080/register
     * @param username 必选 String 用户名
     * @param password 必选 String 密码
     * @return void
     */
    @ResponseBody
    @RequestMapping("/register")
    public void register(@RequestBody JSONObject jsonObject){
        String username=jsonObject.getString("username");
        String password=jsonObject.getString("password");
        paperService.register(username,password);
    }
    /**
     * showdoc
     * @catalog 用户相关
     * @title 登录
     * @description 登录
     * @method post
     * @url https://120.77.84.235:8080/login
     * @param username 必选 String 用户名
     * @param password 必选 String 密码
     * @return {
     *     "userId":1
     * }
     * @return_param 用户Id
     */

    @ResponseBody
    @RequestMapping("/login")
    public Map<String,Integer> login(@RequestBody JSONObject jsonObject){
        String username=jsonObject.getString("username");
        String password=jsonObject.getString("password");
        return paperService.login(username,password);
    }
    /**
     * showdoc
     * @catalog 用户相关
     * @title 添加收藏
     * @description 收藏
     * @method post
     * @url https://120.77.84.235:8080/addLike
     * @param userId 必选 Integer 用户Id
     * @param paperId 必选 Integer 论文Id
     * @return {
     *     "status":1
     * }
     * @return_param 添加后的状态
     */
    @ResponseBody
    @RequestMapping("/addLike")
    public String addLike(@RequestParam("userId") Integer userId,@RequestParam("paperId") Integer paperId){
        Map<String,Integer> result=new HashMap<>();
        paperService.addLike(userId,paperId);
        result.put("status",1);
        return JSON.toJSONString(result);
    }
    /**
     * showdoc
     * @catalog 用户相关
     * @title 查看收藏
     * @description 查看收藏论文列表
     * @method post
     * @url https://120.77.84.235:8080/queryLike
     * @param userId 必选 Integer 用户Id
     * @param start 必选  Integer  初始偏移
     * @param rows 必选 Integer 页面大小(行数)
     * @return {
     *     "status":1
     * }
     * @return_param 删除后的状态
     */
    @ResponseBody
    @RequestMapping("/queryLike")
    public JSONObject  queryLike(@RequestParam("userId")Integer userId, @RequestParam("start")Integer start,@RequestParam("rows")Integer rows){
        return paperService.queryLikes(userId,start,rows);
    }

    /**
     * showdoc
     * @catalog 用户相关
     * @title 移除收藏
     * @description 移除收藏
     * @method post
     * @url https://120.77.84.235:8080/deleteLike
     * @param userId 必选 Integer 用户Id
     * @param paperId 必选 Integer 论文Id
     * @return {
     *     "status":1
     * }
     * @return_param 删除后的状态
     */
    @ResponseBody
    @RequestMapping("/deleteLike")
    public String deleteLike(@RequestParam("userId") Integer userId,@RequestParam("paperId") Integer paperId){
        Map<String,Integer> result=new HashMap<>();
        paperService.deleteLike(userId,paperId);
        result.put("status",1);
        return JSON.toJSONString(result);
    }
    /**
     * showdoc
     * @catalog 论文相关
     * @title 关键词统计
     * @description 按年份、会议统计排名前10的关键词
     * @method post
     * @url https://120.77.84.235:8080/keywordTrends
     * @return [
     *     [
     *         "product",
     *         "2016",
     *         "2017",
     *         "2018",
     *         "2019",
     *         "2020"
     *     ],
     *     [
     *         "Feature extraction",
     *         235,
     *         461,
     *         270,
     *         466,
     *         214
     *     ],
     *     ...
     * ]
     * @return_param 数据
     */
    @ResponseBody
    @RequestMapping("/keywordTrends")
    public JSONArray keywordTrends(){
        return paperService.getKeywordTrends();
    }

}
