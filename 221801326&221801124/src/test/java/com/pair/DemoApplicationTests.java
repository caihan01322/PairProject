package com.pair;


import com.pair.dao.PaperMapper;
import com.pair.pojo.Paper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class DemoApplicationTests {

    @Autowired
    PaperMapper paperMapper;
    @Test
    void contextLoads() {
        Map<String,Object> params=new HashMap<>();
        params.put("title","reconstruction");
        List<String> paperId = paperMapper.getPaperIdByFuzzyMode(params);
        for(int i=0;i<5;i++){
            Paper paperById = paperMapper.getPaperById(paperId.get(i));
            System.out.println(paperById);
        }
    }
}
