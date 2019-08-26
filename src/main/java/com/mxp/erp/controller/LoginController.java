package com.mxp.erp.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mxp.erp.annotation.UserLoginToken;
import com.mxp.erp.api.ITokenService;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.constants.LoginStatus;
import com.mxp.erp.dto.UserParam;
import com.mxp.erp.entity.UserEntity;
import com.mxp.erp.util.RestResponse;
import com.mxp.erp.util.RestResponseCode;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ITokenService tokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RestResponse<JSONObject> login(@RequestBody UserParam param, HttpServletResponse response) {
		RestResponse<JSONObject> restResponse = new RestResponse<>();
		String userName = param.getUserName();
		UserEntity user = userService.getByName(userName);
		if (user == null) {
			restResponse.setCode(RestResponseCode.NOTFOUND);
			restResponse.setSuccess(false);
			restResponse.setMessage("用户名不存在");
			return restResponse;
		}
		if (!user.getPassword().equals(param.getPassword())) {
			restResponse.setCode(RestResponseCode.ERROR);
			restResponse.setSuccess(false);
			restResponse.setMessage("密码错误");
			return restResponse;
		}

		user.setLoginStatus(LoginStatus.LOGIN.getName());
		user.setLastModifyTime(new Date());
		userService.update(user);
		String token = tokenService.getToken(user);
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		response.addCookie(cookie);
		return restResponse;
	}

	@UserLoginToken
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RestResponse<JSONObject> changePassword(@RequestBody UserParam param, HttpServletResponse response) {
		RestResponse<JSONObject> restResponse = new RestResponse<>();
		String userName = param.getUserName();
		UserEntity user = userService.getByName(userName);
		String newPassword = param.getNewPassword();
		if (newPassword == null) {
			restResponse.setCode(RestResponseCode.NOTFOUND);
			restResponse.setSuccess(false);
			restResponse.setMessage("新密码不能为空");
			return restResponse;
		}
		if (user == null) {
			restResponse.setCode(RestResponseCode.NOTFOUND);
			restResponse.setSuccess(false);
			restResponse.setMessage("用户名不存在");
			return restResponse;
		}
		if (!user.getPassword().equals(param.getPassword())) {
			restResponse.setCode(RestResponseCode.ERROR);
			restResponse.setSuccess(false);
			restResponse.setMessage("密码错误");
			return restResponse;
		}
		user.setPassword(param.getNewPassword());
		user.setLastModifyTime(new Date());
		userService.update(user);
		String token = tokenService.getToken(user);
		Cookie cookie = new Cookie("token", token);
		cookie.setPath("/");
		response.addCookie(cookie);
		return restResponse;
	}

	@UserLoginToken
	@RequestMapping("/logout")
	public RestResponse<JSONObject> logout(@RequestBody UserParam param) {
		RestResponse<JSONObject> restResponse = new RestResponse<>();
		String userName = param.getUserName();
		UserEntity user = userService.getByName(userName);
		if (user == null) {
			restResponse.setCode(RestResponseCode.NOTFOUND);
			restResponse.setSuccess(false);
			restResponse.setMessage("用户名不存在");
			return restResponse;
		}
		user.setLastModifyTime(new Date());
		user.setLoginStatus(LoginStatus.LOGOUT.getName());
		userService.update(user);
		return restResponse;
	}

}
