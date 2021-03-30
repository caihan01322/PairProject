package com.pairwork.pairwork.dao;

import com.pairwork.pairwork.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperDao extends JpaRepository<Paper,Long> {
    @Query(value = "select * from paper where (title like '%?1%' or summary like '%?1%' )",nativeQuery = true)
    Page<Paper> findNameLike(String toFind,Pageable pageable);//实现按名字的模糊查询

//    Page<Paper> findNameLike(Pageable pageable);
}


