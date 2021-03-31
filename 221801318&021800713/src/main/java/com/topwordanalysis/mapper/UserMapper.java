package com.topwordanalysis.mapper;

import com.topwordanalysis.databaseOperation.model.User;


/**
 * UserMapper
 *
 * @author 221801318_黄贸之
 * @Date 2021/3/26
 */
public interface UserMapper {
    String login(User user);
    String sign(User user);
}
