package com.mxp.erp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.constants.ScheduleEnum;
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
	
	@Override
	public void register(UserEntity entity) {
		userDao.register(entity);
	}

	@Override
	public void updatePassword(UserEntity entity) {
		userDao.updatePassword(entity);
	}

	@Override
	public void updateErrorPasswordTimes(UserEntity entity) {
		userDao.updateErrorPasswordTimes(entity);
	}

	@Override
	public void deleteUser(UserEntity entity) {
		userDao.deleteUser(entity);
	}

	@Override
	public ArrayList<UserEntity> getAllUser(ScheduleEnum type) {
		switch(type) {
			case LOGIN_ERROR_TIMES:
				return userDao.getLoginErrorTimesUser();
			case LEVEL_OFFICE:
				return userDao.getLevelOfficeUser();
			default:
				System.out.println("类型错误！");
				return null;
		}
	}
}
