package com.mxp.erp.service;

import com.mxp.erp.api.IRoleService;
import com.mxp.erp.api.IUserRoleService;
import com.mxp.erp.base.BaseService;
import com.mxp.erp.dao.RoleDao;
import com.mxp.erp.entity.RoleEntity;
import com.mxp.erp.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleService extends BaseService<RoleEntity> implements IRoleService {

    @Autowired
    IUserRoleService userRoleService;

    @Autowired
    RoleDao roleDao;

    @Override
    public List<RoleEntity> getByUserId(String userId) {
        List<UserRoleEntity> userRoleEntities = userRoleService.getByUserId(userId);
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (UserRoleEntity userRole : userRoleEntities) {
            RoleEntity role = roleDao.selectById(userRole.getRoleId());
            if (role != null) {
                roleEntities.add(role);
            }
        }
        return roleEntities;
    }

    @Override
    public RoleEntity getByName(String roleName) {
        Map<String, Object> map = new HashMap<>();
        map.put(RoleEntity.ROLE_NAME, roleName);
        List<RoleEntity> roleEntities = roleDao.selectByMap(map);
        return roleEntities.isEmpty() ? null : roleEntities.get(0);
    }
}
