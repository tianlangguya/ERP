package com.mxp.erp.entity;

import java.util.Date;

public class UserDetailedInfo extends UserEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String department;//部门
	private String position;//职位
	private Date entryTime;//入职时间
	private String signingCompany;//签约公司
	private float companyYear;//司龄
	private Date estimateLeaveTime;//预计离职时间
	private String resignationDesc;//离职说明
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public String getSigningCompany() {
		return signingCompany;
	}
	public void setSigningCompany(String signingCompany) {
		this.signingCompany = signingCompany;
	}
	public float getCompanyYear() {
		return companyYear;
	}
	public void setCompanyYear(float companyYear) {
		this.companyYear = companyYear;
	}
	public Date getEstimateLeaveTime() {
		return estimateLeaveTime;
	}
	public void setEstimateLeaveTime(Date estimateLeaveTime) {
		this.estimateLeaveTime = estimateLeaveTime;
	}
	public String getResignationDesc() {
		return resignationDesc;
	}
	public void setResignationDesc(String resignationDesc) {
		this.resignationDesc = resignationDesc;
	}
	
}
