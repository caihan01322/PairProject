package com.topwordanalysis.mapper;

import com.topwordanalysis.databaseOperation.model.Paper;

import java.util.List;

/**
 * PaperMapper
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
public interface PaperMapper {
    int add(Paper paper);
    List<Paper> queryAll(Paper paper);
    List<Paper> selectTitle(String title);
    List<Paper> selectType(String type);
    List<Paper> selectYears(String years);
}
