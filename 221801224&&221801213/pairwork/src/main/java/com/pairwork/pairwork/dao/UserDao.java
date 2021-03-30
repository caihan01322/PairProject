package com.pairwork.pairwork.dao;

import com.pairwork.pairwork.entity.Paper;
import com.pairwork.pairwork.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//标识这是一个接口interface
public interface UserDao extends JpaRepository<User,Long> {
    @Query(value = "select * from user where account = ?1",nativeQuery = true)
    List<User> findAccount(String account);

//    @Query(value = "SELECT * FROM paper WHERE paper_id = ANY(SELECT cpaper_id FROM id_paper WHERE id_paper.cuser_id = ?1)",nativeQuery = true)
//        @Query(value = "select  * from paper",nativeQuery = true)
//    @Query(value = "SELECT paper_id,summary,link,keyWords,title FROM id_paper, paper WHERE id_paper.cuser_id = 1 AND id_paper.cpaper_id = paper.paper_id",nativeQuery = true)
//    List<Paper> findUserCollecion(Long user_id);
//    Page<Paper> findUserCollecion(Long user_id,Pageable pageable);

}
