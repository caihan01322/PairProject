package com.Controllers;

import com.Services.IPaperService;
import com.google.gson.Gson;
import com.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaperController {
    @Autowired
    private IPaperService paperService;

    @RequestMapping("/getPapers")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public String getPapers()
    {
        List<Paper> paperList = paperService.getPapers();
        for(Paper paper:paperList)
        {
            paper.setKeywordsArray();
        }
        Gson gson = new Gson();
        return gson.toJson(paperList);
    }
}
