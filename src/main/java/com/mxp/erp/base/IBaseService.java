package com.mxp.erp.base;

import java.util.List;

public interface IBaseService<T extends BaseEntity> {

	T getById(String id);

	void insert(T entity);

	void update(T entity);

	void delete(String id);

	List<T> getAllObject();
}
