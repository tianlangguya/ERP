package com.mxp.erp.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;

public class TokenUtil {

	public static String getTokenUserId() {
		String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
		String userId = JWT.decode(token).getAudience().get(0);
		return userId;
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return requestAttributes == null ? null : requestAttributes.getRequest();
	}
}
