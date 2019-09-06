package com.mxp.erp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxp.erp.api.IRoleService;
import com.mxp.erp.api.IUserRoleService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.dao.RoleDao;
import com.mxp.erp.entity.RoleEntity;
import com.mxp.erp.entity.UserRoleEntity;

@Service("roleService")
public class RoleService extends BaseService<RoleEntity> implements IRoleService {

	@Autowired
	IUserRoleService userRoleService;

	@Autowired
	RoleDao roleDao;

	@Override
	public List<RoleEntity> getByUserId(String userId) {
		List<UserRoleEntity> userRoleEntities = userRoleService.getByUserId(userId);
		List<RoleEntity> roleEntities = new ArrayList<>();
		for (UserRoleEntity userRole : userRoleEntities) {
			RoleEntity role = roleDao.selectById(userRole.getRoleId());
			if (role != null) {
				roleEntities.add(role);
			}
		}
		return roleEntities;
	}
}
