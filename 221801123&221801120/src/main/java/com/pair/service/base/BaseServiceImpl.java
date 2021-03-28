package com.pair.service.base;


import com.pair.dao.base.BaseDao;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

	/**
	 * 设置具体的BaseDao
	 */
	protected abstract void setBaseDao(BaseDao<T> baseDao);
}
