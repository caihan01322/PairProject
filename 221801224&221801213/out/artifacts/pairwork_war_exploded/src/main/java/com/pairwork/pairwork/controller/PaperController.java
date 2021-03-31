package com.pairwork.pairwork.controller;


import com.example.common.Result;
import com.pairwork.pairwork.entity.Paper;
import com.pairwork.pairwork.service.PaperService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

//标识此接口中所有都是返回json数据
@RequestMapping("/paper")//给访问链接加个前缀
public class PaperController {

    @Resource
    PaperService paperService;

    @GetMapping("/{name}")
    public Result<Page<Paper>> findNameLike(@PathVariable String name){
        return Result.success(paperService.findPage(5,10,name));
    }


}
