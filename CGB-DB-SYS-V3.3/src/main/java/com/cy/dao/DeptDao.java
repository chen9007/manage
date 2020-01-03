package com.cy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cy.pojo.Dept;
@Mapper
public interface DeptDao {

	public List<Dept> findDept(String usename) ;
	Dept selectById(Integer id);
	
}
