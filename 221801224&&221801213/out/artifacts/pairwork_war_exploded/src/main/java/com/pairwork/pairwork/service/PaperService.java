package com.pairwork.pairwork.service;


import com.pairwork.pairwork.dao.PaperDao;
import com.pairwork.pairwork.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaperService {
    @Resource
    private PaperDao paperDao;

    public Page<Paper> findPage(Integer pageNum, Integer pageSize, String toFind){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");//倒序
        Pageable pageable = PageRequest.of(pageNum - 1,pageSize,sort);
        return paperDao.findNameLike(toFind,pageable);
//        return paperDao.findNameLike(pageable);
    }
}
