package com.mxp.erp.util;

import java.util.UUID;

public class IdGenerator {

	public static String generate() {
		String id = UUID.randomUUID().toString();
		return id;
	}
}
