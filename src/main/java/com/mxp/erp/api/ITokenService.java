package com.mxp.erp.api;

import com.mxp.erp.dto.User;

public interface ITokenService {

	String getToken(User user);
}
