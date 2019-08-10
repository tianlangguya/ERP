package com.mxp.erp.entity;

import com.mxp.erp.base.BaseEntity;

public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public String user_name;

	public String password;

	public String telephone;

	public String email;

	public long login_count;

	public String login_status;

	public long age;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getLogin_count() {
		return login_count;
	}

	public void setLogin_count(long login_count) {
		this.login_count = login_count;
	}

	public String getLogin_status() {
		return login_status;
	}

	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

}
