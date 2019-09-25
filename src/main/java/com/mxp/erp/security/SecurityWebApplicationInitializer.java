package com.mxp.erp.security;

import com.mxp.erp.config.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
  public SecurityWebApplicationInitializer() {
    super(SecurityConfig.class);
  }

}
