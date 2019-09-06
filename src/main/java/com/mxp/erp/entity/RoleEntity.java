package com.mxp.erp.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mxp.erp.base.BaseEntity;

@TableName("sys_role")
public class RoleEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField("ROLE_Name")
	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
