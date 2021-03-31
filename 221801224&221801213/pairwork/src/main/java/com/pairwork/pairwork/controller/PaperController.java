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

    /**
     * 论文列表模糊查询
     * @param name
     * @return List<Paper>
     * 返回查询到的论文列表
     */
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

    /**
     * 个人收藏所有查询页面
     * @param pageNum
     * @param pageSize
     * @param user_id
     * @return Page<Paper>
     * 返回一个分好页的个人论文收藏页面
     */
    @GetMapping("/getUserCollection/{user_id}")
    public Result<Page<Paper>> findCollection(@RequestParam(required = false,value = "1") Integer pageNum,
                                              @RequestParam(required = false,value = "10") Integer pageSize,
                                              @PathVariable Long user_id){
        return Result.success(paperService.findCollection(1,10,user_id));
    }

    /**
     * 删除所有论文列表中的某一篇论文
     * @param paper_id
     * @return 删除成功信息
     */
    @DeleteMapping("/delPaper/{paper_id}")
    public Result delete(@PathVariable Long paper_id){
        paperService.delPaper(paper_id);
        return Result.success();
    }

    /**
     *
     * 计算某一篇论文的Top10
     * @param paper_id
     * @return List<String>
     * 返回Top10
     *
     */
    @GetMapping("/getKeyWord/{paper_id}")
    public Result<List<String>>  getKeyWordK(@PathVariable Long paper_id){
       return  Result.success(paperService.getKeywords(paper_id));
    }

    /**
     * 计算文章Top10的出现频率
     * @param paper_id
     * @return List<Integer>
     * 返回文章Top10的出现频率
     */
    @GetMapping("/getKeyWordFre/{paper_id}")
    public Result<List<Integer>>  getKeyWordFre(@PathVariable Long paper_id){
        return  Result.success(paperService.getKeywordsV(paper_id));
    }

    /**
     * 计算所有文章的Top10
     * @return List<String>
     *  返回所有论文Top10热词
     */
    @GetMapping("/getKeyWordBySeg")
    public Result<List<String>> getKeyWordBySeg(){
        return Result.success(paperService.getKeywordsBySeg());
    }
}
