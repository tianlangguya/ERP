package com.mxp.erp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.dto.UserParam;
import com.mxp.erp.entity.UserEntity;
import com.mxp.erp.util.RestResponse;
import com.mxp.erp.util.RestResponseCode;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	public IUserService userService;

	@RequestMapping(value = "/getPage", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RestResponse<List<String>> getPage() {
		RestResponse<List<String>> restResponse = new RestResponse<>();
		Page<UserEntity> page = new Page<>();
		page.setCurrent(1);
		page.setSize(4);
		List<UserEntity> user = userService.getObjectByPage(page, new EntityWrapper<>());
		List<String> userString = user.stream().map(UserEntity::toString).collect(Collectors.toList());
		restResponse.setCodeAndIsSuccess(RestResponseCode.OK, true);
		restResponse.setData(userString);
		return restResponse;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RestResponse<List<String>> getAll() {
		RestResponse<List<String>> restResponse = new RestResponse<>();
		List<UserEntity> user = userService.getAllObject();
		List<String> userString = user.stream().map(u -> u.toString()).collect(Collectors.toList());
		restResponse.setCodeAndIsSuccess(RestResponseCode.OK, true);
		restResponse.setData(userString);
		return restResponse;
	}

	@RequestMapping(value = "/getById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getById(@RequestBody String id) {
		UserEntity user = userService.getById(id);
		if (user != null) {
			return user.toString();
		}
		return "该用户不存在";
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
