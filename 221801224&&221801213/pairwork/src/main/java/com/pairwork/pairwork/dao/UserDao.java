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


}
