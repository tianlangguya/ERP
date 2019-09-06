package com.mxp.erp.api;

import java.util.List;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.entity.UserRoleEntity;

public interface IUserRoleService extends IBaseService<UserRoleEntity> {
	public List<UserRoleEntity> getByUserId(String userId);
}
