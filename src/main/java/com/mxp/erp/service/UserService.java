package com.mxp.erp.service;

import org.springframework.stereotype.Service;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.entity.UserEntity;

@Service("userService")
public class UserService extends BaseService<UserEntity> implements IUserService {

}
