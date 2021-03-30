package com.pairwork.pairwork.service;


import com.pairwork.pairwork.dao.PaperDao;
import com.pairwork.pairwork.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperService {
    @Resource
    private PaperDao paperDao;

//    public Page<Paper> findPage(Integer pageNum, Integer pageSize, String toFind){
//        Sort sort = Sort.by(Sort.Direction.DESC,"paper_id");//倒序
//        Pageable pageable = PageRequest.of(pageNum - 1,pageSize,sort);
//        return paperDao.findNameLike(toFind,pageable);
//    }

    public List<Paper> findPage(String toFind){
        return paperDao.findNameLike(toFind);
    }

//    public Page<Paper> findCollection(Integer pageNum,Integer pageSize,Long user_id){//将用户收餐的所有论文以Page格式返回
//        Sort sort = Sort.by(Sort.Direction.DESC,"paper_id");//倒序
//        Pageable pageable = PageRequest.of(pageNum - 1,pageSize,sort);
//        return paperDao.findUserCollecion(user_id,pageable);
//    }

    public List<Paper> findCollecion(Long user_id){
        return paperDao.findUserCollecion(user_id);
    }
}
