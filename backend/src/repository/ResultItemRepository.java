package com.eepractice.webcrawller.repository;

import com.eepractice.webcrawller.bean.Keyword;
import com.eepractice.webcrawller.bean.ResultItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ResultItemRepository extends CrudRepository<ResultItem, String> {

    List<ResultItem> findResultItemsByTitleLike(String queryTitle);

    ResultItem findResultItemById(String id);

    List<ResultItem> findResultItemsByPublisherContains(String publisher);

    List<ResultItem> findResultItemsByPublisherContainsAndConferenceDateContains(String publisher,String date);

    @Query(value = "select keywords from result_item",nativeQuery = true)
    List<Object[]> getAll();

    @Query(value="select * from result_item",nativeQuery = true)
    List<ResultItem> findAll();
}
