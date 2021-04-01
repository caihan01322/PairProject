package com.pair.dao.base;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.pair.model.page.PageBean;
import com.pair.util.DataUtil;
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

	//查询sql语句
	protected String sql = getSql();
	//数量查询语句
	protected String countSql = getCountSql();

	public List<T> find(T entity) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 此方法使用update实现，因为从源码得知，execute()一般用于DDL，而不是DML
	 */
	public void executeSql(String sql) {
		jdbcTemplate.update(sql);
	}

	public void executeSql(String sql, Object[] params) {
		jdbcTemplate.update(sql, params);
	}

	public List<T> queryBySQL(String sql, Object... params) {
		return jdbcTemplate.query(sql, params, rowMapper);
	}

	public List<T> queryBySQL(String sql) {
		return jdbcTemplate.query(sql, rowMapper);
	}

	public Object queryForObject(String sql, Class<?> clazz) {
		return jdbcTemplate.queryForObject(sql, clazz);
	}

	@Override
	public int[] batchUpdate(String... sqls) {
		return jdbcTemplate.batchUpdate(sqls);
	}

	public PageBean<T> pageSearch(int pageCode, int pageSize, int pageNumber,
								  String where, List<Object> params, HashMap<String, String> orderbys) {

		String whereSql = (DataUtil.isValid(where)) ? where : "";
		StringBuilder sqlBuilder = new StringBuilder(sql).append(" ").append(whereSql);
		StringBuilder countSqlBuilder = new StringBuilder(countSql).append(" ").append(whereSql);
		PageBean<T> pageBean = null;
		if (DataUtil.isValid(orderbys)) {
			//设置排序
			sqlBuilder.append(" order by ");
			for (String key : orderbys.keySet()) {
				sqlBuilder.append(key).append(" ").append(orderbys.get(key)).append(",");
			}
			sqlBuilder.deleteCharAt(sql.length() - 1);
		}
		//设置分页
		int begin = (pageCode - 1) * pageSize;
		sqlBuilder.append(" limit ").append(begin).append(", ").append(pageSize);
		//设置参数
		if (DataUtil.isValid(params)) {
			Object[] paramsArray = params.toArray();
			pageBean = new PageBean<T>(jdbcTemplate.query(sqlBuilder.toString(), paramsArray, rowMapper), pageSize, pageCode,
					((BigInteger) jdbcTemplate.queryForObject(countSqlBuilder.toString(), paramsArray, BigInteger.class)).intValue(), pageNumber);
		} else {
			pageBean = new PageBean<T>(jdbcTemplate.query(sqlBuilder.toString(), rowMapper), pageSize, pageCode,
					((BigInteger) jdbcTemplate.queryForObject(countSqlBuilder.toString(), BigInteger.class)).intValue(), pageNumber);
		}
		return pageBean;
	}
}
