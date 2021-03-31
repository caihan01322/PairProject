package Dao;

import Entity.LoginReturn;

public interface LoginDao {

    LoginReturn userLogin(String userName,String password) ;
  //0:用户名不存在，1：密码错误，2：登录成功
}
