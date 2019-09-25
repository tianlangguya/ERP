package com.mxp.erp.controller;

import com.alibaba.fastjson.JSONObject;
import com.mxp.erp.annotation.UserLoginToken;
import com.mxp.erp.api.ITokenService;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.constants.LoginStatus;
import com.mxp.erp.dto.UserParam;
import com.mxp.erp.entity.UserEntity;
import com.mxp.erp.util.RestResponse;
import com.mxp.erp.util.RestResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login")
    public String login() {
        return "loginPage";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        return "test";
    }
    @RequestMapping(value = "/index")
    public String index() {
        return "indexPage";
    }

    @RequestMapping(value = "/loginError")
    public String loginError() {
        return "loginErrorPage";
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
