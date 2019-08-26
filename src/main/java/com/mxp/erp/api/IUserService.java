package com.mxp.erp.api;

import java.util.ArrayList;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.constants.ScheduleEnum;
import com.mxp.erp.entity.UserEntity;

public interface IUserService extends IBaseService<UserEntity> {

	UserEntity getByName(String name);

	void update(UserEntity entity);

	void register(UserEntity entity);
	
	void updatePassword(UserEntity entity);
	
	void updateErrorPasswordTimes(UserEntity entity);
	
	ArrayList<UserEntity> getAllUser(ScheduleEnum type);

	void deleteUser(UserEntity userEntity);
}
