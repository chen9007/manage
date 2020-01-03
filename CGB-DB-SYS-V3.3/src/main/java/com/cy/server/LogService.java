package com.cy.server;

import com.cy.pojo.Log;
import com.cy.pojo.Page;
public interface LogService {
	Page<Log> findPageObjects(
			 String username,
			 Integer pageCurrent);
	int deleteObjects(Integer...ids);
}


