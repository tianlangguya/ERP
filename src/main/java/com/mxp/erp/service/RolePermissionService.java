package com.mxp.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxp.erp.api.IRolePermissionService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.dao.RolePermissionDao;
import com.mxp.erp.entity.RolePermissionEntity;

@Service("rolePermissionService")
public class RolePermissionService extends BaseService<RolePermissionEntity> implements IRolePermissionService {

	@Autowired
	RolePermissionDao rolePermissionDao;

	@Override
	public List<RolePermissionEntity> getByRoleId(String roleId) {

		return rolePermissionDao.getByRoleId(roleId);
	}

}
