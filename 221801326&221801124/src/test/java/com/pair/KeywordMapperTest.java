package com.pair;

import com.pair.dao.KeywordMapper;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Keyword;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class KeywordMapperTest
{

    @Autowired
    KeywordMapper keywordMapper;

    @Test
    public void selectKeywordTest()
    {
        String param1="";
        String param2="";
        keywordMapper.selectKeyword(param1,param2);

    }


}
