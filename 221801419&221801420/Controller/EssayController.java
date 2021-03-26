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
    


    
}
