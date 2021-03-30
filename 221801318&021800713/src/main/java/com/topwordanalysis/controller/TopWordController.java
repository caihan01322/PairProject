package com.topwordanalysis.controller;

import com.topwordanalysis.databaseOperation.model.Paper;
import com.topwordanalysis.databaseOperation.model.TopWord;
import com.topwordanalysis.databaseOperation.model.TopWordResult;
import com.topwordanalysis.service.TopWordService;
import com.topwordanalysis.util.AnalysisPaperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
@RestController
@RequestMapping("/topword")
public class TopWordController {
    @Autowired
    private TopWordService topWordService;

    @GetMapping("/read")
    public void readWord(){
        List<TopWord> topWordsCVPR = AnalysisPaperUtil.readTopWordOfCVPR();
        List<TopWord> topWordsECCV = AnalysisPaperUtil.readTopWordOfECCV();
        List<TopWord> topWordsICCV = AnalysisPaperUtil.readTopWordOfICCV();
        for(TopWord topWord:topWordsCVPR){
            topWordService.add(topWord);
        }
        for(TopWord topWord:topWordsECCV){
            topWordService.add(topWord);
        }
        for(TopWord topWord:topWordsICCV){
            topWordService.add(topWord);
        }
    }

    @GetMapping("/alltop")
    public List<TopWordResult> returnAllTop(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = topWordService.returnAllTop();
        return topWordList;
    }

    @GetMapping("/topwordofeccv")
    public List<TopWordResult> returnTopWordOfECCV(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = topWordService.select(new String[]{"type"},new Object[]{"ECCV"});
        return topWordList;
    }

    @GetMapping("/topwordofcvpr")
    public List<TopWordResult> returnTopWordOfCVPR(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = topWordService.select(new String[]{"type"},new Object[]{"CVPR"});
        return topWordList;
    }

    @GetMapping("/topwordoficcv")
    public List<TopWordResult> returnTopWordOfICCV(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = topWordService.select(new String[]{"type"},new Object[]{"ICCV"});
        return topWordList;
    }
}
