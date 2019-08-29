package com.mxp.erp.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RestResponse<T> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4150800751579236573L;

	private int code;

	private boolean success;

	private String message;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp = Calendar.getInstance().getTime();

	private T data;

	private String path;

	private String exception;

	public RestResponse() {
		success = true;
		code = RestResponseCode.OK;
	}

	public RestResponse(int code, boolean success, String message, T data) {
		super();
		this.code = code;
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public void setCodeAndIsSuccess(int code, boolean success) {
		this.code = code;
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
