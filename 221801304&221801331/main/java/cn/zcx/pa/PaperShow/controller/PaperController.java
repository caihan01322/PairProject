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

  //每页展示10篇论文
  private final int paperNum=10;

  @Autowired
  private PaperService paperService;

  //首页
  @GetMapping("/")
  public String searchEssay()
  {
    return "index";
  }

  //高级搜索页面
  @GetMapping("/advancedSearch")
  public String advancedSearch(Model model)
  {
    return "advancedSearch";
  }


  //根据id获得论文
  @GetMapping("/paper/{id}")
  public String getPaperById(@PathVariable("id") String id,Model model)
  {
    model.addAttribute("paper",paperService.getPaperById(id));
    return "essayDetail";
  }


  //根据id删除论文
  @GetMapping("/delete/{id}")
  public String deletePaperById(@PathVariable("id") String id)
  {
    String p=paperService.getPaperById(id).getPid();
    paperService.deletePaperById(id);
    pageHelper.getPidList().remove(p);
    return "redirect:/currentPage";
  }

  //高级搜索
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

    //普通搜索
    @PostMapping("/easySearch")
    public String easySearch(@RequestParam("title") String title)
    {
      Map<String,Object> params=new HashMap<>();
      params.put("vtitle",title);

      pageHelper=new PageHelper(paperService.queryPidlistByMap(params),paperNum);
      return "redirect:/page/1";
    }

    @PostMapping("/page")
    public String toPage(@RequestParam("id") String id,Model model)
    {
      int num;
      if(id.equals(""))
      {
        num=pageHelper.getCurrentPage();
      }
      else
      {
        num=Integer.parseInt(id);
      }
      if(num<1)
      {
        num=1;
      }
      if(num>pageHelper.getTotalPage())
      {
        num=pageHelper.getTotalPage();
      }
      model.addAttribute("paperList",paperService.getPapersByPidlist(pageHelper.getPageByNum(num)));
      model.addAttribute("currentPage",pageHelper.getCurrentPage());
      model.addAttribute("totalNum",pageHelper.getTotalPage());
      return "papers";
    }

    @GetMapping("/currentPage")
    public String currentPage()
    {
      return "redirect:/page/"+pageHelper.getCurrentPage();
    }

    //根据页号获得内容
    @GetMapping("/page/{id}")
    public String page(@PathVariable("id") int id,Model model)
    {
      if(id<1)
      {
        id=1;
      }
      if(id>pageHelper.getTotalPage())
      {
        id=pageHelper.getTotalPage();
      }
      model.addAttribute("paperList",paperService.getPapersByPidlist(pageHelper.getPageByNum(id)));
      model.addAttribute("currentPage",pageHelper.getCurrentPage());
      model.addAttribute("totalNum",pageHelper.getTotalPage());
      return "papers";
    }

    //获得上一页内容
    @GetMapping("/prePage")
    public String prePage()
    {
        return "redirect:/page/"+(pageHelper.getCurrentPage()-1);
    }

    //获得下一页内容
    @GetMapping("/nextPage")
    public String nextPage()
    {
        return "redirect:/page/"+(pageHelper.getCurrentPage()+1);
    }

    //获得第一页
    @GetMapping("/firstPage")
    public String firstPage()
    {
      return "redirect:/page/1";
    }

    //获得最后一页
    @GetMapping("/lastPage")
    public String lastPage()
    {
      return "redirect:/page/"+(pageHelper.getTotalPage());
    }


    //根据关键词获得相关论文
    @GetMapping("/getPapersByKeyword/{keyword}")
    public String getPapersByKeyword(@PathVariable("keyword") String keyword,Model model)
    {
      pageHelper=new PageHelper(paperService.queryPidlistByKeyword(keyword),paperNum);
      return "redirect:/page/1";
    }


}
