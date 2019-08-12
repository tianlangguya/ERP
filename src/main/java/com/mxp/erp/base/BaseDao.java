package com.mxp.erp.base;

public interface BaseDao<T extends BaseEntity> {

	T getById(long id);
}
