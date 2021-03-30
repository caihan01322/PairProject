package com.pairwork.pairwork.controller;

import com.pairwork.pairwork.entity.Paper;
import com.pairwork.pairwork.entity.User;
import com.example.common.Result;
import com.pairwork.pairwork.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//标识此接口中所有都是返回json数据
@RequestMapping("/user")//给访问链接加个前缀
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    //（注！！！）@PostMapping和@PutMapping作用等同，都是用来向服务器提交信息。
    // 如果是添加信息，倾向于用@PostMapping，如果是更新信息，倾向于用@PutMapping。两者差别不是很明显。
    public Result add(@RequestBody User user){//将传过来的json对象转化为user对象
        userService.add(user);
        return  Result.success();//若执行成功则返回成功
    }

    @GetMapping("/{account}")
    public boolean findByAccount(@PathVariable String account){
        return userService.findByAccount(account);
    }

    @GetMapping("/{id}")
    public Result<Page<Paper>> findCollection(@PathVariable Long id){
        return Result.success(userService.findCollection(5,5,id));
    }
}
