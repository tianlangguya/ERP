package com.mxp.erp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.entity.UserEntity;

@Repository("userDao")
@Mapper
public interface UserDao extends BaseDao<UserEntity> {

	@Override
	UserEntity getById(long id);

	/**
	 * 根据用户名获取用户
	 * 
	 * @param name
	 *            用户名
	 * @return
	 */
	UserEntity getUserByUserName(String name);

	void update(UserEntity user);
}
