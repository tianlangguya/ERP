package com.mxp.erp.base;

import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

public interface IBaseService<T extends BaseEntity> {

	T getById(String id);

	void insert(T entity);

	void update(T entity);

	void delete(String id);

	List<T> getAllObject();

	List<T> getObject(Wrapper<T> wrapper);
	
	List<T> getObjectByPage(Page<T> page, Wrapper<T> wrapper);

	int getCountObject();

}
