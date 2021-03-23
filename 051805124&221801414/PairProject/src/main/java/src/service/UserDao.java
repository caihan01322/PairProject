package src.service;

import src.Basedao;
import src.User;

public class UserDao {
	public static int insert(User user) {
		String sql = "insert into user values(\""+user.getUserName()+"\",\""+user.getPassword()+"\")";
		return Basedao.insertUser(sql);
	}
	public static boolean isUser(User user) {
		String sql = "select * from user where userName = \""+user.getUserName()+"\" and password = \""+user.getPassword()+"\"";
		return Basedao.isUser(sql,user.getUserName(),user.getPassword());
	}
}
