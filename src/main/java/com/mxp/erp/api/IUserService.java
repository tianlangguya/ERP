package com.mxp.erp.api;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.entity.UserEntity;

public interface IUserService extends IBaseService<UserEntity> {

	UserEntity getUserByUserName(String name);
	
}
