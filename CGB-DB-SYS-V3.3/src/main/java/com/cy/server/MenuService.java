package com.cy.server;

import java.util.List;
import java.util.Map;

import com.cy.pojo.Menu;
import com.cy.pojo.Node;
import com.cy.pojo.UserMenu;
public interface MenuService {
	 List<Map<String,Object>> findObjects();
	 int saveObject(Menu entity);
	 int deteleMenuById(Integer id);
	 List<Node> findZtreeMenuNodes();
	 int updateObject(Menu entity);
	 UserMenu findUserMenu();
	 List<UserMenu> findUserMenu(String username);
}
