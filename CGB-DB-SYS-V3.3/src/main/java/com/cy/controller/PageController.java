package com.cy.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.annotation.AsLog;
import com.cy.pojo.JsonResult;
import com.cy.pojo.UserMenu;
import com.cy.server.MenuService;
import com.cy.util.ShiroUtils;
@Controller

public class PageController {
	@Autowired
	MenuService ms;
	@RequestMapping("/123")
	public   String selectUsermenu(Model model){
		UserMenu um = ms.findUserMenu();
		
		System.out.println(um);
		model.addAttribute(um);
		
		return "starter";
	}
	
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult  login(String username,String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken();
		subject.login(token);
		
		return new JsonResult("login ok!");}
	@RequestMapping("doLoginUI")
	public String  login() {
		
		return "sys/login";}
	@RequestMapping("dj")
	public String  dj() {
		return "dj";}
	@RequestMapping("doPageUI")
	public String doPageUI(){
		 return "common/page";
	 }
	
	@Autowired
	MenuService menuService;
	@RequestMapping("index")
	public String doindexUI(Model model){
		
		String username = ShiroUtils.getUsername();
		model.addAttribute("username",username);
		List<UserMenu> userMenus = menuService.findUserMenu(username);
	    model.addAttribute("userMenus",userMenus);
		System.out.println(userMenus);
		return "starter";
	}
	//**************点击加载菜单
	@RequestMapping("menu/menu_list")
	public String doMenuUI() {
		return "sys/menu_list";
	}
	//*************共性
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
		
			return "sys/"+moduleUI;
	}

}
