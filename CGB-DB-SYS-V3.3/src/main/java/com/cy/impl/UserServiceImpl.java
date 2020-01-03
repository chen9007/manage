package com.cy.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.cy.annotation.AsLog;
import com.cy.common.exception.ServiceException;
import com.cy.dao.UserDao;
import com.cy.dao.UserRoleDao;
import com.cy.pojo.Page;
import com.cy.pojo.UserDept;
import com.cy.pojo.UserMsg;
import com.cy.server.UserService;
import com.cy.util.ShiroUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao ud;

	
	  @Autowired
	  Page<UserDept> p;
	  @Autowired
	  
	UserRoleDao  ur;
	 
	  @Override
	  @AsLog("updatepassword")
	
		public int updatePassword(String password, String newPassword,
	 String cfgPassword) {
			//1.判定新密码与密码确认是否相同
			if(StringUtils.isEmpty(newPassword))
			throw new IllegalArgumentException("新密码不能为空");
			if(StringUtils.isEmpty(cfgPassword))
			throw new IllegalArgumentException("确认密码不能为空");
			if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
			//2.判定原密码是否正确
			if(StringUtils.isEmpty(password))
			throw new IllegalArgumentException("原密码不能为空");
			//获取登陆用户
		/*
		 * UserMsg use = (UserMsg) SecurityUtils.getSubject().getPrincipal();
		 * 
		 * SimpleHash hx = new SimpleHash("SHA1", password, use.getSalt(), 1); if
		 * (!use.getPassword().equals(hx.toHex())) throw new
		 * IllegalArgumentException("原密码不正确"); String s = UUID.randomUUID().toString();
		 * hx=new SimpleHash("SHA1", newPassword, s, 1); int
		 * rows=ud.updatePassword(hx.toHex(), s,use.getId()); if(rows==0) throw new
		 * ServiceException("修改失败");
		 */
			
			UserMsg user=(UserMsg)SecurityUtils.getSubject().getPrincipal();
			
			SimpleHash sh=new SimpleHash("MD5",
			password, user.getSalt(), 1);
			if(!user.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
			//3.对新密码进行加密
			String salt=UUID.randomUUID().toString();
			sh=new SimpleHash("MD5",newPassword,salt, 1);
			//4.将新密码加密以后的结果更新到数据库
			int rows=ud.updatePassword(sh.toHex(), salt,user.getId());
			if(rows==0)
			throw new ServiceException("修改失败");
			return rows;
		}

	  @Override
	  @AsLog("saveUser")
	    public int saveObject(UserMsg entity, Integer[] roleIds) {
	    	long start=System.currentTimeMillis();
	    	log.info("start:"+start);
	    	//1.参数校验
	    	if(entity==null)
	    		throw new ServiceException("保存对象不能为空");
	    	if(StringUtils.isEmpty(entity.getUsername()))
	    		throw new ServiceException("用户名不能为空");
	    	if(StringUtils.isEmpty(entity.getPassword()))
	    		throw new ServiceException("密码不能为空");
	    	if(roleIds==null || roleIds.length==0)
	    		throw new ServiceException("至少要为用户分配角色");
	    	//2.保存用户自身信息
	        //2.1对密码进行加密
	    	String source=entity.getPassword();
	    	String salt=UUID.randomUUID().toString();
	    	SimpleHash sh=new SimpleHash(//Shiro框架
	    			"MD5",//algorithmName 算法
	    			 source,//原密码
	    			 salt, //盐值
	    			 1);//hashIterations表示加密次数
	    	entity.setSalt(salt);
	    	entity.setPassword(sh.toHex());
	    	int rows=ud.insertObject(entity);
	    	//3.保存用户角色关系数据
	    	ur.insertObjects(entity.getId(), roleIds);
	    	long end=System.currentTimeMillis();
	    	log.info("end:"+end);
	    	log.info("total time :"+(end-start));
	    	//4.返回结果
	    	return rows;
	}

	  
	@Override
	public Page page(String username, Integer pageCurrent) {
		//Page<Object> page = new Page<>();
		//if (StringUtils.isEmptyOrWhitespace(username)) throw new ServiceException("用户名不正确");
		if (pageCurrent==null ||pageCurrent<1) {
			 pageCurrent=1;
			
			}
		int rowCount=ud.getRowCount(username);
		
		  //2.2) 验证查询结果，假如结果为0不再执行如下操作
		  if(rowCount==0)
        throw new ServiceException("系统没有查到对应记录");
		  //3.基于条件查询当前页记录(pageSize定义为2)
		  //3.1)定义pageSize
		  int pageSize=p.getPageSize();
		  //3.2)计算startIndex
		  int startIndex=(pageCurrent-1)*pageSize;
		  //3.3)执行当前数据的查询操作
		  
		  List<UserDept> records = ud.get(username, startIndex, pageSize);
		
		  //4.对分页信息以及当前页记录进行封装
		  //4.1)构建PageObject对象
		 
		 
		  //4.2)封装数据
		  p.setPageCurrent(pageCurrent);
		  p.setPageSize(pageSize);
		  p.setRowCount(rowCount);
		  p.setRecords(records);
        p.setPageCount((rowCount-1)/pageSize+1);
      
		  //5.返回封装结果。
		  return p;
	}

	

	@Override
	  @AsLog("updatevalid")
	public Integer updateValidById(Integer id, Integer valid, String modifiedUser) {
		if(id==null||id<=0)
			throw new ServiceException("参数不合法,id="+id);
			if(valid!=1&&valid!=0)
			throw new ServiceException("参数不合法,valie="+valid);
			
			
			System.out.println(id+"--"+valid+"---"+modifiedUser);
			
			if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
			//2.执行禁用或启用操作
			int rows=ud.updateValidById(id, valid, modifiedUser);
			System.out.println(rows+"-----------------------------------------------------------------------------------------------------------------");
			//3.判定结果,并返回
			if(rows==0)
			throw new ServiceException("此记录可能已经不存在");
			return rows;

	
	}

}
