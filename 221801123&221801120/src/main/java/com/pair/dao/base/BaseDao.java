package com.pair.dao.base;


import org.springframework.jdbc.core.RowMapper;

public interface BaseDao<T> {

	RowMapper<T> getRowMapper();

}
