package com.mxp.erp.interceptor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisUpdateInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object object = invocation.getArgs()[1];
		SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
		if (sqlCommandType.equals(SqlCommandType.INSERT)) {
			Date date = new Date();
			Field fieldCreate = getField(object, "creationTime");
			fieldCreate.setAccessible(true);
			fieldCreate.set(object, date);
			Field fieldUpdate = getField(object, "lastModifyTime");
			fieldUpdate.setAccessible(true);
			fieldUpdate.set(object, date);
		} else if (sqlCommandType.equals(SqlCommandType.UPDATE)) {
			ParamMap<?> method = (ParamMap<?>) object;
			Object clazz = method.get("param1");
			Field fieldUpdate = getField(clazz, "lastModifyTime");
			fieldUpdate.setAccessible(true);
			fieldUpdate.set(clazz, new Date());
		}
		return invocation.proceed();
	}

	public Field getField(Object object, String field) {
		try {
			Field result = object.getClass().getSuperclass().getDeclaredField(field);
			if (result != null) {
				return result;
			}
		} catch (Exception e) {
			getField(object, field);
		}
		return null;
	}

	@Override
	public Object plugin(Object o) {
		return Plugin.wrap(o, this);
	}

	@Override
	public void setProperties(Properties properties) {
		System.out.println(properties.getProperty("name"));
	}

}
