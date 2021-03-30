package com.pairwork.pairwork.controller;

import com.pairwork.pairwork.common.Result;
import com.pairwork.pairwork.entity.Paper;
import com.pairwork.pairwork.service.PaperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/paper")//给访问链接加个前缀
public class PaperController {

    @Resource
    PaperService paperService;

    @GetMapping("/findNameLike/{name}")
    public Result<List<Paper>> findNameLike(@PathVariable String name){
        return Result.success(paperService.findPage(name));
    }
//
//    public Result<Page<Paper>> findNameLike(@RequestParam(required = false,value = "1") Integer pageNum,
//                                              @RequestParam(required = false,value = "10") Integer pageSize,
//                                              @PathVariable String name){
//        return Result.success(paperService.findPage(5,10,name));
//    }

//    @GetMapping("/getUserCollection/{user_id}")
//    public Result<List<Paper>> findCollection(@PathVariable Long user_id){
//        return Result.success(paperService.findCollecion(user_id));
//    }

    @GetMapping("/getUserCollection/{user_id}")
    public Result<Page<Paper>> findCollection(@RequestParam(required = false,value = "1") Integer pageNum,
                                              @RequestParam(required = false,value = "10") Integer pageSize,
                                              @PathVariable Long user_id){
        return Result.success(paperService.findCollection(1,10,user_id));
    }

    @DeleteMapping("/delPaper/{paper_id}")
    public Result delete(@PathVariable Long paper_id){
        paperService.delPaper(paper_id);
        return Result.success();
    }

    @GetMapping("/getKeyWord/{paper_id}")
    public Result<List<String>>  getKeyWordK(@PathVariable Long paper_id){
       return  Result.success(paperService.getKeywords(paper_id));
    }

    @GetMapping("/getKeyWordFre/{paper_id}")
    public Result<List<Integer>>  getKeyWordFre(@PathVariable Long paper_id){
        return  Result.success(paperService.getKeywordsV(paper_id));
    }
}
