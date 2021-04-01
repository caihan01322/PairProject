package com.Services.impl;

import com.Services.IMeetingService;
import com.mapper.PaperMapper;
import com.pojo.MeetingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService implements IMeetingService
{
    @Autowired
    private PaperMapper mapper;

    @Override
    public List<MeetingInfo> getMeetingInfo()
    {
        return mapper.getMeetingInfo();
    }
}
