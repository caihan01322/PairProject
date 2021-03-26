package cn.zcx.pa.PaperShow.controller;

import cn.zcx.pa.PaperShow.service.paper.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
public class PaperController
{
  @Autowired
  private PaperService paperService;

  @GetMapping("/paper/{id}")
  public String getPaperById(@PathVariable("id") String id)
  {
    return paperService.getPaperById(id).toString();
  }

  @GetMapping("/delete/{id}")
  public void deletePaperById(@PathVariable("id") String id)
  {
    paperService.deletePaperById(id);
  }

  @PostMapping("/search")
  public int search(@RequestParam("CVPR") int CVPR)
  {
    Map<String,Object> params=new HashMap<>();
    params.put("vconference",1);
    params.put("CVPR",1);
    return paperService.queryPapersByMap(params).size();
  }


}
