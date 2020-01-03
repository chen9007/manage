package com.cy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.pojo.Menu;
import com.cy.pojo.Node;
import com.cy.pojo.UserMenu;

@Mapper
public interface MenuDao {
	//********查询总菜单*****
	List<Map<String,Object>> findObjects();
	int insertObject(Menu entity);
	int getChildCount(Integer id);
	int deleteObject(Integer id);
	List<Node> findZtreeMenuNodes();
	int updateObject(Menu entity);
	
	List<Menu> selectChildMenu();
	UserMenu selectUserMenu();
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);
	List<UserMenu> getMenuByUserName(String username);
	List<Menu>  selectmenubyId(Integer id)
	;

}
