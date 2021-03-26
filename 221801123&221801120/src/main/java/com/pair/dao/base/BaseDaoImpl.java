package com.pair.dao.base;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 * 注入JdbcTemplate对象
 * 提供方法的空实现
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Resource
	protected JdbcTemplate jdbcTemplate;
	//rowMapper，由子类提供
	protected RowMapper<T> rowMapper = getRowMapper();

	public List<T> queryBySQL(String sql, Object... params) {
		return jdbcTemplate.query(sql, params, rowMapper);
	}


}
