package com.mxp.erp.base;

import java.util.List;

import com.mxp.erp.constants.ERPConstants;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = {ERPConstants.MY_CACHE})
public abstract class BaseService<T extends BaseEntity> implements IBaseService<T> {

	@Autowired
	BaseDao<T> baseDao;

	@Override
  @Cacheable
	public T getById(String id) {
	  System.out.println("走的数据库");
		return baseDao.selectById(id);
	}

	@Override
  @CachePut
	public void insert(T entity) {
		baseDao.insert(entity);
	}

	@Override
  @CachePut
	public void update(T entity) {
		baseDao.updateById(entity);
	}

	@Override
  @CacheEvict
	public void delete(String id) {
		baseDao.deleteById(id);
	}

	@Override
  @Cacheable
	public List<T> getAllObject() {
		return baseDao.selectList(new EntityWrapper<T>());
	}

	@Override
  @Cacheable
	public List<T> getObject(Wrapper<T> wrapper) {
		return baseDao.selectList(wrapper);
	}

	@Override
  @Cacheable
	public List<T> getObjectByPage(Page<T> page, Wrapper<T> wrapper) {
		return baseDao.selectPage(page, wrapper);
	}

	@Override
  @Cacheable
	public int getCountObject() {
		return baseDao.selectCount(new EntityWrapper<T>());
	}

}
