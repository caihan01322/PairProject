package com.practice.pairproject.controller;

//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.plugins.Page;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.PaperService;

import com.practice.pairproject.util.AjaxResponse;
import com.practice.pairproject.util.MyPage;
import com.practice.pairproject.util.StoragePaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/paper")
public class PaperController {

    //默认每页paper个数
    private static int pageSize = 10;

    @Autowired
    private PaperService paperService;

    @Autowired
    private StoragePaper storagePaper;

    @ResponseBody
    @PostMapping({"/load/ECCV"})
    public String insertPaper1() {

        //ECCV 1  ECCV+ 2
        int size1 = storagePaper.loadECCVpapers(1);
        int size2 = storagePaper.loadECCVpapers(2);
        return String.valueOf(size1 + size2);
    }

    @ResponseBody
    @PostMapping({"/load/CVPR"})
    public String insertPaper2() {

        //CVPR 0
        int size = storagePaper.loadCVPR_ICCVpapers(0);
        return String.valueOf(size);
    }

    @ResponseBody
    @PostMapping({"/load/ICCV"})
    public String insertPaper3() {

        //ICCV 3
        int size = storagePaper.loadCVPR_ICCVpapers(3);
        return String.valueOf(size);
    }


    /**
     * 删除一篇Paper，使用DELETE方法，参数是id
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public AjaxResponse deletePaper(@PathVariable("id") Integer id){

        int result = paperService.deleteByPrimaryKey(id);
        if(result < 1){
            log.error("【删除失败！】: " + id);
            return AjaxResponse.fail(500, "【删除失败！】: " + id);
        }

        return AjaxResponse.success("【删除成功！】");
    }

    /* AJAX:
        var pids = new Array();
        //这里组装testPOJO数组
        $.ajax({
            url:“paper/delete”,
            data:JSON.stringify(pids),
            type:"delete",
            dataType:"json",
            contentType:"application/json",
            success:function (res) {
            },
            error:function(msg){
            }
        });
    */
    /**
     * 删除选中的Paper，使用DELETE方法，参数是id-list
     * @param pids
     * @return
     */
    @DeleteMapping("/delete")
    public AjaxResponse deletePapers(@RequestBody List<Integer> pids){
        log.info("233333333");
        int result = paperService.deleteByPrimaryKeyList(pids);
        if(result != pids.size()){
            log.error("【删除失败！】: " + result);
            return AjaxResponse.fail(500, "【删除失败！】: " + result);
        }
        return AjaxResponse.success("【删除成功！】");
    }


    /**
     * 【分页】
     * 多条件联合模糊查询
     * @param title
     * @param pid
     * @param keyword
     * @param sort
     * @param model
     * @return
     */
    @GetMapping("/search")
    public AjaxResponse searchUser(
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name= "title" , defaultValue = "") String title,
            @RequestParam(name= "pid" , defaultValue = "") String pid,
            @RequestParam(name= "keyword" , defaultValue = "") String keyword,
            @RequestParam(name= "sort" , defaultValue = "1") String sort, Model model){
        if( pageSize <= 0 ){
            pageSize = this.pageSize;
        }

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title", title);
        paramMap.put("pid", pid);
        paramMap.put("keyword", keyword);
        paramMap.put("sort", sort);

        //第一个参数：表示当前第几页;
        //第二个参数：每页显示的条数据;
        MyPage<Paper> page = new MyPage<>(pageNum, pageSize);
        paperService.searchPaper(page, paramMap);
        List<Paper> paperList = page.getRecords();

        if( paperList.isEmpty()){
            log.info("【未查询到数据】 " );
            return AjaxResponse.success("【未查询到数据】");
        }
        //model.addAttribute("paperList",paperList);

        return AjaxResponse.success(page, "【查询成功！】");
    }


    /**
     * 【无分页】
     * 查询所有论文列表
     * @param model
     * @return
     */
    /*@GetMapping("/show/all")
    public AjaxResponse showAll(Model model){
        List<Paper> paperList = paperService.selectAll();
        if(paperList.isEmpty()){
            log.error("【查询所有论文列表失败！】");
            return AjaxResponse.fail(500, "【查询所有论文列表失败！】");
        }

        //model.addAttribute("list",list2);
        return AjaxResponse.success(paperList,"【查询所有论文列表成功！】");
    }*/

    /**
     * 【分页】
     * 查询所有论文列表
     * @param pageNum  第几页
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/show")
    public AjaxResponse showPage(@RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                 Model model){
        if( pageSize <= 0 ){
            pageSize = this.pageSize;
        }

        //第一个参数：表示当前第几页;
        //第二个参数：每页显示的条数据;
        MyPage<Paper> page = new MyPage<>(pageNum, pageSize);
        /*
        //只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页!!!!
        PageHelper.startPage(pageNum, pageSize);
        List<Paper> paperList = paperService.selectAll();
        */
        paperService.selectAll(page);
        List<Paper> paperList = page.getRecords();

        if(paperList.isEmpty()){
            log.error("【查询所有论文列表失败！】");
            return AjaxResponse.fail(500, "【查询所有论文列表失败！】");
        }
        /*
        //该分页的json数据
        PageInfo<Paper> papers = PageInfo.of(paperList);
        */
        return AjaxResponse.success(page, "【查询所有论文列表成功！】");
    }

    /**
     * 点击某个keyword->相关的论文列表
     * @param keyword
     * @return
     */
    @GetMapping(value = {"/list/{keyword}"})
    public AjaxResponse GetUser(@PathVariable("keyword") String keyword,
                                @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                Model model) {
        if( pageSize <= 0 ){
            pageSize = this.pageSize;
        }

        //第一个参数：表示当前第几页;
        //第二个参数：每页显示的条数据;
        MyPage<Paper> page = new MyPage<>(pageNum, pageSize);
        paperService.selectPaperByKeyword(page, keyword);
        List<Paper> paperList = page.getRecords();

        if(paperList.isEmpty()){
            log.error("【查询keyword-->论文列表失败！】");
            return AjaxResponse.fail(500, "【查询keyword-->论文列表失败！】");
        }
        return AjaxResponse.success(page, "【查询keyword-->论文列表成功！】");

    }

}
