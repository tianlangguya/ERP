package com.mxp.erp.constants;

import java.util.ArrayList;
import java.util.List;

public enum LoginStatus {
	LOGIN("login"), LOGOUT("logout");

	private String name;

	LoginStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static LoginStatus getValue(String name) {
		for (LoginStatus c : LoginStatus.values()) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return LOGIN;
	}

	public static String[] getValues() {
		List<String> values = new ArrayList<>();
		for (LoginStatus c : LoginStatus.values()) {
			values.add(c.getName());
		}
		return values.toArray(new String[] {});
	}

}
