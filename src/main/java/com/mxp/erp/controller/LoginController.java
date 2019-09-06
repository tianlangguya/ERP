package com.mxp.erp.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mxp.erp.annotation.UserLoginToken;
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

	// @Autowired
	// private ITokenService tokenService;

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody UserParam param, HttpServletResponse response, HttpServletRequest request) {

		// RestResponse<JSONObject> restResponse = new RestResponse<>();
		String userName = param.getUserName();
		String password = param.getPassword();
		// if (user == null) {
		// restResponse.setCodeAndIsSuccess(RestResponseCode.NOTFOUND, false);
		// restResponse.setMessage(RestResponseCode.NOTFOUND_USER);
		// return restResponse;
		// }
		// if (!user.getPassword().equals(param.getPassword())) {
		// restResponse.setCodeAndIsSuccess(RestResponseCode.ERROR, false);
		// restResponse.setMessage(RestResponseCode.PASSWORD_ERROR);
		// return restResponse;
		// }

		try {
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
		} catch (DisabledAccountException e) {
			request.setAttribute("msg", "账户已被禁用");
			return "redirect:/login/login";
		} catch (AuthenticationException e) {
			request.setAttribute("msg", "用户名或密码错误");
			return "redirect:/login/login";
		}
		return "redirect:/index";

		// user.setLoginStatus(LoginStatus.LOGIN.getName());
		// user.setLastModifyTime(new Date());
		// userService.update(user);
		// String token = tokenService.getToken(user);
		// Cookie cookie = new Cookie("token", token);
		// cookie.setPath("/");
		// response.addCookie(cookie);
		// return restResponse;
	}

	@RequestMapping(value = "/login1")
	@ResponseBody
	public String loginPage() {
		// try {
		//// response.sendRedirect("login");
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return "login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public String index(HttpServletRequest request) {
		return "index123";
	}

	@UserLoginToken
	@RequestMapping("/logout")
	public RestResponse<JSONObject> logout(@RequestBody UserParam param) {
		RestResponse<JSONObject> restResponse = new RestResponse<>();
		String userName = param.getUserName();
		UserEntity user = userService.getByName(userName);
		if (user == null) {
			restResponse.setCodeAndIsSuccess(RestResponseCode.NOTFOUND, false);
			restResponse.setMessage(RestResponseCode.NOTFOUND_USER);
			return restResponse;
		}
		user.setLastModifyTime(new Date());
		user.setLoginStatus(LoginStatus.LOGOUT.getName());
		userService.update(user);
		return restResponse;
	}

}
