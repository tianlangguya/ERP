package com.mxp.erp.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private Date creationTime;
	private Date lastModifyTime;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
