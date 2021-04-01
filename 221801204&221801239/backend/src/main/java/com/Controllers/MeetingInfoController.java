package com.Controllers;

import com.Services.IMeetingService;
import com.google.gson.Gson;
import com.pojo.MeetingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingInfoController
{
    @Autowired
    private IMeetingService meetingService;

    @RequestMapping("/getMeetingInfo")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public String getMeetingInfo()
    {
        Gson gson = new Gson();
        List<MeetingInfo> meetingInfoList = meetingService.getMeetingInfo();
        return gson.toJson(meetingInfoList);
    }
}
