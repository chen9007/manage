package com.cy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.annotation.AsLog;
import com.cy.dao.DeptDao;
import com.cy.pojo.Dept;
import com.cy.server.DeptService;
@Service
public class DeptServerImpl implements DeptService{
	@Autowired
	DeptDao dd;

	@Override
	  
	public List<Dept> findDept() {
		
		return  dd.findDept(null);
	}

}
