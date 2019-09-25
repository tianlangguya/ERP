package com.mxp.erp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mxp.erp.base.BaseEntity;

@TableName("sys_role_permission")
public class RolePermissionEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ROLE_ID="ROLE_ID";

	public static final String PERMISSION_ID="PERMISSION_ID";

	@TableField("ROLE_ID")
	private String roleId;

	@TableField("PERMISSION_ID")
	private String permissionId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	
	

}
