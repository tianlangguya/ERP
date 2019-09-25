package com.mxp.erp.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IMyRbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
