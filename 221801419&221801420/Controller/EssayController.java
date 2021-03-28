package Controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    
    //论文查询（对作者，摘要和论文题目进行模糊查询）
    /*
     * searchString：查询的字符串
     */
    @RequestMapping("/singleSearch")
    @ResponseBody
    public List<Essay> essaySingleSearch(String searchString){
        EssayDaoImpl e = new EssayDaoImpl();
        return e.singleSearch(searchString);
    }
    
    //论文关键词获取
    /*
     * keywordsType:查询谁的关键词（用户名，cvpr,iccv,eccv)
     * year:查询年份(当输入年会时，year为"all"则查询所有年份的top10关键词)
     */
    @RequestMapping("/keywords")
    @ResponseBody
    public List<Keywords> essayKeywordsGet(String keywordsType,String year){
        return essayService.keywordsGet(keywordsType, year);
    }
    
  //论文列表的标签查询
    /*
     * searchStr：搜索的内容
     * searchLabel：搜索的标签（keywords,title,articlenum)
     */
    @RequestMapping("/labelSearch")
    @ResponseBody
    public List<Essay> labelSearch(String searchStr,String searchLabel,String username){
        return essayService.labelSearch(searchStr, searchLabel, username);
    }
    
    //论文搜索文件上传
    /*
     * file ：上传的excel表格
     */
    @RequestMapping(value="/fileSearch",method=RequestMethod.POST)
    @ResponseBody
    public List<String> fileSearch(MultipartFile file){
        ArrayList<String> titles = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        XSSFWorkbook workbook = null;
        InputStream in = null;
        try {
            in = file.getInputStream();
            workbook = new XSSFWorkbook(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (int rowindex = 0;rowindex <= sheet.getLastRowNum();rowindex++) {
            XSSFRow row = sheet.getRow(rowindex);
            if (row == null) {
                continue;
            }
            XSSFCell titleCell = row.getCell(0);
            titles.add(titleCell.getStringCellValue());
        }

        return titles;
    }
}
