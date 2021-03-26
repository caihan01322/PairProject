package com.pairing.controller;

import com.pairing.bean.PageResponseBody;
import com.pairing.bean.Paper;
import com.pairing.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class PaperListController {

    @Autowired
    PaperService paperService;

    @GetMapping("/paper_collect")
    public String paper_collect() {
        return "paperList/paper_collect";
    }

    @GetMapping("/main")
    public String paper_main() { return "main"; }

    @GetMapping("/get_search_paper")
    @ResponseBody
    public PageResponseBody getSearchPaper(@RequestParam(value = "searchInfo") String searchInfo
            , @RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {
        PageResponseBody pageResponseBody = new PageResponseBody();
        pageResponseBody.setCode(200);
        for(Map.Entry<List<Paper>, Integer> vo : paperService.getPaper(searchInfo, pageNum).entrySet()) {
            pageResponseBody.setList(vo.getKey());
            pageResponseBody.setCount(vo.getValue());
        }
        return pageResponseBody;
    }

    @GetMapping("/get_collect_paper")
    @ResponseBody
    public PageResponseBody getCollectPaper(@RequestParam(value = "searchInfo") String searchInfo
            , @RequestParam(value = "pageNum", defaultValue = "0") int pageNum
            , @RequestParam(value = "userName") String userName) {
        PageResponseBody pageResponseBody = new PageResponseBody();
        pageResponseBody.setCode(200);
        for(Map.Entry<List<Paper>, Integer> vo
                : paperService.getCollectPaper(searchInfo, pageNum, userName).entrySet()) {
            pageResponseBody.setList(vo.getKey());
            pageResponseBody.setCount(vo.getValue());
        }
        return pageResponseBody;
    }

    @GetMapping("/collect")
    @ResponseBody
    public String collectPaper(@RequestParam(value = "userName") String userName
            , @RequestParam(value = "paperId") String paperId
            , @RequestParam(value = "keywords") String keywords
            , @RequestParam(value = "abstrac") String abstrac
            , @RequestParam(value = "publicationTitle") String publicationTitle
            , @RequestParam(value = "persistentLink") String persistentLink ) {
        return paperService.insertPaperToCollection(userName, paperId, keywords, abstrac
                , publicationTitle, persistentLink);
    }

    @GetMapping("/delete")
    @ResponseBody
    public String deletePaper(@RequestParam(value = "userName") String userName
            , @RequestParam(value = "paperId") String paperId
            , @RequestParam(value = "keywords") String keywords
            , @RequestParam(value = "abstrac") String abstrac
            , @RequestParam(value = "publicationTitle") String publicationTitle
            , @RequestParam(value = "persistentLink") String persistentLink ) {
        return paperService.deletePaperFromCollection(userName, paperId);
    }

    @GetMapping("/update")
    @ResponseBody
    public String updatePaper(@RequestParam(value = "userName") String userName
            , @RequestParam(value = "paperId") String paperId
            , @RequestParam(value = "keywords") String keywords
            , @RequestParam(value = "abstrac") String abstrac
            , @RequestParam(value = "publicationTitle") String publicationTitle
            , @RequestParam(value = "persistentLink") String persistentLink ) {
        return paperService.updatePaperToCollection(userName, paperId, keywords, abstrac
                , publicationTitle, persistentLink);
    }
}
