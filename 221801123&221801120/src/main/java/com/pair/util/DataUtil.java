package com.pair.util;


import com.pair.util.json.JSON;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据工具
 */
public abstract class DataUtil {

	/**
	 * 分页使用，判断是否是大于0的数字
	 */
	private static Pattern pattern = Pattern.compile("^[1-9][0-9]*$");

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

	/**
	 * 判断Map有效性
	 */
	public static boolean isValid(Map<?, ?> map) {
		return ! (map == null || map.size() == 0);
	}

	/**
	 * 判断List的有效性
	 */
	public static boolean isValid(List<?> list) {
		return ! (list == null || list.size() == 0);
	}

	/**
	 * 获得页码
	 * @return 返回>=1的数字
	 * 如果给定的字符串不合法，返回1
	 */
	public static int getPageCode(String str) {
		if(isNumber(str)) {
			return Integer.parseInt(str);
		}
		return 1;
	}

	/**
	 * 是不是数字
	 * @param num
	 */
	public static boolean isNumber(String num) {
		if(!isValid(num)) {
			return false;
		}
		Matcher matcher = pattern.matcher(num);
		return matcher.matches();
	}

	/**
	 * 向客户端发送json格式数据
	 * @param useJSONStyle true --返回类型为application/json;charset=utf-8
	 * 否则 text/html
	 * 为了解决json格式跨域无法传送的问题
	 */
	public static void writeJSON(JSON json, HttpServletResponse response, boolean useJSONStyle) {
		if(response == null) {
			throw new NullPointerException("response为空");
		}
		PrintWriter out = null;
		try {
			response.setContentType(useJSONStyle ? "application/json;charset=utf-8" : "text/html;charset=utf-8");
			out = response.getWriter();
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
		}
	}

	public static void writeJSON(cn.hutool.json.JSONObject json, HttpServletResponse response, boolean useJSONStyle) {
		if(response == null) {
			throw new NullPointerException("response为空");
		}
		PrintWriter out = null;
		try {
			response.setContentType(useJSONStyle ? "application/json;charset=utf-8" : "text/html;charset=utf-8");
			out = response.getWriter();
			out.write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.close();
			}
		}
	}

	public static void writeJSON(JSON json, HttpServletResponse response) {
		writeJSON(json, response, true);
	}

	public static void writeJSON(cn.hutool.json.JSONObject json, HttpServletResponse response) {
		writeJSON(json, response, true);
	}
}
