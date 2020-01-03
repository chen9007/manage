package com.cy.common.intercepter;

import java.rmi.ServerException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cy.common.exception.ServiceException;
@Component
public class TimeIntercepter implements HandlerInterceptor{
	@Override
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.err.println("------------------------------666----------------------------------------");
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 7);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			long start=c.getTimeInMillis();
			c.set(Calendar.HOUR_OF_DAY, 23);
			long end =c.getTimeInMillis();
			long time=System.currentTimeMillis();
			if (time<start||time>end)throw new ServiceException("不在访问时间内");
				
				return true;			
	}

}
