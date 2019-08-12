package com.mxp.erp.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mxp.erp.api.ITokenService;
import com.mxp.erp.dto.User;

@Service("tokenService")
public class TokenService implements ITokenService{

	@Override
	public String getToken(User user) {
		Date start = new Date();
		long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;// 一小时有效时间
		Date end = new Date(currentTime);
		String token = "";

		token = JWT.create().withAudience(String.valueOf(user.getId())).withIssuedAt(start).withExpiresAt(end)
				.sign(Algorithm.HMAC256(user.getPassword()));
		return token;
	}

}
