package com.pair.util;


import javax.servlet.http.HttpSession;

/**
 * 数据工具
 */
public abstract class DataUtil {

	/**
	 * 判断字符串是否为空
	 */
	public static boolean isValid(String str) {
		return str != null && !str.trim().equals("");
	}

	public static boolean isValid(String...strs) {
		for(String str : strs) {
			if(!isValid(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验验证码
	 */
	public static boolean checkVerify(String verify, HttpSession session) {
		String rand = (String) session.getAttribute("rand");
		return rand.equals(verify);
	}
}
