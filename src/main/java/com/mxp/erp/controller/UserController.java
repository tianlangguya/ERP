package com.mxp.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxp.erp.api.IUserService;
import com.mxp.erp.entity.UserEntity;

@Controller
@RequestMapping(value = "/rest/user")
public class UserController {

	@Autowired
	public IUserService userService;

	@RequestMapping(value = "/getById", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void getById(@RequestBody long id) {
		UserEntity user = userService.getById(id);
		if (user != null) {
			System.out.println(id);
		}
	}

	@RequestMapping(value = "/index")
	public String goIndex() {
		return "html/index";
	}
}
