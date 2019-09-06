package com.mxp.erp.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mxp.erp.filter.KickoutSessionControlFilter;
import com.mxp.erp.filter.MyRememberFilter;
import com.mxp.erp.security.MyShiroRealm;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager,
			KickoutSessionControlFilter kickoutSessionControlFilter) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		filtersMap.put("kickout", kickoutSessionControlFilter);
		filtersMap.put("MyRememberFilter", myRememberFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(1024);
		return hashedCredentialsMatcher;
	}

	@Bean
	public KickoutSessionControlFilter kickoutSessionControlFilter(CacheManager cacheManager,
			DefaultWebSessionManager sessionManager) {
		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
		kickoutSessionControlFilter.setCacheManager(cacheManager);
		kickoutSessionControlFilter.setSessionManager(sessionManager);
		kickoutSessionControlFilter.setKickoutAfter(false);
		kickoutSessionControlFilter.setMaxSession(1);
		kickoutSessionControlFilter.setKickoutUrl("/login");
		return kickoutSessionControlFilter;
	}

	@Bean
	public SecurityManager securityManager(CacheManager cacheManager, DefaultWebSessionManager sessionManager,
			MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(myShiroRealm);
		// 自定义缓存实现 使用redis
		securityManager.setCacheManager(cacheManager);
		// 自定义session管理 使用redis
		securityManager.setSessionManager(sessionManager);
		return securityManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 *
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return myShiroRealm;
	}

	/**
	 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	@Bean
	public RedisCacheManager cacheManager(RedisManager redisManger) {
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		redisCacheManager.setRedisManager(redisManger);
		return redisCacheManager;
	}

	/**
	 * 配置shiro redisManager 使用的是shiro-redis开源插件
	 *
	 * @return
	 */
	@Bean
	public RedisManager redisManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("127.0.0.1");
		redisManager.setPort(6379);
		redisManager.setExpire(1800);// 配置缓存过期时间
		redisManager.setTimeout(0);
		return redisManager;
	}

	/**
	 * Session Manager 使用的是shiro-redis开源插件
	 */
	@Bean
	public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(redisSessionDAO);
		return sessionManager;
	}

	/**
	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
	 */
	@Bean
	public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager);
		return redisSessionDAO;
	}

	/***
	 * 授权所用配置
	 *
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public MyRememberFilter myRememberFilter() {
		return new MyRememberFilter();
	}
}
