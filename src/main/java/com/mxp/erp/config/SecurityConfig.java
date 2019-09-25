package com.mxp.erp.config;

import com.mxp.erp.security.MyAuthenticationFailHander;
import com.mxp.erp.security.MyAuthenticationProvider;
import com.mxp.erp.security.MyAuthenticationSuccessHandler;
import com.mxp.erp.security.MyRbacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationProvider provider;

    @Autowired
    MyAuthenticationSuccessHandler successHandler;

    @Autowired
    MyAuthenticationFailHander failHander;

    @Autowired
    MyRbacService rbacService;

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .loginProcessingUrl("/form")
                .successHandler(successHandler)
                .failureHandler(failHander)
                .permitAll()                   //  定义当需要用户登录时候，转到的登录页面。
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me").userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository()).tokenValiditySeconds(3600).and()
                .authorizeRequests().antMatchers("/loginError","/index","/user/registerUser").permitAll()// 定义哪些URL需要被保护、哪些不需要被保护
                .anyRequest().access("@myRbacService.hasPermission(request,authentication)") //必须经过认证以后才能访问
                .and()
                .csrf().disable();
    }

}
