package Dao;

import Entity.LoginReturn;

public interface RegisterDao {

    LoginReturn userRegister(String username,String pwd);
  //0:用户已存在，1：注册成功
}
