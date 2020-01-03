package com.cy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pojo.Role;
import com.cy.pojo.RoleMenu;
import com.cy.pojo.User;

@Mapper
public interface RoleDao {
				List<Role> dofindRole(@Param("name")String name, 
						@Param("startIndex")Integer startIndex,
						@Param("pageSize")Integer pageSize);
				Integer getRowCount(@Param("name")String name);
				int deleteObject(Integer id);
				Role findById(Integer id);
				int update(Role entity);
				int insertObject(Role entity);
				RoleMenu findObjectById(Integer id);
				List<User> findObjects();
				
}
