package com.cy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.annotation.AsLog;
import com.cy.common.exception.ServiceException;
import com.cy.dao.RoleDao;
import com.cy.dao.RoleMenuDao;
import com.cy.dao.UserRoleDao;
import com.cy.pojo.Page;
import com.cy.pojo.PageSize;
import com.cy.pojo.Role;
import com.cy.pojo.RoleMenu;
import com.cy.pojo.User;
import com.cy.server.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	PageSize p;
	@Autowired
	RoleDao rd;
	@Autowired
	private RoleMenuDao sysRoleMenuDao;
	@Autowired
	private UserRoleDao sysUserRoleDao;

	@Override
	  @AsLog("deleterole")
	public int deleteObject(Integer id) {
		// 1.验证数据的合法性
		if (id == null || id <= 0)
			throw new IllegalArgumentException("请先选择");
		// 3.基于id删除关系数据
		int n1 = sysRoleMenuDao.deleteObjectsByMenuId(id);
		System.out.println("n1:" + n1);
		int n2 = sysUserRoleDao.deleteObjectsByRoleId(id);
		System.out.println("n2" + n2);
		int rows = rd.deleteObject(id);
		if (rows == 0)
			throw new ServiceException("此菜单可能已经不存在");
		// 4.删除角色自身

		// 5.返回结果
		return rows;
	}

	@Override
	public Page<Role> dofindRole(String name, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("页码不正确");
		Integer pageSize = p.getPageSize();

		/** 总记录(通过查询获得) */
		Integer rowCount = rd.getRowCount(name);
		if (rowCount <= 0)
			throw new IllegalArgumentException("没查询到相关记录");
		/** 总页数(通过计算获得) */
		Integer pageCount = (rowCount - 1) / pageSize + 1;
		Integer startIndex = (pageCurrent - 1) * pageSize;
		/** 当前页记录 */
		List<Role> records = rd.dofindRole(name, startIndex, pageSize);
		return new Page<>(pageCurrent, pageSize, rowCount, pageCount, records);

	}

	@Override
	public Role dofindById(Integer id) {

		Role r = rd.findById(id);
		System.out.println(r);
		if (r == null)
			throw new ServiceException("该记录可能已经不存在");
		return r;
	}


	@Override 
	public int update(Role entity,Integer[] menuIds) { if(entity==null)
		throw new ServiceException("更新的对象不能为空"); if(entity.getId()==null) throw new
		ServiceException("id的值不能为空");

		if(StringUtils.isEmpty(entity.getName())) throw new
		ServiceException("角色名不能为空");

		if(menuIds==null||menuIds.length==0) throw new
		ServiceException("必须为角色指定一个权限");

		int row = rd.update(entity); 
		if (row<=0) throw new
		ServiceException("数据不存在或者更新失败");
		sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
		sysRoleMenuDao.insertObject(entity.getId(),menuIds);

		return row; }

	@Override
	public int saveObject(Role entity, Integer[] menuIds) {
		// 1.参数有效性校验
		if (entity == null)
			throw new IllegalArgumentException("保存对象不能为空");
		if (StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("角色名不允许为空");
		if (menuIds == null || menuIds.length == 0)
			throw new ServiceException("必须为角色分配权限");
		// 2.保存角色自身信息
		int rows = rd.insertObject(entity);
		// 3.保存角色菜单关系数据
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		// 4.返回业务结果
		return rows;
	}

	@Override
	public RoleMenu findObjectById(Integer id) {
		if (id == null || id <= 0)
			throw new ServiceException("id的值不合法");
		// 2.执行查询
		RoleMenu result = rd.findObjectById(id);
		// 3.验证结果并返回
		if (result == null)
			throw new ServiceException("此记录已经不存在");
		System.out.println(result);
		return result;

	}

	@Override
	public List<User> findObjects() {
		// TODO Auto-generated method stub
		return rd.findObjects();
	}

}
