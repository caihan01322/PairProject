package com.example.demo11.ViewController;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SearchViewController
{
    @Autowired
    @ApiOperation(value = "测试接口",notes = "222",tags = "测试",httpMethod = "GET")
    @GetMapping("/main")
    //@RequestMapping("/main")
    public String getMain()
    {
        return "Welcome";
    }
}
