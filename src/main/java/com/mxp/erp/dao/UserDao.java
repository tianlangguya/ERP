package com.mxp.erp.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.constants.ScheduleEnum;
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
	
	void register(UserEntity user);
	
	void updatePassword(UserEntity user);
	
	void updateErrorPasswordTimes(UserEntity user);

	ArrayList<UserEntity> getLoginErrorTimesUser();
	
	ArrayList<UserEntity> getLevelOfficeUser();

	void deleteUser(UserEntity entity);
}
