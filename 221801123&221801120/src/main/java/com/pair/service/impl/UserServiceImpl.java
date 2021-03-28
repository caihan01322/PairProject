package com.pair.service.impl;


import com.pair.dao.UserDao;
import com.pair.model.role.User;
import com.pair.service.UserService;
import com.pair.util.DataUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public User login(String name, String password) {
        String sql = "select * from user where name = ? and password = ?";
        List<User> users = userDao.queryBySQL(sql, new Object[] {name, password});
        return DataUtil.isValid(users) ? users.get(0) : null;
    }
}
