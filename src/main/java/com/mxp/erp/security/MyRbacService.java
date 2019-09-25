package com.mxp.erp.security;

import com.mxp.erp.api.IPermissionService;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.entity.PermissionEntity;
import com.mxp.erp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("myRbacService")
public class MyRbacService implements IMyRbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    IPermissionService permissionService;
    @Autowired
    IUserService userService;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            userName = String.valueOf(principal);
        }
        UserEntity user = userService.getByName(userName);
        if (user != null) {
            List<PermissionEntity> permissionEntities = permissionService.getByUserId(user.getId());
            for (PermissionEntity entity : permissionEntities) {
                if (antPathMatcher.match(entity.getPermissionName(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }
        }


        return hasPermission;
    }
}
