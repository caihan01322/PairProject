package com.pair.controller.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HotWordController {

    @Resource
    protected JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/user/chart")
    public void chart(HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String sql = "select keyword, count(keyword) num from thesis_keyword group by keyword order by count(keyword) desc limit 10";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String s = gson.toJson(map);
        System.out.println(s);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(s);
    }
}
