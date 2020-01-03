package com.cy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pojo.JsonResult;
import com.cy.pojo.Log;
import com.cy.pojo.Page;
import com.cy.server.LogService;

@Controller

@RequestMapping("/log/")
public class LogController {
	@Autowired
	private LogService  logservice;
	@RequestMapping("doLogListUI")
	public String doLogListUI(){
		return "sys/log_list";
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String username,Integer pageCurrent){
	 Page<Log> pageObject=
		logservice.findPageObjects(username,pageCurrent);
	return new JsonResult(pageObject);
	}
	//**************************删除
	 @RequestMapping("doDeleteObjects")
	  @ResponseBody
	  public JsonResult doDeleteObjects(Integer... ids){
		  logservice.deleteObjects(ids);
		  return new JsonResult("delete ok");
		
	  }


}


