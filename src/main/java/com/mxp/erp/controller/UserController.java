package com.mxp.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.dao.UserParam;
import com.mxp.erp.entity.UserEntity;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public IUserService userService;

	@RequestMapping(value = "/getById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getById(@RequestBody long id) {
		UserEntity user = userService.getById(id);
		if (user != null) {
			return user.toString();
		}
		return "该用户不存在";
	}

	@RequestMapping(value = "/getByName", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getByName(@RequestBody UserParam userParam) {
		UserEntity user = userService.getUserByUserName(userParam.getUserName());
		if (user == null) {
			return "用户不存在！";
		} else {
			if (user.getPassword().equals(userParam.getPassword())) {
				// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
				// String enPassword = encoder.encode(password);加密
				return "登陆成功！";
			} else {
				return "登陆失败！";
			}
		}
	}



}
