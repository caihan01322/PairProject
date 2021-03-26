package com.practice.pairproject.controller;

import com.practice.pairproject.pojo.Paper;
import com.practice.pairproject.pojo.User;
import com.practice.pairproject.service.PaperService;
import com.practice.pairproject.service.UserService;
import com.practice.pairproject.service.impl.PaperServiceImpl;
import com.practice.pairproject.util.StoragePaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;

@Controller
@RequestMapping({"/paper"})
public class PaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private StoragePaper storagePaper;

    @ResponseBody
    @RequestMapping(value = {"/load/ECCV"}, method = {RequestMethod.POST})
    public String insertPaper1() {

        //ECCV 1  ECCV+ 2
        int size1 = storagePaper.loadECCVpapers(1);
        int size2 = storagePaper.loadECCVpapers(2);
        return String.valueOf(size1+size2);
    }

    @ResponseBody
    @RequestMapping(value = {"/load/CVPR"}, method = {RequestMethod.POST})
    public String insertPaper2() {

        //CVPR 0
        int size = storagePaper.loadCVPR_ICCVpapers(0);
        return String.valueOf(size);
    }

    @ResponseBody
    @RequestMapping(value = {"/load/ICCV"}, method = {RequestMethod.POST})
    public String insertPaper3() {

        //ICCV 3
        int size = storagePaper.loadCVPR_ICCVpapers(3);
        return String.valueOf(size);
    }
}
