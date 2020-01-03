package com.cy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pojo.JsonResult;
import com.cy.pojo.Menu;
import com.cy.pojo.UserMenu;
import com.cy.server.MenuService;

@RestController
@RequestMapping("/menu/")
public class MenuController {
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(Menu entity){
		menuService.updateObject(entity);
		return new JsonResult("update ok");
	}

	@Autowired
	private MenuService  menuService;
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(menuService.findObjects());
		
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult dodelObject(Integer id){
		menuService.deteleMenuById(id);
		return new JsonResult("delete ok/删除成功");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(Menu entity){
		menuService.saveObject(entity);
		return new JsonResult("save ok");
	}
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	
	 public JsonResult doFindZtreeMenuNodes(){
		 return new JsonResult(
		 menuService.findZtreeMenuNodes());
	 }
	

	
	


}
