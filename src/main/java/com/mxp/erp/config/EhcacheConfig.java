package com.mxp.erp.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class EhcacheConfig {

  @Bean
  public EhCacheManagerFactoryBean ehCacheFactory(){
    EhCacheManagerFactoryBean ehCacheFactory = new EhCacheManagerFactoryBean();
    ehCacheFactory.setConfigLocation(new ClassPathResource("ehcache.xml"));
    ehCacheFactory.setShared(true);
    return ehCacheFactory;
  }
  @Bean
  public EhCacheCacheManager ehCacheManager(EhCacheManagerFactoryBean bean) {
    EhCacheCacheManager  ehcacheManager = new EhCacheCacheManager();
    ehcacheManager.setCacheManager(bean.getObject());
    return ehcacheManager;
  }
}
