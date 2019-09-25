package com.mxp.erp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="com.mxp.erp",exclude = {HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class})
@MapperScan("com.mxp.erp.dao")
@EnableScheduling
@EnableCaching
public class ErpApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(ErpApplication.class);
	 }
	
}
