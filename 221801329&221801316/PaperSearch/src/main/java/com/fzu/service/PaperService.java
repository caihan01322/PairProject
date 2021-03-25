package com.fzu.service;
import com.fzu.pojo.Paper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaperService {
    /*上传一篇论文*/
    void uploadPaper(Paper paper);
    List<Paper> queryPaperByPage(Integer start,Integer rows);


}
