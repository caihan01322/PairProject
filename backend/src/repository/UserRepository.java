package com.eepractice.webcrawller.repository;

import com.eepractice.webcrawller.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByUsername(String username);

    User findUserById(Integer id);
}
