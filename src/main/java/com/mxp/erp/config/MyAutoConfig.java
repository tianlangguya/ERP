package com.mxp.erp.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
@ComponentScan(basePackages = { "com.mxp.erp" })
@MapperScan(basePackages = { "com.mxp.erp.dao" })
@EnableTransactionManagement
public class MyAutoConfig {
	@Bean
	public SpringLiquibase liquibase(DataSource dataSource) throws Exception {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setDataSource(dataSource);
		liquibase.setChangeLog("classpath:liquibase/master.xml");
		liquibase.setContexts("development,test,production");
		liquibase.setShouldRun(true);
		return liquibase;
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

}
