package com.mxp.erp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mxp.erp.base.BaseEntity;

@TableName("sys_permission")
public class PermissionEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField("PERMISSION_NAME")
	private String permissionName;

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

}
