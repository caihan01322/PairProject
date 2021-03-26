package com.example.demo11.controller;

import com.example.demo11.dao.ArticalDaoImpl;
import com.example.demo11.model.*;
import com.example.demo11.model.Collection;
import com.example.demo11.service.ArticalService;
import com.example.demo11.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import com.example.demo11.ajAxResponse;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ArticalController
{
    @Autowired
    ArticalService articalService;
    @Autowired
    UserService userService;
    @GetMapping("/search")
    public ajAxResponse search(@RequestParam String keyword,@RequestParam(required = false)Integer pageNum)
    {
        List<Artical> list = articalService.searchArtical(keyword);

        List<FullArtical> full = new ArrayList<FullArtical>();
        int totalNum = (list.size()/5 )+(list.size()==0?0:1);

        if(pageNum == null)
        {
            pageNum = 0;
        }
        if(pageNum > totalNum)
        {
            return ajAxResponse.fail("页数超了！");
        }

            for(int i = pageNum*5;i < pageNum*5+5&&i < list.size();i++)
            {
                FullArtical art = new FullArtical();
                art.artical = list.get(i);
                List<authors> authors = articalService.searchAuthors(art.artical.academicNum);
                List<keywords> keywords = articalService.searchKeywords(art.artical.academicNum);
                List<String> realKeyword  = new ArrayList<String>();
                List<String> realAuthor = new ArrayList<String>();
                for (authors au:
                    authors ) {
                    realAuthor.add(au.author);
                }
                for (keywords key:
                     keywords) {
                    realKeyword.add(key.keyword);
                }
                art.authors = realAuthor;
                art.keywords = realKeyword;
                full.add(art);
            }
        PageModel page = new PageModel();
            page.totalNum = totalNum;
            page.CurrentNum = pageNum;
            page.list = full;
            return ajAxResponse.successfully(page);


    }
    @PostMapping("/Collect")
    public ajAxResponse CollectArticals(@RequestParam String Account,@RequestParam int [] Nums)
    {
        if(userService.getUserByAccount(Account) == 0)
        {
            return  ajAxResponse.fail401("没找到人");
        }
        return ajAxResponse.successfullyUpdate("收藏成功",articalService.Collect(Account,Nums));
    }
    @GetMapping("/getConllections")
    public ajAxResponse getCollections(@RequestParam String Account)
    {
        if(userService.getUserByAccount(Account) == 0)
        {
            return  ajAxResponse.fail401("没找到人");
        }
        Collection col = new Collection();
        for (Artical art: articalService.getCollection(Account)
             ) {
            FullArtical full = new FullArtical();
            full.artical = art;
            List<authors> authors = articalService.searchAuthors(art.academicNum);
            List<keywords> keywords = articalService.searchKeywords(art.academicNum);
            List<String> realKeyword  = new ArrayList<String>();
            List<String> realAuthor = new ArrayList<String>();
            for (authors au:
                    authors ) {
                realAuthor.add(au.author);
            }
            for (keywords key:
                    keywords) {
                realKeyword.add(key.keyword);
            }
            full.keywords = realKeyword;
            full.authors = realAuthor;
            col.list.add(full);
        }
        col.totalPaperNum = col.list.size();
        return ajAxResponse.successfully(col);
    }
    @PostMapping("/deleteCollections")
    public ajAxResponse deleteArtical(@RequestParam String Account,@RequestParam int[] Nums)
    {
        if(userService.getUserByAccount(Account) == 0)
        {
            return  ajAxResponse.fail401("没找到人");
        }
        return ajAxResponse.successfullyUpdate("删除成功！",articalService.deleteCollections(Account,Nums));
    }
    @GetMapping("/Top20")
    public ajAxResponse top20()
    {

        List<hotkey> hotkeys = articalService.top20();
        Collections.sort(hotkeys, new Comparator<hotkey>() {
            @Override
            public int compare(hotkey o1, hotkey o2) {
                if(o1.getNumber() == o2.getNumber())
                    return 0;
                else if(o1.getNumber() > o2.getNumber())
                    return 1;
                else return -1;
                //return o1.getNumber().compareTo(o2.getNumber());
            }
        });
        Map<String,Integer> map = new LinkedHashMap<>();
        for (hotkey key :
                hotkeys) {
            map.put(key.keyword,key.Number);
        }
       return ajAxResponse.successfully(map);
    }
    @GetMapping("/Top10")
    public ajAxResponse top10()
    {

        List<hotkey> hotkeys = articalService.top20().subList(0,10);
        Collections.sort(hotkeys, new Comparator<hotkey>() {
            @Override
            public int compare(hotkey o1, hotkey o2) {
                if(o1.getNumber() == o2.getNumber())
                    return 0;
                else if(o1.getNumber() > o2.getNumber())
                    return 1;
                else return -1;
                //return o1.getNumber().compareTo(o2.getNumber());
            }
        });
        Map<String,Integer> map = new LinkedHashMap<>();
        for (hotkey key :
                hotkeys) {
            map.put(key.keyword,key.Number);
        }
        return ajAxResponse.successfully(map);
    }


}
