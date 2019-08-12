package com.mxp.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mxp.erp.annotation.UserLoginToken;
import com.mxp.erp.util.TokenUtil;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping("/")
	public String root() {
		return "index";
	}

	// @RequestMapping("/login")
	// public Object login(User user, HttpServletResponse response) {
	// JSONObject jsonObject = new JSONObject();
	// User userForBase = new User();
	// userForBase.setId("1");
	// userForBase.setPassword("123");
	// userForBase.setUsername("mrc");
	//
	// if (!userForBase.getPassword().equals(user.getPassword())) {
	// jsonObject.put("message", "登录失败,密码错误");
	// return jsonObject;
	// } else {
	// String token = tokenService.getToken(userForBase);
	// jsonObject.put("token", token);
	//
	// Cookie cookie = new Cookie("token", token);
	// cookie.setPath("/");
	// response.addCookie(cookie);
	//
	// return jsonObject;
	//
	// }
	// }

	@UserLoginToken
	@RequestMapping("/login")
	public String getMessage() {
		// 取出Headers里面的token中带的用户id 进行操作
		System.out.println(TokenUtil.getTokenUserId());

		return "你已通过验证";
	}
}
