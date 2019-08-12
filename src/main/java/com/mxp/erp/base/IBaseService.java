package com.mxp.erp.base;

public interface IBaseService<T extends BaseEntity> {
	T getById(long id);
}
