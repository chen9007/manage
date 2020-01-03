package com.cy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pojo.JsonResult;
import com.cy.pojo.UserMsg;
import com.cy.server.UserService;
import com.cy.util.ShiroUtils;

@RestController
@RequestMapping("/user/")
public class UserController {
	@Autowired
	UserService  us;
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		System.out.println(11111);
		 us.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("update ok");
	 }

	@RequestMapping("doFindPageObjects")
	public JsonResult doLogListUI(String username,Integer pageCurrent){
		return new JsonResult(us.page(username, pageCurrent));
	}
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer id,Integer valid,String modifiedUser){
		
		return new JsonResult(us.updateValidById(id, valid,modifiedUser));
	}
	@RequestMapping("doSaveObject")
	
	public JsonResult doSaveObject(
			UserMsg entity,
			Integer[] roleIds){
		us.saveObject(entity,roleIds);
		return new JsonResult("save ok");
	}
	@RequestMapping("dogetuser")	
	public JsonResult dogetuser(){
		String username = ShiroUtils.getUsername();
		
		System.err.println(6666);
		return new JsonResult(username+"111");
	}
	@RequestMapping("doLogin")	
	public JsonResult doLogin(Boolean isRememberMe,
			String username,String password){
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken tok = new UsernamePasswordToken(username, password);
		 if(isRememberMe) {
				tok.setRememberMe(true); 
			 }

		subject.login(tok);

		return new JsonResult("login ok");
	}
		
}
