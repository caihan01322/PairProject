package com.topwordanalysis.service.User;

import com.topwordanalysis.databaseOperation.dao.BaseCRUD;
import com.topwordanalysis.databaseOperation.dao.UserDaoImpl;
import com.topwordanalysis.databaseOperation.encryption.MD5Util;
import com.topwordanalysis.databaseOperation.model.User;

import java.util.List;

/**
 * @author 221801318_黄贸之
 * @Date 2021/3/24
 */
public class UserAdmin {
    /**
     * 学生登录
     *
     * @param user
     */
    public String login(User user){
        String message = "";
        if (user.getMail() == null || user.getMail().equals("")) {
            message = "邮箱不能为空";
            return message;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            message = "密码不能为空";
            return message;
        }
        BaseCRUD<User> userBaseCRUD = new UserDaoImpl();
        List<User> readData = userBaseCRUD.readByKey(new String[]{"mail"}, new Object[]{user.getMail()});

        if (readData == null || readData.size() <= 0) {
            message = "邮箱不存在";
        } else {
            if (!readData.get(0).getPassword().equals(MD5Util.md5Password(user.getPassword()))) {
                message = "密码错误";
            } else {
                message = "ok";
            }
        }
        return message;
    }

    public String sign(User user){
        String message = "";
        if (user.getMail() == null || user.getMail().equals("")) {
            message = "邮箱不能为空";
            return message;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            message = "密码不能为空";
            return message;
        }
        BaseCRUD<User> userBaseCRUD = new UserDaoImpl();
        user.setPassword(MD5Util.md5Password(user.getPassword()));
        userBaseCRUD.create(user);
        return message;
    }
}
