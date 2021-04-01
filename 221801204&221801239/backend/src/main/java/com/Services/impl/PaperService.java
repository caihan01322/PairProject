package com.Services.impl;

import com.Services.IPaperService;
import com.mapper.PaperMapper;
import com.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperService implements IPaperService
{
    @Autowired
    private PaperMapper mapper;

    @Override
    public List<Paper> getPapers()
    {
        return mapper.getPapers();
    }
}
