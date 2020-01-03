package com.cy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.cy.pojo.UserDept;
import com.cy.pojo.UserMsg;

@Mapper
public interface UserDao {
   List<UserDept> get(String username,Integer startIndex,Integer pageSize);
    int getRowCount(String username);
    Integer updateValidById(@Param("id")Integer id, @Param("valid")Integer valid,@Param("modifiedUser")String modifiedUser);
    int insertObject(UserMsg entity);
	int updatePassword(String hex, String salt, Integer id);
	UserMsg findUserByName(String username);
	
}
