package com.mxp.erp.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mxp.erp.entity.UserEntity;

public class User extends UserEntity implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User(UserEntity user) {
		this.setAge(user.getAge());
		this.setCreate_time(user.create_time);
		this.setDescription(user.getDescription());
		this.setEmail(user.getEmail());
		this.setId(user.getId());
		this.setLast_modify_time(user.getLast_modify_time());
		this.setLogin_count(user.getLogin_count());
		this.setLogin_status(user.getLogin_status());
		this.setTelephone(user.getTelephone());
		this.setUser_name(user.getUser_name());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		String username = this.getUsername();
		if (username != null) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
			authorities.add(authority);
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		return this.getUser_name();
	}

    //账户是否未过期,过期无法验证
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

    //指定用户是否解锁,锁定的用户无法进行身份验证
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	 //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 是否可用 ,禁用的用户不能身份验证
	@Override
	public boolean isEnabled() {
		return true;
	}
}
