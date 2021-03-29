package com.pair.controller;


import com.pair.pojo.Keyword;
import com.pair.service.KeywordService;
import com.pair.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class KeywordController {
    @Autowired
    KeywordService keywordService;
    @Autowired
    PaperService paperService;

    @RequestMapping("/clouds")
    public String getKeyWords(Model model, HttpServletRequest request) {
        String publisher = request.getParameter("meeting");
        keywordService.getTop10Cloud(model, publisher);
        return "cloud";
    }

    @RequestMapping("/getPapers/{keyword}")
    public void getPapers(@PathVariable("keyword") String keyword, Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        paperService.getPapersByKeyword(model, keyword);
        request.setAttribute("selectTerm", "keyword");
        request.setAttribute("selectItem", keyword);
        request.setAttribute("selectMode", "precise");
        request.getRequestDispatcher("/paperSelect").forward(request, response);
    }

    @RequestMapping("/top10words/{meeting}")
    @ResponseBody
    public List<Keyword> getTop10ByMeeting(@PathVariable("meeting") String meeting) {
        List<Keyword> top10Keyword = keywordService.getTop10Keyword(meeting);
        return top10Keyword;
    }

    @RequestMapping("/top10")
    public String getTop10(HttpServletRequest request, Model model) {
        String publisher = request.getParameter("meeting");
        String keyword = request.getParameter("words");
        if (publisher == null) {
            publisher = "CVPR";
        }
        if (keyword == null) {
            keyword = "Cameras";
        }
        keywordService.getTop10(model, publisher, keyword);
        //某顶会几年间的top10词汇
        return "lineChart";
    }

}
