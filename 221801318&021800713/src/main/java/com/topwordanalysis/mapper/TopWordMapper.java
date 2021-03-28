package com.topwordanalysis.mapper;

import com.topwordanalysis.databaseOperation.model.Paper;
import com.topwordanalysis.databaseOperation.model.TopWord;

import java.util.List;

/**
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
public interface TopWordMapper {
    void add(TopWord topWord);
    List<TopWord> select(String[] propertyName,String[] value);
}
