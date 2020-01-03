package com.cy.server;

import java.util.List;

import com.cy.pojo.Page;
import com.cy.pojo.Role;
import com.cy.pojo.RoleMenu;
import com.cy.pojo.User;

public interface RoleService {

	public Page<Role> dofindRole(String name, Integer pageCurrent);
	
	int deleteObject(Integer id);

	Role dofindById(Integer id);

	 int update(Role entity,Integer[] roleId);
	 int saveObject(Role entity,Integer[]menuIds);
	 RoleMenu findObjectById(Integer id) ;
	 List<User> findObjects();
}
