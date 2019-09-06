package com.mxp.erp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.entity.RolePermissionEntity;

@Repository("rolePermissionDao")
public interface RolePermissionDao extends BaseDao<RolePermissionEntity> {

	List<RolePermissionEntity> getByRoleId(String roleId);
}
