package com.mxp.erp.util;

/**
 *
 */
public final class RestResponseCode {

	public static final int OK = 200;
	public static final int ERROR = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int FORBIDDEN = 403;
	public static final int NOTFOUND = 404;
	
	public static final String NOTFOUND_USER = "用户名不存在";
	public static final String PASSWORD_ERROR = "密码错误";
	public static final String NEW_PASSWORD_NULL = "新密码不能为空";
	
}
