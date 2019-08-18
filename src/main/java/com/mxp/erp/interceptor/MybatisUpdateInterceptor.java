package com.mxp.erp.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mxp.erp.dao.UserDao;
import com.mxp.erp.entity.UserEntity;
import com.mysql.jdbc.Statement;

//@Component
//@Configuration
@Intercepts({ @Signature(type = UserDao.class, method = "update", args = { UserEntity.class }) })
public class MybatisUpdateInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(MybatisUpdateInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		logger.warn(invocation.toString());
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object o) {
		return Plugin.wrap(o,this);
	}

	@Override
	public void setProperties(Properties properties) {
		logger.warn(properties.toString());

	}

}
