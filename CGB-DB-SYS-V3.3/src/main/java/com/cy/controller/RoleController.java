package com.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pojo.JsonResult;
import com.cy.pojo.Menu;
import com.cy.pojo.Role;
import com.cy.server.MenuService;
import com.cy.server.RoleService;

@RestController
@RequestMapping("/role/")
public class RoleController {
	@Autowired
	private RoleService rs;
	 @RequestMapping("doFindRoles")
	 public JsonResult doFindRoles() {
		 return new JsonResult(rs.findObjects());
	 }

	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doUpdateObject(String name,Integer pageCurrent){
		
		return new JsonResult(rs.dofindRole(name,pageCurrent));
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult dofindObjectbyid(Integer id){
		System.out.println(id);
		return new JsonResult(rs.findObjectById(id));
	}
	@RequestMapping("doUpdateObject")
	
	 @ResponseBody public JsonResult doUpdateObject(Role entity,Integer[]
	 menuIds){ System.out.println(entity); return new JsonResult(rs.update(entity,
	 menuIds)); }
	
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDelObject(Integer id){
		
		return new JsonResult(rs.deleteObject(id));
	}
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
	    		Role entity,Integer[] menuIds){
	    	    rs.saveObject(entity,menuIds);
	return new JsonResult("save ok");    
	}

	


}
