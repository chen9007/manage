package com.cy.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pojo.JsonResult;
import com.cy.pojo.Menu;
import com.cy.server.DeptService;

@RestController
@RequestMapping("/dept/")
public class DeptController {

	  @Autowired DeptService ds;
	  
	  @RequestMapping("doFindObjects")
	  
	  @ResponseBody public JsonResult doUpdateObject(Menu entity){
		  System.out.println(111);
	  return new JsonResult(ds.findDept());
	 }
	  @RequestMapping("doFindZTreeNodes")
	  
	  @ResponseBody public JsonResult doZTree(){
		  
		  return new JsonResult(ds.findDept());
	  }
	 
}
