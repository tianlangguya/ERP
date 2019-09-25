package com.mxp.erp.dao;

import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.entity.RoleEntity;

@Repository("roleDao")
public interface RoleDao extends BaseDao<RoleEntity> {
}
