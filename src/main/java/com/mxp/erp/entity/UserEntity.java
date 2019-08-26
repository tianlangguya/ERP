package com.mxp.erp.entity;

import java.util.Date;

import com.mxp.erp.base.BaseEntity;

public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String userName;

	private String password;

	private String telephone;

	private String email;

	private long loginCount;

	private String loginStatus;
	
	private int loginErrorTimes;
	
	private int loginErrorTimesLimit;
	
	private Date loginErrorTime;
	
	private int state;

	private long age;
	
	private Date DepartureTime;

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
		return DepartureTime;
	}

	public void setDepartureTime(Date departureTime) {
		DepartureTime = departureTime;
	}

	public int getLoginErrorTimesLimit() {
		return loginErrorTimesLimit;
	}

	public void setLoginErrorTimesLimit(int loginErrorTimesLimit) {
		this.loginErrorTimesLimit = loginErrorTimesLimit;
	}

	public Date getLoginErrorTime() {
		return loginErrorTime;
	}

	public void setLoginErrorTime(Date loginErrorTime) {
		this.loginErrorTime = loginErrorTime;
	}

}
