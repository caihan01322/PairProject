package com.pairwork.pairwork.dao;

import com.pairwork.pairwork.entity.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperDao extends JpaRepository<Paper,Long> {
    @Query(value = "SELECT p FROM Paper  p  where (p.summary like %?1% or p.title like %?1%)",nativeQuery = false)
    List<Paper> findNameLike(String toFind);//实现按名字的模糊查询

//    @Query(value = "select  * from paper",nativeQuery = true)
    @Query(value = "SELECT * FROM id_paper, paper WHERE id_paper.cuser_id = ?1 AND id_paper.cpaper_id = paper.paper_id",nativeQuery = true)
    List<Paper> findUserCollecion(Long user_id);
//    Page<Paper> findNameLike(Pageable pageable);
}


