package com.jdzy.papersearch.dao;

import com.jdzy.papersearch.pojo.Author;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorDao {
    List<Author> findAuthorByPaperId(Integer paperId, Integer limit);
}
