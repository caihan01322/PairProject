package com.practice.pairproject.controller;

//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.plugins.Page;
import com.practice.pairproject.pojo.Keyword;
import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.service.KeywordService;
import com.practice.pairproject.service.PaperService;

import com.practice.pairproject.util.AjaxResponse;
import com.practice.pairproject.util.MyPage;
import com.practice.pairproject.util.StoragePaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/paper")
public class PaperController {

    //默认每页paper个数
    private static int pageSize = 10;

    @Autowired
    private PaperService paperService;

    @Autowired
    private KeywordService keywordService;

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
     *
     * @param id
     * @return
     */
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public Object deletePaper(@PathVariable("id") Integer id) {

        int result = paperService.deleteByPrimaryKey(id);
        Map<String, Object> re = new HashMap<String, Object>();
        if (result < 1) {
            log.error("【删除失败！】: " + id);
            //return AjaxResponse.fail(500, "【删除失败！】: " + id);
            re.put("code", 500);
        }

        //return AjaxResponse.success("【删除成功！】");
        re.put("code", 200);
        return re;
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
     *
     * @param request
     * @return
     */
    @ResponseBody
    @DeleteMapping("/deletes")
    public AjaxResponse deletePapers(HttpServletRequest request) {
        String[] pids = request.getParameterValues("pids");
        //@RequestBody String[] pids,
        log.info("【pids】：" + pids);

        List<String> ps = Arrays.asList(pids);
        List<Integer> pidList = ps.stream().map(Integer::parseInt).collect(Collectors.toList());
        int result = paperService.deleteByPrimaryKeyList(pidList);
        if (result != pidList.size()) {
            log.error("【删除失败！】: " + result);
            return AjaxResponse.fail(500, "【删除失败！】: " + result);
        }
        return AjaxResponse.success("【删除成功！】");
    }


    /**
     * 【分页】
     * 多条件联合模糊查询
     *
     * @param title
     * @param pid
     * @param keyword
     * @param sort
     * @param model
     * @return
     */
    @GetMapping("/search/{pageNum}")
    public String searchPapers(
            @PathVariable(name = "pageNum") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "pid", defaultValue = "") String pid,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @RequestParam(name = "sort", defaultValue = "1") String sort, Model model) {
        if (pageSize <= 0) {
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

        if (paperList.isEmpty()) {
            log.info("【未查询到数据】 ");
            //return AjaxResponse.success("【未查询到数据】");
        }
        //return AjaxResponse.success(page, "【查询成功！】");

        model.addAttribute("paperList", paperList);
        model.addAttribute("page", page);
        log.info("【查询到数据--title】： " + title);
        return "paperList";
    }

    /**
     * 首页查询
     * @param pageNum
     * @param pageSize
     * @param title
     * @param sort
     * @param model
     * @return
     */
    //@ResponseBody
  /*  @GetMapping("/search/list/{pageNum}")
    public String searchPaper(
            @PathVariable(name = "pageNum") int pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "title", defaultValue = "") String title,
            @RequestParam(name = "sort", defaultValue = "1") String sort, Model model) {
        if (pageSize <= 0) {
            pageSize = this.pageSize;
        }

        log.info("【title】：" + title);

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("title", title);
        paramMap.put("sort", sort);

        //第一个参数：表示当前第几页;
        //第二个参数：每页显示的条数据;
        MyPage<Paper> page = new MyPage<>(pageNum, pageSize);
        paperService.searchPaper(page, paramMap);
        List<Paper> paperList = page.getRecords();

        if (paperList.isEmpty()) {
            log.info("【未查询到数据】 ");
            //return AjaxResponse.success("【未查询到数据】");
        }
        //return AjaxResponse.success(page, "【查询成功！】");

        model.addAttribute("paperList", paperList);
        model.addAttribute("page", page);
        log.info("【查询到数据--title】： " + title);
        return "paperList";
    }
*/

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
     *
     * @param pageNum  第几页
     * @param pageSize
     * @param model
     * @return
     */
    //@ResponseBody
    @GetMapping("/show/{pageNum}")
    public String showPage(@PathVariable(name = "pageNum") int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                           Model model) {
        if (pageSize <= 0) {
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

        if (paperList.isEmpty()) {
            log.error("【查询所有论文列表失败！】");
            //return AjaxResponse.fail(500, "【查询所有论文列表失败！】");
        }
        /*
        //该分页的json数据
        PageInfo<Paper> papers = PageInfo.of(paperList);
        */
        //return AjaxResponse.success(page, "【查询所有论文列表成功！】");

        //Map<String, Object> result = new HashMap<>();

        model.addAttribute("paperList", paperList);
        model.addAttribute("page", page);
        return "paperList";
        /*result.put("paperList",paperList);
        result.put("page",page);
        return result;*/
    }

    /**
     * 查询某pid的论文的keyword
     *
     * @param pid
     * @return
     */
    @ResponseBody
    @GetMapping("/select/{pid}")
    public Object selectPaper(@PathVariable(name = "pid") int pid, Model model) {

        List<Keyword> keywords = keywordService.selectByPid(pid);
        Paper paper = paperService.selectByPrimaryKey(pid);

        if (keywords.isEmpty()) {
            log.error("【查询论文的keywords失败！】");
            //return AjaxResponse.fail(500, "【查询论文的keywords失败！】");
        }
        log.info("【查询论文的keywords成功！】:");
        for (Keyword k : keywords) {
            System.out.println(k);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("data", keywords);
        result.put("paper2", paper);
        result.put("code", 200);
        return result;
       /*model.addAttribute("keywords",keywords);
       model.addAttribute("paper2", paper);
       model.addAttribute("code",200);
       return "paperList";*/
    }

    /**
     * 点击某个keyword->相关的论文列表
     *
     * @param keyword
     * @return
     */
    @GetMapping(value = {"/list/{pageNum}"})
    public String GetUser(@PathVariable("pageNum") int pageNum,
                          @RequestParam(name = "keyword") String keyword,
                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                          Model model) {
        if (pageSize <= 0) {
            pageSize = this.pageSize;
        }

        //第一个参数：表示当前第几页;
        //第二个参数：每页显示的条数据;
        MyPage<Paper> page = new MyPage<>(pageNum, pageSize);
        paperService.selectPaperByKeyword(page, keyword);
        List<Paper> paperList = page.getRecords();

        if (paperList.isEmpty()) {
            log.error("【查询keyword-->论文列表失败！】");
            //return AjaxResponse.fail(500, "【查询keyword-->论文列表失败！】");
        }
        //return AjaxResponse.success(page, "【查询keyword-->论文列表成功！】");
        log.info("【查询keyword-->论文列表成功！】");
        model.addAttribute("paperList", paperList);
        model.addAttribute("page", page);
        return "paperList2";
    }

}
