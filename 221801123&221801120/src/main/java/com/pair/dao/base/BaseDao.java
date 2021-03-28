package com.pair.dao.base;


import com.pair.model.page.PageBean;
import org.springframework.jdbc.core.RowMapper;

import java.util.HashMap;
import java.util.List;

public interface BaseDao<T> {

	/**
	 * 获取用于统计记录数量的sql语句
	 */
	String getCountSql();

	/**
	 * 获取用于查询此实体类的sql
	 */
	String getSql();

	/**
	 * 获取特定与某一个实体的mapper
	 */
	RowMapper<T> getRowMapper();


	/**
	 * 分页查询
	 * @param pageCode 需要查询的页码
	 * @param pageSize 每页的大小
	 * @param pageNumber 显示的页码数量
	 * @param where where条件语句
	 * @param params 参数列表
	 * @param orderbys 排序条件，比如id desc
	 * @return {@link PageBean}
	 */
	PageBean<T> pageSearch(int pageCode, int pageSize, int pageNumber, String where,
						   List<Object> params, HashMap<String, String> orderbys);
}
