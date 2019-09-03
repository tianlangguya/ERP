package com.mxp.erp.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

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
		baseDao.updateById(entity);
	}

	@Override
	public void delete(String id) {
		baseDao.deleteById(id);
	}

	@Override
	public List<T> getAllObject() {
		return baseDao.selectList(new EntityWrapper<T>());
	}

	@Override
	public List<T> getObject(Wrapper<T> wrapper) {
		return baseDao.selectList(wrapper);
	}

	@Override
	public List<T> getObjectByPage(Page<T> page, Wrapper<T> wrapper) {
		return baseDao.selectPage(page, wrapper);
	}

	@Override
	public int getCountObject() {
		return baseDao.selectCount(new EntityWrapper<T>());
	}

}
