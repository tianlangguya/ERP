package com.mxp.erp.base;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T extends BaseEntity> implements IBaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;

	@Override
	public T getById(long id) {
		return baseDao.getById(id);
	}
}
