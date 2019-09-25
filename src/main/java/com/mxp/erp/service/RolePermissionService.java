package com.mxp.erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String,Object> map=new HashMap<>();
		map.put(RolePermissionEntity.ROLE_ID,roleId);
		return rolePermissionDao.selectByMap(map);
	}

}
