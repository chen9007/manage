package com.cy.impl;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.cy.annotation.AsLog;
import com.cy.common.exception.ServiceException;
import com.cy.dao.MenuDao;
import com.cy.dao.RoleMenuDao;
import com.cy.pojo.Menu;
import com.cy.pojo.Node;
import com.cy.pojo.UserMenu;
import com.cy.server.MenuService;

@Service
//@EnableTransactionManagement
@Transactional(isolation = Isolation.READ_COMMITTED,
             rollbackFor =Throwable.class,
             timeout = 30,
             propagation = Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService{
	@Autowired
    private MenuDao menuDao;
	@Autowired
	RoleMenuDao rmd;
	  @Override
	 
	  public List<Map<String, Object>> findObjects() {
		List<Map<String,Object>> list=
			menuDao.findObjects();
		if(list==null||list.size()==0)
		throw new ServiceException("没有对应的菜单信息");
		return list;

}
	@Override
	 @Transactional(readOnly = false)
	
	public int saveObject(Menu entity) {
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
			if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
			int rows;
			//2.保存数据
			try{
			rows=menuDao.insertObject(entity);
			}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("保存失败");
			}
			//3.返回数据
			return rows;

	}
	@Override
	 @Transactional(readOnly = false)
	public int updateObject(Menu entity) {
			//1.合法验证
			if(entity==null)
			throw new ServiceException("保存对象不能为空");
			if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("菜单名不能为空");
			
			//2.更新数据
			int rows=menuDao.updateObject(entity);
			if(rows==0)
			throw new ServiceException("记录可能已经不存在");
			//3.返回数据
			return rows;
	}


	 @Override
	  @AsLog("delete")
	 @Transactional(readOnly = false)
	public int deteleMenuById(Integer id) {
		if(id==null||id<=0)
			throw new IllegalArgumentException("请先选择");
			//2.基于id进行子元素查询
			int count=menuDao.getChildCount(id);
			if(count>0)
			throw new ServiceException("请先删除子菜单");
			//3.删除角色,菜单关系数据
			rmd.deleteObjectsByMenuId(id);
			//4.删除菜单元素
			int rows=menuDao.deleteObject(id);
			if(rows==0)
			throw new ServiceException("此菜单可能已经不存在");
			//5.返回结果
			return rows;

	}
	@Override
	 
	public List<Node> findZtreeMenuNodes() {
		
		return menuDao.findZtreeMenuNodes();
	}
	@Override
	 
	public UserMenu findUserMenu() {
		
		return menuDao.selectUserMenu();
	}
	@Override
	public List<UserMenu> findUserMenu(String username) {
	List<UserMenu>li= menuDao.getMenuByUserName(username);
		
		
		
		
		return li;
	}
	}
