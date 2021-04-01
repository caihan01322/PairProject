package com.pair.dao.impl;


import com.pair.dao.UserDao;
import com.pair.dao.base.BaseDaoImpl;
import com.pair.model.role.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    private static RowMapper<User> rowMapper;

    static {
        rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        };
    }

    public String getCountSql() {
        return "select count(id) from user";
    }

    public String getSql() {
        return "select * from user";
    }

    public RowMapper<User> getRowMapper() {
        return rowMapper;
    }
}
