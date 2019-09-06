package com.mxp.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxp.erp.api.IPermissionService;
import com.mxp.erp.api.IRolePermissionService;
import com.mxp.erp.api.IRoleService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.dao.PermissionDao;
import com.mxp.erp.entity.PermissionEntity;
import com.mxp.erp.entity.RoleEntity;
import com.mxp.erp.entity.RolePermissionEntity;

@Service("permissionService")
public class PermissionService extends BaseService<PermissionEntity> implements IPermissionService {

	@Autowired
	PermissionDao permissionDao;

	@Autowired
	IRolePermissionService rolePermissionService;

	@Autowired
	IRoleService roleService;

	@Override
	public List<PermissionEntity> getByRoleId(String roleId) {
		List<RolePermissionEntity> rolePermissionEntities = rolePermissionService.getByRoleId(roleId);
		List<String> permissionIds = rolePermissionEntities.stream().map(RolePermissionEntity::getPermissionId)
				.collect(Collectors.toList());
		return permissionDao.selectBatchIds(permissionIds);
	}

	@Override
	public List<PermissionEntity> getByUserId(String userId) {
		List<RoleEntity> roleEntities = roleService.getByUserId(userId);
		List<PermissionEntity> permissionEntities = new ArrayList<>();
		for (RoleEntity role : roleEntities) {
			List<PermissionEntity> entities = getByRoleId(role.getId());
			if (!entities.isEmpty()) {
				permissionEntities.addAll(entities);
			}

		}
		return null;
	}

}
