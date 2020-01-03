package com.cy.server;

import com.cy.pojo.Page;
import com.cy.pojo.UserDept;
import com.cy.pojo.UserMsg;

public interface UserService {

	 Page<UserDept> page(String username,Integer currentPage) ;
	 
	Integer updateValidById(Integer id, Integer valid,String modifiedUser);
	int saveObject(UserMsg entity,Integer[]roleIds);

      int updatePassword(String pwd, String newPwd, String cfgPwd);

}
