package com.cy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleMenuDao {
	int deleteObjectsByMenuId(Integer menuId);
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);

	int deleteObjectsByRoleId(Integer roleid);

	int insertObject(Integer id, Integer[] menuIds);
	 List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds) ;

}
