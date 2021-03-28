package com.pair.dao.base;


import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface BaseDao<T> {

	RowMapper<T> getRowMapper();

	/**
	 * 根据sql查询
	 */
	List<T> queryBySQL(String sql, Object...params);
}
