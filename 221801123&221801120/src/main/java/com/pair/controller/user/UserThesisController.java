package com.pair.controller.user;


import com.pair.model.Thesis;
import com.pair.model.page.PageBean;
import com.pair.service.ThesisService;
import com.pair.util.DataUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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
    public String list(String pn, String title, String id, String content, Model model) {
        int pageCode = DataUtil.getPageCode(pn);
        String where = " where 1 = 1 ";
        List<Object> params = new ArrayList<Object>(1);
        if (DataUtil.isValid(id)) {
            where += "and id = ?";
            params.add(id);
        }else if (DataUtil.isValid(title)) {
            where += "and content like '%" + title + "%'";
        }
        PageBean<Thesis> pageBean = thesisService.pageSearch(pageCode, pageSize, pageNumber, where, params, null);
        model.addAttribute("pageBean", pageBean);
        return "user/thesis_list";
    }
}
