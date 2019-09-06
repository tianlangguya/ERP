package com.mxp.erp.dao;

import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.entity.PermissionEntity;

@Repository("permissionDao")
public interface PermissionDao extends BaseDao<PermissionEntity> {

}
