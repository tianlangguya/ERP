package com.mxp.erp.api;

import com.mxp.erp.entity.UserEntity;

public interface ITokenService {

	String getToken(UserEntity user);
}
