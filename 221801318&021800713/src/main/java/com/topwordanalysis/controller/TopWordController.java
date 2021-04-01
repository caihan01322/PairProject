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

import java.util.ArrayList;
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


    @GetMapping("/returntopall")
    public List<TopWordResult> returntopall(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = new ArrayList<>();
        List<TopWordResult> topWordListTemp = topWordService.select(new String[]{"year"},new Object[]{"2020"});
        for (int i=0;i<10;i++){
            List<TopWordResult> temp1 = new ArrayList<>();
            if (topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2016"})!=null){
                temp1 = topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2016"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);

            if (topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2017"})!=null){
                temp1 = topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2017"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2018"})!=null){
                temp1 = topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2018"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2019"})!=null){
                temp1 = topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2019"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2020"})!=null){
                temp1 = topWordService.select(new String[]{"word","year"},new Object[]{topWordListTemp.get(i).getWord(),"2020"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
        }
//        for(TopWordResult topWord:topWordList){
//            System.out.println(topWord.getWord());
//            System.out.println(topWord.getCount());
//        }
        return topWordList;
    }


    @GetMapping("/returntopallofeccv")
    public List<TopWordResult> returnTopAllOfEccv(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = new ArrayList<>();
        List<TopWordResult> topWordListTemp = topWordService.select(new String[]{"year","type"},new Object[]{"2020","ECCV"});
        for (int i=0;i<10;i++){
            List<TopWordResult> temp1 = new ArrayList<>();
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2016","ECCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2016","ECCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);

            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2017","ECCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2017","ECCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2018","ECCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2018","ECCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2019","ECCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2019","ECCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2020","ECCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2020","ECCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
        }
        //        for(TopWordResult topWord:topWordList){
        //            System.out.println(topWord.getWord());
        //            System.out.println(topWord.getCount());
        //        }
        return topWordList;
    }

    @GetMapping("/returntopalloficcv")
    public List<TopWordResult> returnTopAllOfIccv(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = new ArrayList<>();
        List<TopWordResult> topWordListTemp = topWordService.select(new String[]{"year","type"},new Object[]{"2020","ICCV"});
        for (int i=0;i<10;i++){
            List<TopWordResult> temp1 = new ArrayList<>();
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2016","ICCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2016","ICCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);

            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2017","ICCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2017","ICCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2018","ICCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2018","ICCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2019","ICCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2019","ICCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2020","ICCV"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2020","ICCV"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
        }
        //        for(TopWordResult topWord:topWordList){
        //            System.out.println(topWord.getWord());
        //            System.out.println(topWord.getCount());
        //        }
        return topWordList;
    }


    @GetMapping("/returntopallofcvpr")
    public List<TopWordResult> returnTopAllOfCvpr(){
        TopWordService topWordService = new TopWordService();
        List<TopWordResult> topWordList = new ArrayList<>();
        List<TopWordResult> topWordListTemp = topWordService.select(new String[]{"year","type"},new Object[]{"2020","CVPR"});
        for (int i=0;i<10;i++){
            List<TopWordResult> temp1 = new ArrayList<>();
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2016","CVPR"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2016","CVPR"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);

            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2017","CVPR"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2017","CVPR"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2018","CVPR"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2018","CVPR"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2019","CVPR"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2019","CVPR"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
            if (topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2020","CVPR"})!=null){
                temp1 = topWordService.select(new String[]{"word","year","type"},new Object[]{topWordListTemp.get(i).getWord(),"2020","CVPR"});
            }else{
                temp1.add(new TopWordResult(topWordListTemp.get(i).getWord(),"0"));
            }
            topWordList.addAll(temp1);
        }
        //        for(TopWordResult topWord:topWordList){
        //            System.out.println(topWord.getWord());
        //            System.out.println(topWord.getCount());
        //        }
        return topWordList;
    }

}
