package com.pairwork.pairwork.dao;

import com.pairwork.pairwork.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository//标识这是一个接口interface
public interface UserDao extends JpaRepository<User,Long> {
    @Query(value = "select * from user where account = ?1",nativeQuery = true)
    boolean findAccount(String account);
}
