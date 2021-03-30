package com.topwordanalysis.controller;

import com.topwordanalysis.databaseOperation.model.*;
import com.topwordanalysis.service.PaperService;
import com.topwordanalysis.util.AnalysisPaperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PaperController
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
@RestController
@RequestMapping("/paper")
public class PaperController {
    List<RealPaper> paperList = new ArrayList<>();
    @Autowired
    private PaperService paperService = new PaperService();
    @GetMapping("/read")
    public void readPaper(){
        List<Paper> paperListCVPR = AnalysisPaperUtil.readPaperOfCVPR();
        List<Paper> paperListECCV = AnalysisPaperUtil.readPaperOfECCV();
        List<Paper> paperListICCV = AnalysisPaperUtil.readPaperOfICCV();
        for(Paper paper:paperListCVPR){
            paperService.add(paper);
        }
        for(Paper paper:paperListECCV){
            paperService.add(paper);
        }
        for(Paper paper:paperListICCV){
            paperService.add(paper);
        }
    }
    @GetMapping("/returnall")
    public List<Paper> returnAll(){
        List<Paper> paperList = paperService.queryAll();
        return paperList;
    }
    @PostMapping("/search")
    public List<Paper> searchByTitle(@RequestBody Search search){
        if (search.getString1().equals("关键字")){
            if(search.getString2().equals("模糊查询")){
                List<Paper> paperList = paperService.searchByKeyWord("%"+search.getString3()+"%");
                System.out.println(paperList.size());
                return paperList;
            }else if(search.getString2().equals("精准查询")){
                List<Paper> paperList = paperService.searchByKeyWord(search.getString3());
                return paperList;
            }
        }else if(search.getString1().equals("标题")){
            if(search.getString2().equals("模糊查询")){
                List<Paper> paperList = paperService.searchByTitle("%"+search.getString3()+"%");
                return paperList;
            }else if(search.getString2().equals("精准查询")){
                List<Paper> paperList = paperService.searchByTitle(search.getString3());
                return paperList;
            }
        }
        return null;
    }
    @PostMapping("/addpapertolist")
    public void addPaperToList(@RequestBody List<RealPaper> paper){
        for (RealPaper paper1:paper){
            System.out.println(paper1.getPaperAbstract());
            System.out.println(paper1.getLink());
            System.out.println(paper1.getTitle());
            System.out.println(paper1.getYear());
            paperList.add(paper1);
        }
    }
    @PostMapping("/deletepaper")
    public void deletePaper(@RequestBody DeletePaper paper){
        System.out.println(paper.getLink());
        for (int i=0;i<paperList.size();i++){
            System.out.println(paperList.get(i).getLink());
            System.out.println(paperList.get(i).getLink().equals(paper.getLink()));
            if (paperList.get(i).getLink().equals(paper.getLink())){
                paperList.remove(i);
            }
        }
    }
    @GetMapping("/returnpaperlist")
    public List<RealPaper> returnPaperList(){
        return paperList;
    }
    @GetMapping("/returnsum")
    public int returnSum(){
        List<Paper> paperList = paperService.queryAll();
        return paperList.size();
    }
}
