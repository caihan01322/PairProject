package com.lz.demo_01.controller;

import com.lz.demo_01.pojo.*;
import com.lz.demo_01.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("my")
public class MyController {
    @Autowired
    private MyService myService;

    //分页查询
    @RequestMapping("list")
    public ResultBean list(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int limit
    ) {
        List<Academics> my = myService.getPage(page, limit);
        int count = myService.findAllArticles();
        return new ResultBean(0, "查询成功", count, my);
    }

    //修改或新增用户
    @RequestMapping("save")
    public ResultBean save(@RequestBody Academics academics) {
        //获取数据条数
        int count = myService.findAllArticles();
        //获取所有用户
        List<Academics> mys = myService.findAllArticlesList();
        // 判断是新增还是修改

        if (myService.findOneAcademic(academics).size() == 0) {//用户不存在
            if (academics.getAcademicNum() <= 0) {
                int index = count - 1;
                if (index < 0) {
                    academics.setAcademicNum(1);
                } else {
                    academics.setAcademicNum(mys.get(index).getAcademicNum() + 1);
                }
            }
            myService.addAcademic(academics);
        } else {
            System.out.println(academics);
            myService.updateAcademic(academics);
        }

        return new ResultBean(200, "保存成功", 0, null);
    }

    //删除用户
    @RequestMapping("remove")
    public ResultBean modify(@RequestBody int[] ids) {
        myService.delAcademics(ids);
        return new ResultBean(200, "删除成功", 0, null);
    }

    //按条件查询
    @RequestMapping("queryByParam")
    public ResultBean queryByParam(@RequestParam(value = "page", required = false) int page,
                                   @RequestParam(value = "limit", required = false) int limit,
                                   @RequestParam(value = "uauthor", required = false) String author,
                                   @RequestParam(value = "uacademicNum", required = false) String academicNum,
                                   @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "keyword", required = false) String keyword
    ) {
        int uid = 0;
        if (academicNum != null && !(academicNum.equals(""))) {
            uid = Integer.valueOf(academicNum);
        }
        System.out.println(author);
        System.out.println(academicNum);
        System.out.println(title);
        System.out.println(keyword);
        //查询所有符合条件数据
        List<Academics> users = myService.queryByParam(author, uid, title, keyword);
        //分页获取符合条件数据
        List<Academics> users2 = myService.queryByParamPage(page, limit, author, uid, title, keyword);
        //获取总条数
        int count = users.size();
        //System.out.println(users2);
        return new ResultBean(0, "查询成功", count, users2);

    }
}
