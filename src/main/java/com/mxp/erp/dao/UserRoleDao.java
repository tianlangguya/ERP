package com.mxp.erp.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mxp.erp.base.BaseDao;
import com.mxp.erp.entity.UserRoleEntity;

@Repository("userRoleDao")
public interface UserRoleDao extends BaseDao<UserRoleEntity> {

	List<UserRoleEntity> getByUserId(String userId);
}
