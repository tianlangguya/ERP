package com.mxp.erp.controller;

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
@RequestMapping(value = "/pm")
public class PersonnelManagementController {

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

	@RequestMapping(value = "/getByName", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
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
				return "登陆失败！";
			}
		}
	}

	/**
	 * 新增人员
	 * 
	 * @param userParam
	 * @return
	 */
	@RequestMapping(value = "/newlyAddedWorker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String newlyAddedWorker(@RequestBody UserParam userParam) {
		UserEntity user = userService.getByName(userParam.getUserName());
		if (user == null) {
			user = new UserEntity();
			user.setUserName(userParam.getUserName());
			user.setPassword(userParam.getPassword());
			user.setTelephone(userParam.getTelephone());
			userService.insert(user);
			return "注册成功！";
		} else {
			return "用户已存在！";
		}
	}

}
