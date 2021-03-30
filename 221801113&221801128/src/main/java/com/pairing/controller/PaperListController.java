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

    /**
     * 返回收藏夹页面
     * @return
     */
    @GetMapping("/paper_collect")
    public String paper_collect() {
        return "paperList/paper_collect";
    }

    /**
     * 返回论文查询页面
     * @return
     */
    @GetMapping("/main")
    public String paper_main() { return "main"; }

    /**
     * 进行论文搜索
     * @param searchInfo 搜索信息
     * @param pageNum    页码
     * @return
     */
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

    /**
     * 进行收藏夹搜索
     * @param searchInfo 搜索信息
     * @param pageNum    页码
     * @param userName   用户名
     * @return
     */
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

    /**
     * 收藏功能
     * @param userName 执行收藏的用户
     * @param paperId  收藏的论文id
     * @param keywords 收藏的论文的关键词
     * @param abstrac  收藏的论文的摘要
     * @param publicationTitle 收藏的论文标题
     * @param persistentLink   收藏的论文链接
     * @return
     */
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

    /**
     * 删除功能
     * @param userName 执行删除的用户
     * @param paperId  删除的论文id
     * @param keywords 删除的论文的关键词
     * @param abstrac  删除的论文的摘要
     * @param publicationTitle 删除的论文标题
     * @param persistentLink   删除的论文链接
     * @return
     */
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

    /**
     * 更新功能
     * @param userName 执行更新的用户
     * @param paperId  更新的论文id
     * @param keywords 更新的论文的关键词
     * @param abstrac  更新的论文的摘要
     * @param publicationTitle 更新的论文标题
     * @param persistentLink   更新的论文链接
     * @return
     */
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
