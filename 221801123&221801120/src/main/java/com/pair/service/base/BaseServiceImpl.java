package com.pair.service.base;


import com.pair.dao.base.BaseDao;
import com.pair.model.page.PageBean;

import java.util.HashMap;
import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

	/**
	 * 设置具体的BaseDao
	 */
	protected abstract void setBaseDao(BaseDao<T> baseDao);

	public PageBean<T> pageSearch(int pageCode, int pageSize, int pageNumber,
								  String where, List<Object> params, HashMap<String, String> orderbys) {
		return baseDao.pageSearch(pageCode, pageSize, pageNumber, where, params, orderbys);
	}
}
