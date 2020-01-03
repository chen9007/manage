package com.cy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pojo.Log;

@Mapper
public interface LogDao {
	List<Log> findPageObjects(
			@Param("username")String  username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	int getRowCount(@Param("username") String username);
	//************删除************
	int deleteObjects(@Param("ids")Integer...ids);
	//****************************新增
	int insertObject(Log entity);

}


