package com.mxp.erp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.dto.UserParam;
import com.mxp.erp.entity.UserEntity;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public IUserService userService;

	@RequestMapping(value = "/getById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getById(@RequestBody String id) {
		UserEntity user = userService.getById(id);
		if (user != null) {
			return user.toString();
		}
		return "该用户不存在";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getByName(@RequestBody UserParam userParam) {
		UserEntity user = userService.getByName(userParam.getUserName());
		if (user == null) {
			return "用户不存在！";
		} else {
			if (user.getPassword().equals(userParam.getPassword())) {
				// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
				// String enPassword = encoder.encode(password);加密
				return "登陆成功！";
			} else {
				if (user.getLoginErrorTimes() > 5) {
					return "错误次数超过限制，请明天再试！";
				} else {
					user.setLoginErrorTimes(user.getLoginErrorTimes());
					userService.update(user);
				}
				return "密码错误！";
			}
		}
	}

	/**
	 * 注冊
	 * 
	 * @param userParam
	 * @return
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String registerUser(@RequestBody UserParam userParam) {
		UserEntity user = userService.getByName(userParam.getUserName());
		if (user == null) {
			user = new UserEntity();
			user.setUserName(userParam.getUserName());
			user.setPassword(userParam.getPassword());
			user.setTelephone(userParam.getTelephone());
			Date date = new Date();
			user.setCreationTime(date);
			user.setLastModifyTime(date);
			userService.insert(user);
			return "注册成功！";
		} else {
			return "用户已存在！";
		}
	}

	/**
	 * 修改密码，更新密码
	 * 
	 * @param userParam
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String updatePassword(@RequestBody UserParam userParam) {
		UserEntity user = userService.getByName(userParam.getUserName());
		if (user == null) {
			return "用户不存在！";
		} else {
			if (user.getPassword().equals(userParam.getOldPassword())) {
				if (userParam.getNewPassword() != "") {
					user.setPassword(userParam.getNewPassword());
					userService.update(user);
					return "更换密码成功";
				} else {
					return "新密码是空的！";
				}
			} else {
				return "原密码不正确";
			}
		}
	}

	/**
	 * 忘记密码，输入用户名和邮箱
	 * 
	 * @param userParam
	 * @return
	 */
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String forgetPassword(@RequestBody UserParam userParam) {
		UserEntity user = userService.getByName(userParam.getUserName());
		if (user == null) {
			return "用户不存在！";
		} else {
			if (user.getEmail().equals(userParam.getEmail())) {
				return "邮箱也是对的！";
			} else {
				return "邮箱错误！";
			}
		}
	}
}
