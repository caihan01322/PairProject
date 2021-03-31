package com.pair.controller.user;


import com.pair.model.Thesis;
import com.pair.model.page.PageBean;
import com.pair.service.ThesisService;
import com.pair.util.DataUtil;
import com.pair.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user/thesis")
public class UserThesisController {

    @Resource
    private ThesisService thesisService;
    @Value("#{properties['thesis.pageSize']}")
    private int pageSize;
    @Value("#{properties['thesis.pageNumber']}")
    private int pageNumber;

    /**
     * 论文列表
     */
    @RequestMapping("/list")
    public String list(String pn, String title, String abstractContent, String keyword, Model model) {
        int pageCode = DataUtil.getPageCode(pn);
        String where = " where 1 = 1 ";
        List<Object> params = new ArrayList<Object>(1);
        if (DataUtil.isValid(title)) {
            where += "and title like '%" + title + "%'";
        }else if (DataUtil.isValid(keyword)) {
            where += "and keyword like '%" + keyword + "%'";
        }else if (DataUtil.isValid(abstractContent)) {
            where += "and abstract_content like '%" + abstractContent + "%'";
        }
        PageBean<Thesis> pageBean = thesisService.pageSearch(pageCode, pageSize, pageNumber, where, null, null);
        model.addAttribute("pageBean", pageBean);
        return "user/thesis_list";
    }

    /**
     * 删除论文
     * @param tid 论文id
     * @param response
     */
    @RequestMapping("/delete/{tid}")
    public void delete(@PathVariable String tid, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        JSONObject json = new JSONObject();
        thesisService.delete(tid);
        json.addElement("result", "1").addElement("message", "删除成功");
        DataUtil.writeJSON(json, response);
        System.out.println(json);
    }

    /**
     * 论文编辑
     */
    @RequestMapping("/edit")
    @ResponseBody
    public void edit(String id, String title, String meet, String year, String keyword, String abstractContent
            , String link, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        if(!DataUtil.isValid(id, title, meet, year, keyword, abstractContent, link)) {
            json.addElement("result", "0").addElement("message", "格式非法");
        }else {
            thesisService.update(id, title, meet, year, keyword, abstractContent, link);
            json.addElement("result", "1").addElement("message", "保存成功");
        }
        DataUtil.writeJSON(json, response);
    }
}
