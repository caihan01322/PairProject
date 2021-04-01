package com.Controllers;

import com.Services.IKeywordService;
import com.google.gson.Gson;
import com.pojo.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KeywordController
{
    @Autowired
    private IKeywordService keywordService;

    @RequestMapping("/gettopwords")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public String getTopWords()
    {
        Gson gson = new Gson();
        List<Keyword> keywordList = keywordService.getTopWords();
        return  gson.toJson(keywordList);

    }
}
