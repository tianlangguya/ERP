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
	
	UserEntity getUserByUserName(String name);
}
