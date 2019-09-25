package com.mxp.erp.service;

import java.util.List;

import com.mxp.erp.constants.ERPConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.constants.ScheduleEnum;
import com.mxp.erp.dao.UserDao;
import com.mxp.erp.entity.UserEntity;

@Service("userService")
@CacheConfig(cacheNames = {ERPConstants.MY_CACHE})
public class UserService extends BaseService<UserEntity> implements IUserService {
	@Autowired
	UserDao userDao;

	@Override
  @Cacheable
	public UserEntity getByName(String name) {
   System.out.println("查询功能，缓存未找到，直接读取数据库，ID为：" + name);
		return userDao.getUserByUserName(name);
	}

	@Override
	@Transactional
	public List<UserEntity> getAllUserByScheduleType(ScheduleEnum type) {
		switch (type) {
		case LOGIN_ERROR_TIMES:
			return userDao.getLoginErrorTimesUser();
		case LEVEL_OFFICE:
			return userDao.getLevelOfficeUser();
		default:
			System.out.println("类型错误!");
			return null;
		}
	}

	@Override
	public UserEntity getByNameAndPassword(String name, String password) {
		return userDao.getByNameAndPassword(name, password);
	}
}
