package com.cy.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.annotation.AsLog;
import com.cy.dao.LogDao;
import com.cy.pojo.Log;
import com.cy.util.IPUtils;
import com.cy.util.ShiroUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AspectLg {
	@Autowired
    LogDao  ld;
	@Pointcut("@annotation(com.cy.annotation.AsLog)")
	public void  pointcut1() {}
	@Around("pointcut1()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
	long s = System.currentTimeMillis();
	Object pro = jp.proceed();
	long e = System.currentTimeMillis();
	long t=e-s;
	saveLog(jp,t);
	return pro;
	
		
		
	}
	
	private void saveLog(ProceedingJoinPoint jp, long t) throws Exception {
		
		String oper="operation";
		String para = new ObjectMapper().writeValueAsString(jp.getArgs());
		MethodSignature sig = (MethodSignature) jp.getSignature();
		Class<? extends Object> c1 = jp.getTarget().getClass();
		String c = c1.getName();
		
		
		String name = sig.getName();
		Class<? extends ProceedingJoinPoint> class1 = jp.getClass();
		
		System.out.println(class1+"------------------------------------------------------------");
		
		
		
		
		String m = c1.getDeclaredMethod(name, sig.getParameterTypes()).getName();
		String mname=c+"."+m;
	    AsLog an = c1.getDeclaredMethod(name, sig.getParameterTypes()).getAnnotation(AsLog.class);
	    if (an!=null) {
			 oper=an.value();
		}
	    Log l = new Log();
		 l.setCreatedTime(new Date());
		 l.setIp(IPUtils.getIpAddr());
		l.setOperation(oper);
		l.setMethod(mname);
		l.setTime(t);
		l.setUsername(ShiroUtils.getUsername());
		l.setParams(para);
	   ld.insertObject(l);
	}
	


}
