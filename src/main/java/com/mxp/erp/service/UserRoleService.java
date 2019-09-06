package com.mxp.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mxp.erp.api.IUserRoleService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.dao.UserRoleDao;
import com.mxp.erp.entity.UserRoleEntity;

@Service("userRoleService")
public class UserRoleService extends BaseService<UserRoleEntity> implements IUserRoleService {

	@Autowired
	UserRoleDao userRoleDao;

	@Override
	public List<UserRoleEntity> getByUserId(String userId) {
		
		return userRoleDao.getByUserId(userId);
	}

}
