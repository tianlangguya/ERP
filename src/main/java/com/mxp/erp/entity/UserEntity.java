package com.mxp.erp.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mxp.erp.base.BaseEntity;

@TableName("sys_user")
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableField("USER_NAME")
	private String userName;

	@TableField("PASSWORD")
	private String password;

	@TableField("TELEPHONE")
	private String telephone;

	@TableField("EMAIL")
	private String email;

	@TableField("LOGIN_COUNT")
	private long loginCount;

	@TableField("LOGIN_STATUS")
	private String loginStatus;

	@TableField("LOGIN_ERROR_TIMES")
	private int loginErrorTimes;

	@TableField("LOGIN_ERROR_TIME")
	private Date loginErrorTime;

	@TableField("STATE")
	private int state;

	@TableField("AGE")
	private long age;

	@TableField("DEPARTURE_TIME")
	private Date departureTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(long loginCount) {
		this.loginCount = loginCount;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public int getLoginErrorTimes() {
		return loginErrorTimes;
	}

	public void setLoginErrorTimes(int times) {
		this.loginErrorTimes = times;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.userName + "|" + this.password + "|" + this.telephone + "|" + this.email + "|" + this.loginCount
				+ "|" + this.loginStatus + "|" + this.age + "|";
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getLoginErrorTime() {
		return loginErrorTime;
	}

	public void setLoginErrorTime(Date loginErrorTime) {
		this.loginErrorTime = loginErrorTime;
	}

}
