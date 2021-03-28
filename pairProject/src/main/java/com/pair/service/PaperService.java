package com.pair.service;

import com.pair.dao.PaperMapper;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Service
public class PaperService {
    @Autowired
    PaperMapper paperMapper;
    public void getPapersByKeyword(Model model,String keyword){
        List<Paper> papers = paperMapper.getPapersByKeyword(keyword);
        model.addAttribute("papers", papers);
    }
    public int getPaperNum(Map map) {
        return paperMapper.getPaperNum(map);
    }
    public List<Paper> selectPaperListWithoutKeywords(){
        return paperMapper.selectPaperListWithoutKeywords();
    }
    public Paper getPaperById(String pid){
        return paperMapper.getPaperById(pid);
    }
    public List<String> getPaperIdByFuzzyMode(Map map){
        return paperMapper.getPaperIdByFuzzyMode(map);
    }
    public List<String> getPaperIdByPreciseMode(Map map){
        return paperMapper.getPaperIdByPreciseMode(map);
    }
    public void deletePaperByPid(String pid){
        paperMapper.deletePaperByPid(pid);
    }

}
