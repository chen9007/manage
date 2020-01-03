package com.cy.impl;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.annotation.AsLog;
import com.cy.common.exception.ServiceException;
import com.cy.dao.LogDao;
import com.cy.pojo.Log;
import com.cy.pojo.Page;
import com.cy.pojo.PageSize;
import com.cy.server.LogService;
@Service
public class LogImpl  implements LogService{
		  @Autowired
	      private LogDao sysLogDao;
		  @Autowired
		  PageSize p;
		  @Override
		  
		  public Page<Log> findPageObjects(
				  String name, Integer pageCurrent) {
			  //1.验证参数合法性
			  //1.1验证pageCurrent的合法性，
			  //不合法抛出IllegalArgumentException异常
			  if(pageCurrent==null||pageCurrent<1)
			  throw new IllegalArgumentException("当前页码不正确");
			  //2.基于条件查询总记录数
			  //2.1) 执行查询
			  int rowCount=sysLogDao.getRowCount(name);
			  //2.2) 验证查询结果，假如结果为0不再执行如下操作
			  if(rowCount==0)
	          throw new ServiceException("系统没有查到对应记录");
			  //3.基于条件查询当前页记录(pageSize定义为2)
			  //3.1)定义pageSize
			  int pageSize=p.getPageSize();
			  //3.2)计算startIndex
			  int startIndex=(pageCurrent-1)*pageSize;
			  //3.3)执行当前数据的查询操作
			  List<Log> records=
			  sysLogDao.findPageObjects(name, startIndex, pageSize);
			  //4.对分页信息以及当前页记录进行封装
			  //4.1)构建PageObject对象
			  Page<Log> pageObject=new Page<>();
			 
			  //4.2)封装数据
			  pageObject.setPageCurrent(pageCurrent);
			  pageObject.setPageSize(pageSize);
			  pageObject.setRowCount(rowCount);
			  pageObject.setRecords(records);
	          pageObject.setPageCount((rowCount-1)/pageSize+1);
			  //5.返回封装结果。
			  return pageObject;
		  }
		  //**********************************删除*******************
		  @Override
		  @AsLog("deletelog")
		  @RequiresPermissions("sys:menu:add1")
		  public int deleteObjects(Integer...ids) {
		  		//1.判定参数合法性
		  		if(ids==null||ids.length==0)
		  	    throw new IllegalArgumentException("请选择一个");
		  		//2.执行删除操作
		  		int rows;
		  		try{
		  		rows=sysLogDao.deleteObjects(ids);
		  		}catch(Throwable e){
		  		e.printStackTrace();
		  		//发出报警信息(例如给运维人员发短信)
		  		throw new ServiceException("系统故障，正在恢复中...");
		  		}
		  		//4.对结果进行验证
		  		if(rows==0)
		  		throw new ServiceException("记录可能已经不存在");
		  		//5.返回结果
		  		return rows;
		  }

	}
