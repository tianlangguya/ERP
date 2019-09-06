package com.mxp.erp.api;

import java.util.List;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.entity.RolePermissionEntity;

public interface IRolePermissionService extends IBaseService<RolePermissionEntity> {

	public List<RolePermissionEntity> getByRoleId(String roleId);

}
