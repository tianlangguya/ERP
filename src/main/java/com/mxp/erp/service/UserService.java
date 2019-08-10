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
	public UserEntity getUserByUserName(String name) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(name);
	}

}
