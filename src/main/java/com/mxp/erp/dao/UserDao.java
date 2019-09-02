package com.mxp.erp.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.entity.UserEntity;

@Repository("userDao")
public interface UserDao extends BaseDao<UserEntity> {

	/**
	 * 根据用户名获取用户
	 * 
	 * @param name
	 *            用户名
	 * @return
	 */
	UserEntity getUserByUserName(String name);

	ArrayList<UserEntity> getLoginErrorTimesUser();

	ArrayList<UserEntity> getLevelOfficeUser();

}
