package Controller;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Dao.EssayDaoImpl;
import Entity.Essay;
import Entity.Keywords;
import Entity.User;
import Service.EssayService;


@Controller
@RequestMapping(value="/essay")
public class EssayController {
   
    private EssayService essayService;//提供论文操作的服务
    
    //论文的添加收藏
    /*
     * userName:用户名字
     * essayMeeting:所属顶会（cvpr,eccv,iccv）
     * essayName：文章名称
     */
    @RequestMapping(value="/collect")
    @ResponseBody
    public String essayCollect(String userName,String essayMeeting,String essayName) { 
        return essayService.essayCollect(userName, essayMeeting, essayName);      
    }
    

    //论文的删除
    /*
     * userName:用户名字
     * essayMeeting:所属顶会（cvpr,eccv,iccv）
     * essayName：文章名称
     */
    @RequestMapping(value="/delete")
    @ResponseBody
    public String essayDelete(String userName,String essayMeeting,String essayName) { 
        return essayService.essayDelete(userName, essayMeeting, essayName);     
    }
    
    //论文详细信息获取
    /*
     * essayName：论文名称
     */
    @RequestMapping("/getInfo")
    @ResponseBody
    public Essay essayInfoGet(String essayName){
        EssayDaoImpl e = new EssayDaoImpl();
        System.out.println(e.getInfo(essayName).getAuthors());
        return e.getInfo(essayName);
    }
    
    //论文信息修改
    /*
     * essay：前端传递的essayjson数据
     * essayMeeting:论文所属顶会
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public String essayEdit(@RequestBody Essay essay,String essayMeeting) {
        EssayDaoImpl e = new EssayDaoImpl();
        if (essayMeeting.equals("iccv")) {
            e.editiccv(essay);
        }
        if (essayMeeting.equals("cvpr")) {
            e.editcvpr(essay);
        }
        if (essayMeeting.equals("eccv")) {
            e.editeccv(essay);
        }
        return "success";
    }

    
}
