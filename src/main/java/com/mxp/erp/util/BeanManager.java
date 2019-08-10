package com.mxp.erp.util;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

public class BeanManager implements ApplicationContextAware {

	private static transient ApplicationContext APPLICATION_CONTEXT = null;

	public static <T> T getService(Class<T> classType) {
		return APPLICATION_CONTEXT.getBean(classType);
	}

	public static <T> T getService(String name, Class<T> classType) {
		return APPLICATION_CONTEXT.getBean(name, classType);
	}

	public static <T> T getService(Class<T> classType, Object... args) {
		return APPLICATION_CONTEXT.getBean(classType, args);
	}

	public static <T> Collection<? extends T> getServices(Class<T> classType) {
		Map<String, T> beansOfType = APPLICATION_CONTEXT.getBeansOfType(classType);
		return beansOfType.values();
	}

	public static <T> Map<String, T> getBeansOfType(Class<T> classType) {
		return APPLICATION_CONTEXT.getBeansOfType(classType);
	}

	public static Environment getEnvironment() {
		return APPLICATION_CONTEXT.getEnvironment();
	}

	public static void publishEvent(Object event) {
		APPLICATION_CONTEXT.publishEvent(event);
	}

	@Override
	public void setApplicationContext(ApplicationContext content) throws BeansException {
		APPLICATION_CONTEXT = content;
	}

}
