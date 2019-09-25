package com.mxp.erp.security;

import com.mxp.erp.api.IRoleService;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.entity.RoleEntity;
import com.mxp.erp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.getByName(username);

        if (user != null) {//假设返回的用户信息如下;
            List<RoleEntity> roleEntities = roleService.getByUserId(user.getId());
            if (roleEntities.isEmpty()) {
                return null;
            }
            UserInfo userInfo = new UserInfo(user.getUserName(), user.getPassword(), roleEntities.get(0).getRoleName(), true, true, true, true);
            return userInfo;
        }
        return null;
    }
}
