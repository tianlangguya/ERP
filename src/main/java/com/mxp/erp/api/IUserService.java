package com.mxp.erp.api;

import java.util.List;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.constants.ScheduleEnum;
import com.mxp.erp.entity.UserEntity;

public interface IUserService extends IBaseService<UserEntity> {
	UserEntity getByName(String name);

	List<UserEntity> getAllUserByScheduleType(ScheduleEnum type);
}
