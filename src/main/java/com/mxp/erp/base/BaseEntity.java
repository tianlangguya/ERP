package com.mxp.erp.base;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public long id;
	public Date create_time;
	public Date last_modify_time;
	public String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getLast_modify_time() {
		return last_modify_time;
	}

	public void setLast_modify_time(Date last_modify_time) {
		this.last_modify_time = last_modify_time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
