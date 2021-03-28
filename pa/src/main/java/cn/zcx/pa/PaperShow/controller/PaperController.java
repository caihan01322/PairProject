package cn.zcx.pa.PaperShow.controller;

import cn.zcx.pa.PaperShow.dao.PaperDao;
import cn.zcx.pa.PaperShow.pojo.PageHelper;
import cn.zcx.pa.PaperShow.pojo.Paper;
import cn.zcx.pa.PaperShow.service.paper.PaperService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class PaperController
{
  //分页类
  private PageHelper pageHelper;

  //每页展示5篇论文
  private final int paperNum=10;

  @Autowired
  private PaperService paperService;


  @GetMapping("/index")
  public String searchEssay(Model model)
  {
    model.addAttribute("user","zcx");
    return "index";
  }

  @GetMapping("/advancedSearch")
  public String advancedSearch(Model model)
  {
    return "advancedSearch";
  }

  @GetMapping("/essayDetail")
  public String essayDetail(Model model)
  {
    return "essayDetail";
  }





  @GetMapping("/paper/{id}")
  public String getPaperById(@PathVariable("id") String id,Model model)
  {
    model.addAttribute("paper",paperService.getPaperById(id));
    return "essayDetail";
  }

  @GetMapping("/delete/{id}")
  public void deletePaperById(@PathVariable("id") String id)
  {
    paperService.deletePaperById(id);
  }

  @PostMapping("/search")
  public String search(@RequestParam("title") String title,@RequestParam("tmode") int tmode,
                       @RequestParam("keyword") String keyword,@RequestParam("kmode") int kmode,
                       @RequestParam("abst") String abst,@RequestParam("amode") int amode,
                       @RequestParam("beginYear") String beginYear,@RequestParam("endYear") String endYear,
                       @RequestParam("conference") String[] conference, Model model)
  {
    Map<String,Object> params=new HashMap<>();
    if(!title.equals(""))
    {
      params.put(tmode==0?"etitle":"vtitle",title);
    }
    if(!keyword.equals(""))
    {
      params.put(kmode==0?"ekeyword":"vkeyword",keyword);
    }
    if(!abst.equals(""))
    {
      params.put(amode==0?"eabst":"vabst",abst);
    }
    params.put("beginYear",beginYear.equals("")?2000:Integer.parseInt(beginYear));
    params.put("endYear",endYear.equals("")?2020:Integer.parseInt(endYear));

    params.put("conference",1);
    for(String str:conference)
    {
      if(str.equals("CVPR"))
      {
        params.put("CVPR",1);
      }
      if(str.equals("ECCV"))
      {
        params.put("ECCV",1);
      }
      if(str.equals("ICCV"))
      {
        params.put("ICCV",1);
      }
    }

    pageHelper=new PageHelper(paperService.queryPidlistByMap(params),paperNum);
    return "redirect:/page/1";
  }

    @GetMapping("/page/{id}")
    public String page(@PathVariable("id") int id,Model model)
    {
      model.addAttribute("paperList",paperService.getPapersByPidlist(pageHelper.getPageByNum(id)));
      return "papers";
    }

    @GetMapping("/getPapersByKeyword/{keyword}")
    public String getPapersByKeyword(@PathVariable("keyword") String keyword,Model model)
    {
      pageHelper=new PageHelper(paperService.queryPidlistByKeyword(keyword),paperNum);
      return "redirect:/page/1";
    }


}
