package com.practice.pairproject.controller;

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
        if(result != 0){
            log.info("【删除成功！】");
            return AjaxResponse.success("【删除成功！】");
        }
        return AjaxResponse.fail(500, "【删除失败！】");
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
        int result = paperService.deleteByPrimaryKeyList(pids);
        if(result != pids.size()){
            log.info("【删除失败！】: " + result);
            return AjaxResponse.fail(500, "【删除失败！】");
        }
        return AjaxResponse.success("【删除成功！】");
    }


    @GetMapping("/show")
    public String home(Model model){



        System.out.println("home后端查询到了！");

        //model.addAttribute("list",list2);
        return "home";
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
    public AjaxResponse searchUser(@RequestParam("title") String title,
                                   @RequestParam("pid") String pid,
                                   @RequestParam("keyword") String keyword,
                                   @RequestParam("sort") String sort, Model model){
       /* List<User> list = _userService.selectUserLikeUserName(name);
        System.out.println("admin-searchUser后端查询到了！" + name);
        for (User user : list) {
            System.out.println(user);
        }
        model.addAttribute("list",list);
        return "admin/adminCenter";*/

       if(sort.isEmpty() || sort.equals("")){
           sort = "1";
       }

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title",title);
        paramMap.put("pid",pid);
        paramMap.put("keyword",keyword);
        paramMap.put("sort",sort);

        List<Paper> paperList = paperService.searchPaper(paramMap);
        model.addAttribute("paperList",paperList);

        return AjaxResponse.success("【删除成功！】");
    }



}
