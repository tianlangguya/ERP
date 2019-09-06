package com.mxp.erp.api;

import java.util.List;

import com.mxp.erp.base.IBaseService;
import com.mxp.erp.entity.PermissionEntity;

public interface IPermissionService extends IBaseService<PermissionEntity> {

	public List<PermissionEntity> getByRoleId(String roleId);

	public List<PermissionEntity> getByUserId(String userId);

}
