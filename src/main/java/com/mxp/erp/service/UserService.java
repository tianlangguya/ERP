package com.mxp.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.dao.UserDao;
import com.mxp.erp.entity.UserEntity;

@Service("userService")
public class UserService extends BaseService<UserEntity> implements IUserService {

	@Autowired
	UserDao userDao;

	@Override
	public UserEntity getByName(String name) {
		return userDao.getUserByUserName(name);
	}

	@Override
	public void update(UserEntity entity) {
		userDao.update(entity);
	}
}
