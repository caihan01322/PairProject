package com.practice.pairproject.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.pojo.User;
import com.practice.pairproject.service.PaperService;
import com.practice.pairproject.service.UserService;
import com.practice.pairproject.service.impl.PaperServiceImpl;
import com.practice.pairproject.util.AjaxResponse;
import com.practice.pairproject.util.StoragePaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
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
     * 多条件联合模糊查询
     * @param title
     * @param pid
     * @param keyword
     * @param sort
     * @param model
     * @return
     */
    @GetMapping("/search")
    public AjaxResponse searchUser(@RequestParam(name= "title" , defaultValue = "") String title,
                                   @RequestParam(name= "pid" , defaultValue = "") String pid,
                                   @RequestParam(name= "keyword" , defaultValue = "") String keyword,
                                   @RequestParam(name= "sort" , defaultValue = "1") String sort, Model model){
       /* if(sort.isEmpty() || sort.equals("")){
            sort = "1";
        }*/
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title",title);
        paramMap.put("pid",pid);
        paramMap.put("keyword",keyword);
        paramMap.put("sort",sort);

        List<Paper> paperList = paperService.searchPaper(paramMap);
        if( paperList.isEmpty()){
            log.info("【未查询到数据】 " );
            return AjaxResponse.success("【未查询到数据】");
        }
        model.addAttribute("paperList",paperList);

        return AjaxResponse.success(paperList, "【查询成功！】");
    }


    /**
     * 【无分页】
     * 查询所有论文列表
     * @param model
     * @return
     */
    @GetMapping("/show/all")
    public AjaxResponse showAll(Model model){
        List<Paper> paperList = paperService.selectAll();
        if(paperList.isEmpty()){
            log.error("【查询所有论文列表失败！】");
            return AjaxResponse.fail(500, "【查询所有论文列表失败！】");
        }

        //model.addAttribute("list",list2);
        return AjaxResponse.success("【查询所有论文列表成功！】");
    }

    /**
     * 【分页】
     *  查询所有论文列表
     * @param pageNum  第几页
     * @param pageSize
     * @param model
     * @return
     */
    @GetMapping("/show/{page}")
    public AjaxResponse showPage(@PathVariable("page") int pageNum, @RequestParam String pageSize, Model model){

        int page_size = -1;
        if( !pageSize.isEmpty() && !pageSize.equals("") ){
            page_size = Integer.valueOf(pageSize);
        }
        if( page_size <= 0 ){
            page_size = this.pageSize;
        }

        //只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页!!!!
        PageHelper.startPage(pageNum, page_size);
        List<Paper> paperList = paperService.selectAll();

        if(paperList.isEmpty()){
            log.error("【查询所有论文列表失败！】");
            return AjaxResponse.fail(500, "【查询所有论文列表失败！】");
        }
        //该分页的json数据
        PageInfo<Paper> papers = PageInfo.of(paperList);
        //model.addAttribute("list",list2);
        return AjaxResponse.success(papers, "【查询所有论文列表成功！】");
    }




}
