package com.cy.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.common.exception.ServiceException;
import com.cy.dao.MenuDao;
import com.cy.dao.RoleMenuDao;
import com.cy.dao.UserDao;
import com.cy.dao.UserRoleDao;
import com.cy.pojo.UserMsg;
@Service
public class UserRealm extends AuthorizingRealm{
    @Autowired
    UserDao ud;
    @Autowired
    UserRoleDao  userRoleDao;
    @Autowired
    RoleMenuDao  roleMenuDao;
    @Autowired
    MenuDao  menuDao;
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher matc = new HashedCredentialsMatcher();
		matc.setHashIterations(1);
		matc.setHashAlgorithmName("MD5");
		super.setCredentialsMatcher(matc);
	}
	
	
	
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
		PrincipalCollection principals) {
		//1.获取登录用户信息，例如用户id
		UserMsg user=(UserMsg)principals.getPrimaryPrincipal();
		Integer userId=user.getId();
		//2.基于用户id获取用户拥有的角色(sys_user_roles)
		List<Integer> roleIds=
		userRoleDao.findRoleIdsByUserId(userId);
		if(roleIds==null||roleIds.size()==0)
		throw new AuthorizationException();
		//3.基于角色id获取菜单id(sys_role_menus)
		Integer[] array={};
		List<Integer> menuIds=
		roleMenuDao.findMenuIdsByRoleIds(
				roleIds.toArray(array));
	    if(menuIds==null||menuIds.size()==0)
	    throw new AuthorizationException();
		//4.基于菜单id获取权限标识(sys_menus)
	    List<String> permissions=
	    menuDao.findPermissions(
	    	menuIds.toArray(array));
		//5.对权限标识信息进行封装并返回
	    Set<String> set=new HashSet<>();
	    for(String per:permissions){
	    	if(!StringUtils.isEmpty(per)){
	    		set.add(per);
	    	}
	    }
	    SimpleAuthorizationInfo info=
	    new SimpleAuthorizationInfo();
	    info.setStringPermissions(set);
		return info;//返回给授权管理器
	}



	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;
		String username = utoken.getUsername();
		UserMsg user = ud.findUserByName(username);
		if (user==null) throw new ServiceException("该用户不存在");
		if (user.getValid()==0) throw new ServiceException("该用户尚未授权");
		ByteSource salt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),salt,getName());
		System.err.println(info);
		return info;
	}

}
