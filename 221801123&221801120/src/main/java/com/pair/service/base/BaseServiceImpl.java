package com.pair.service.base;


import com.pair.dao.base.BaseDao;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

}
