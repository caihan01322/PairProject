package com.example.demo11.controller;

import com.example.demo11.ajAxResponse;
import com.example.demo11.dao.UserDaoImpl;
import com.example.demo11.dao.UserJBDCDAO;
import com.example.demo11.model.Artical;
import com.example.demo11.model.User;

import com.example.demo11.service.ArticalService;
import com.example.demo11.service.UserService;
import com.example.demo11.service.UserServiceImp;

import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController()
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public ajAxResponse Login(@RequestParam String Account,@RequestParam String password)
    {

        User user = userService.getUserByAccount(Account,password);
        if(user.account.length() == 0)
        {
            return ajAxResponse.fail("用户名或密码错误");
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("imgUrl",userService.getImg(Account));
        map.put("Account",user.account);
        return ajAxResponse.successfully(map);

    }
    @PostMapping(value = "/register")
    public ajAxResponse register(@RequestParam String Account,@RequestParam String password)
    {
        if(Account.length()<6 || password.length()<6)
        {
            return ajAxResponse.fail("用户名密码不符合规范(6位)");
        }
        boolean isok = userService.Register(Account,password);
        if(isok)
        {
            return ajAxResponse.successfully(null);
        }
        return ajAxResponse.fail("用户名已存在");

    }
    @PostMapping(value = "imgUpload")
    public ajAxResponse imgUpload(@RequestParam String Account,@RequestParam(value = "file")MultipartFile file)
    {
        ArrayList<String> suffixs = new ArrayList<String>()
        {
            {
                add(".jpg");
                add(".jepg");
                add(".gif");
                add(".png");
                add(".tif");
                add(".psd");
            }
        };
        if(file.isEmpty())
        {
            return ajAxResponse.fail("上传失败 文件为空!");
        }
        String filename = Account;
        String suffixname = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        suffixname = suffixname.toLowerCase();
        if(!suffixs.contains(suffixname))
        {
            return ajAxResponse.fail("不支持的文件格式");
        }
        filename+=suffixname;
        String path = "/usr/img/";
        filename=path+filename;
        File dest = new File(filename);
        try {
            file.transferTo(dest);
            filename = "http://121.5.100.116:8080/image/"+Account+suffixname;
            userService.saveImg(Account,filename);
        }catch (Exception e)
        {

            e.printStackTrace();
            return ajAxResponse.fail("上传失败");
        }

        return ajAxResponse.successfully(filename);
    }
    @PostMapping("/updateInfo")
    public ajAxResponse updateInfo(@RequestParam String Account,@RequestParam String username,@RequestParam String password)
    {
        if(userService.changeInfo(Account,password,username) == false)
        {
            return ajAxResponse.fail("更新失败");
        }
        return ajAxResponse.successfullyUpdate("更新成功！",1);
    }


}
