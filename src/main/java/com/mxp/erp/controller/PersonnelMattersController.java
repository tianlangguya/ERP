package com.mxp.erp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.dto.UserParam;
import com.mxp.erp.entity.UserEntity;
import com.mxp.erp.util.RestResponse;
import com.mxp.erp.util.RestResponseCode;

/**
 * 人事管理
 * @author maxp
 *
 */
@Controller
public class PersonnelMattersController {
	
	@Autowired
	private IUserService userService;

	
	/**
	 * 离职
	 * @param param
	 * @param hsr
	 * @return
	 */
	@RequestMapping(value = "/leveloffice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public RestResponse<JSONObject> levelOffice(@RequestBody UserParam param, HttpServletResponse hsr){
		RestResponse<JSONObject> res = new RestResponse<>();
		String userName = param.getUserName();
		UserEntity user = userService.getByName(userName);
		if(user == null) {
			res.setCodeAndIsSuccess(RestResponseCode.NOTFOUND, false);
			res.setMessage(RestResponseCode.NOTFOUND_USER);
			return res;
		}
		
		return res;
	}
}
