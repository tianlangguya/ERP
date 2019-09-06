package com.mxp.erp.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mxp.erp.api.IPermissionService;
import com.mxp.erp.api.IRoleService;
import com.mxp.erp.api.IUserService;
import com.mxp.erp.entity.PermissionEntity;
import com.mxp.erp.entity.RoleEntity;
import com.mxp.erp.entity.UserEntity;

public class MyShiroRealm extends AuthorizingRealm {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPermissionService permissionService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
		Object principal = principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principals);
			SecurityUtils.getSubject().logout();
			return null;
		}
		if (principal instanceof UserEntity) {
			UserEntity userLogin = (UserEntity) principal;
			List<RoleEntity> roleEntities = roleService.getByUserId(userLogin.getId());
			Set<String> roles = roleEntities.stream().map(RoleEntity::getRoleName).collect(Collectors.toSet());
			authorizationInfo.addRoles(roles);
			List<PermissionEntity> permissionEntities = permissionService.getByUserId(userLogin.getId());
			Set<String> permissions = permissionEntities.stream().map(PermissionEntity::getPermissionName)
					.collect(Collectors.toSet());
			authorizationInfo.addStringPermissions(permissions);
		}
		logger.info("---- 获取到以下权限 ----");
		logger.info(authorizationInfo.getStringPermissions().toString());
		logger.info("---------------- Shiro 权限获取成功 ----------------------");
		return authorizationInfo;
	}

	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 *
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String name = token.getUsername();
		char[] psd = token.getPassword();
		String password = psd == null || psd.length == 0 ? null : String.valueOf(psd);
		// 从数据库获取对应用户名密码的用户
		if (name == null || password == null) {
			return null;
		}
		UserEntity user = userService.getByNameAndPassword(name, password);
		if (user != null) {
			logger.info("---------------- Shiro 凭证认证成功 ----------------------");
			ByteSource salt = ByteSource.Util.bytes(user.getUserName());
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), // 用户
					user.getPassword(), salt, // 密码
					getName() // realm name
			);
			return authenticationInfo;
		}
		throw new UnknownAccountException("没有此用户");
	}

}
