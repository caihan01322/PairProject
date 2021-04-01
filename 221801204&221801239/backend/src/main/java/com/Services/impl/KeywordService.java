package com.Services.impl;

import com.Services.IKeywordService;
import com.mapper.PaperMapper;
import com.pojo.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService implements IKeywordService
{
    @Autowired
    private PaperMapper mapper;

    @Override
    public List<Keyword> getTopWords()
    {
        return mapper.getTopWords();
    }
}
