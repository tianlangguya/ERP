package com.mxp.erp.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

public abstract class BaseService<T extends BaseEntity> implements IBaseService<T> {

	@Autowired
	BaseDao<T> baseDao;

	@Override
	public T getById(String id) {
		return baseDao.selectById(id);
	}

	@Override
	public void insert(T entity) {
		baseDao.insert(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.updateAllColumnById(entity);
	}

	@Override
	public void delete(String id) {
		baseDao.deleteById(id);
	}

	@Override
	public List<T> getAllObject() {
		return baseDao.selectList(new EntityWrapper<T>());

	}

}
