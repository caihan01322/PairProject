package com.pair.session;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 监听session的创建、销毁以及设置、移除属性，防止重复登录
 */
public final class SessionContainer {
	
	private SessionContainer() {}
	
	/**
	 * 维持已登录的用户
	 * key -> 用户id
	 * value -> 用户的session
	 */
	public static final ConcurrentMap<String, HttpSession> loginUsers = new ConcurrentHashMap<>();
	
}
