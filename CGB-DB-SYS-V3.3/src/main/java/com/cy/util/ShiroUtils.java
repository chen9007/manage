package com.cy.util;

import org.apache.shiro.SecurityUtils;

import com.cy.pojo.UserMsg;

public class ShiroUtils {

	  public static String getUsername() {
		  return getUser().getUsername(); }
	 //** 获取登录用户 *//*
					 public static UserMsg getUser() {
						 return (UserMsg)
								 
					  SecurityUtils.getSubject().getPrincipal(); }
					
					 
}
