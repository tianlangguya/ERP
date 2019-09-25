package com.mxp.erp.security;

import com.mxp.erp.api.IPermissionService;
import com.mxp.erp.api.IRoleService;
import com.mxp.erp.entity.PermissionEntity;
import com.mxp.erp.entity.RoleEntity;
import com.mxp.erp.util.BeanManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserInfo implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String role;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public UserInfo(String username, String password, String role, boolean accountNonExpired, boolean accountNonLocked,
                    boolean credentialsNonExpired, boolean enabled) {
        // TODO Auto-generated constructor stub
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        IPermissionService service = BeanManager.getService(IPermissionService.class);
        IRoleService roleService = BeanManager.getService(IRoleService.class);
        if (role != null) {
            RoleEntity roleEntity = roleService.getByName(role);
            if (roleEntity != null) {
                List<PermissionEntity> permissionEntityList = service.getByRoleId(roleEntity.getId());
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (PermissionEntity entity : permissionEntityList) {
                    GrantedAuthority authority=new SimpleGrantedAuthority(entity.getPermissionName());
                    authorities.add(authority);
                }
                return authorities;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
