package com.cy.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;


@Configuration

public class SpringShiroConfig {
		@Bean
		public SecurityManager  manage1(Realm realm,CookieRememberMeManager rememberManager,DefaultWebSessionManager sessionManager,CacheManager cacheManager) {
			  DefaultWebSecurityManager sm= new DefaultWebSecurityManager();
			//sm.setRealm(realm);
			sm.setRealm(realm);
			sm.setRememberMeManager(rememberManager);
			sm.setSessionManager(sessionManager);
			sm.setCacheManager(cacheManager);
			return sm;	
		}
		
		
	
	 @Bean public ShiroFilterFactoryBean sfb1(SecurityManager manage) {
	  ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
	  sfBean.setSecurityManager(manage); sfBean.setLoginUrl("/doLoginUI");
	  LinkedHashMap<String, String> mp = new LinkedHashMap<>();
	  mp.put("/bower_components/**","anon"); mp.put("/build/**","anon");
	 mp.put("/dist/**","anon"); mp.put("/plugins/**","anon");
	 mp.put("/user/doLogin","anon"); mp.put("/user/doLogout","logout");
	 mp.put("/**","authc"); mp.put("/**","user");
	 sfBean.setFilterChainDefinitionMap(mp);
	
	  
	  return sfBean;
	  
	  }
	 
		@Bean
		public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
				 return new LifecycleBeanPostProcessor();
		}
		@DependsOn("lifecycleBeanPostProcessor")
		@Bean
		public DefaultAdvisorAutoProxyCreator newDefaultAdvisorAutoProxyCreator() {
				 return new DefaultAdvisorAutoProxyCreator();
		}
		//第三步:配置advisor对象,shiro框架底层会通过此对象的matchs方法返回值决定是否创建代理对象,进行权限控制。
		 @Bean
		public AuthorizationAttributeSourceAdvisor 
		newAuthorizationAttributeSourceAdvisor(
			    		    @Autowired SecurityManager securityManager) {
				        AuthorizationAttributeSourceAdvisor advisor=
						new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
			return advisor;
		}
		 @Bean
		 public CookieRememberMeManager rememberMeManager() {
			 CookieRememberMeManager cManager=
			 new CookieRememberMeManager();
	SimpleCookie cookie=new SimpleCookie("rememberMe");
			 cookie.setMaxAge(10*60);
			 cManager.setCookie(cookie);
			 return cManager;
		 }
		 @Bean   
		 public DefaultWebSessionManager sessionManager() {
		 		 DefaultWebSessionManager sManager=
		 				 new DefaultWebSessionManager();
		 		 sManager.setGlobalSessionTimeout(60*60*1000);
		 		 return sManager;
		 	 }
		 @Bean
		 public CacheManager shiroCacheManager(){
		 	 return new MemoryConstrainedCacheManager();
		 }


}
