package com.pairwork.pairwork.controller;

import com.pairwork.pairwork.common.Result;
import com.pairwork.pairwork.entity.Paper;
import com.pairwork.pairwork.entity.User;

import com.pairwork.pairwork.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/user")//给访问链接加个前缀
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    //（注！！！）@PostMapping和@PutMapping作用等同，都是用来向服务器提交信息。
    // 如果是添加信息，倾向于用@PostMapping，如果是更新信息，倾向于用@PutMapping。两者差别不是很明显。
    public Result add(User user){//将传过来的json对象转化为user对象
        userService.add(user);
        return  Result.success();//若执行成功则返回成功
    }

    @GetMapping("/getAccount/{account}")
    public List<User> findByAccount(@PathVariable String account){
        return userService.findByAccount(account);
    }

//    @GetMapping("/getUserCollection/{user_id}")
//    public Result<Page<Paper>> findCollection(@PathVariable Long user_id){
//        return Result.success(userService.findCollection(5,5,user_id));
//    }


//    public Result<List<Paper>> findColleciont(@PathVariable Long user_id){
//        return Result.success(userService.findCollecion(user_id));
//    }

    @GetMapping("/example1/{money}")//测试接口链接
    public Result example1(Float money, String product){
//        System.out.println("product:"+ product);//product:洗洁精
        System.out.println("money:"+ money);//money:123.0
        return Result.success();
    }
}
