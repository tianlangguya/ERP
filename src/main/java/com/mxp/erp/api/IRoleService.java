package com.mxp.erp.api;

import java.util.List;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.entity.RoleEntity;

public interface IRoleService extends IBaseService<RoleEntity> {

	public List<RoleEntity> getByUserId(String userId);
}
