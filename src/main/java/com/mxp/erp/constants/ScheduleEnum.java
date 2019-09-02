package com.mxp.erp.constants;

public enum ScheduleEnum {
	LOGIN_ERROR_TIMES(1), LEVEL_OFFICE(2);

	private int type;

	ScheduleEnum(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}
