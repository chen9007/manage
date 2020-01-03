package com.cy.aspect;
/*
 * package com.cy.aspect;
 * 
 * 
 * 
 * import java.lang.reflect.Method; import java.util.Date;
 * 
 * import org.aspectj.lang.ProceedingJoinPoint; import
 * org.aspectj.lang.Signature; import org.aspectj.lang.annotation.Around; import
 * org.aspectj.lang.annotation.Aspect; import
 * org.aspectj.lang.annotation.Pointcut; import
 * org.aspectj.lang.reflect.MethodSignature; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Component;
 * 
 * import com.cy.annotation.AsLog; import com.cy.dao.LogDao; import
 * com.cy.pojo.Log; import com.fasterxml.jackson.core.JsonProcessingException;
 * import com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import lombok.extern.slf4j.Slf4j;
 * 
 * @Aspect
 * 
 * @Component
 * 
 * @Slf4j public class LogAspect { //private Logger
 * log=Logger.getLogger(SysLogAspect.class);
 * 
 * @Autowired private LogDao logd;
 * 
 * @Pointcut("@annotation(com.cy.annotation.AsLog)") public void logPointCut(){}
 * 
 * @Around("logPointCut()") public Object around(ProceedingJoinPoint //连接点
 * jointPoint) throws Throwable{ long startTime=System.currentTimeMillis();
 * //执行目标方法(result为目标方法的执行结果) Object result=jointPoint.proceed(); long
 * endTime=System.currentTimeMillis(); long totalTime=endTime-startTime;
 * log.info("方法执行的总时长为:"+totalTime); saveSysLog(jointPoint,totalTime); return
 * result; } private void saveSysLog(ProceedingJoinPoint jp, long t) throws
 * Exception{ String params = new
 * ObjectMapper().writeValueAsString(jp.getArgs()); MethodSignature s =
 * (MethodSignature) jp.getSignature(); s.getName(); s.getParameterTypes();
 * String operation=""; String x = jp.getTarget().getClass().getName(); Class<?>
 * y = jp.getTarget().getClass().getDeclaringClass(); String method=y+"."+x;
 * Method meth =
 * jp.getTarget().getClass().getDeclaredMethod(s.getName(),s.getParameterTypes()
 * ); AsLog a = meth.getAnnotation(AsLog.class); if (a!=null) { operation =
 * a.value(); } Log log=new Log(); log.setIp("172.88.1.173");
 * log.setUsername("杨硕"); log.setOperation(operation); log.setMethod(method);
 * log.setParams(params); log.setTime(t); log.setCreatedTime(new Date());
 * 
 * logd.insertObject(log);
 * 
 * 
 * 
 * } private void saveSysLog1(ProceedingJoinPoint point, long t) throws
 * Exception{
 * 
 * //1.获取日志信息 MethodSignature ms=(MethodSignature) point.getSignature();
 * Class<?> targetClass= point.getTarget().getClass(); String
 * className=targetClass.getName(); //获取接口声明的方法 String
 * methodName=ms.getMethod().getName(); Class<?>[]
 * parameterTypes=ms.getMethod().getParameterTypes(); //获取目标对象方法 Method
 * targetMethod=targetClass.getDeclaredMethod( methodName,parameterTypes);
 * //判定目标方法上是否有RequestLog注解 boolean
 * flag=targetMethod.isAnnotationPresent(AsLog.class); String username;
 * //此工具类需要学完shiro，AOP再进行自定义实现 ShiroUtils.getPrincipal().getUsername(); //获取方法参数
 * Object[] paramsObj=point.getArgs();
 * System.out.println("paramsObj="+paramsObj); //将参数转换为字符串 String params=new
 * ObjectMapper() .writeValueAsString(paramsObj); //2.封装日志信息 Log log=new Log();
 * log.setUsername(username); //登陆的用户 //假如目标方法对象上有注解,我们获取注解定义的操作值 if(flag){
 * AsLog requestLog= targetMethod.getDeclaredAnnotation(AsLog.class);
 * log.setOperation(requestLog.value()); }
 * log.setMethod(className+"."+methodName);//className.methodName()
 * log.setParams(params);//method params log.setIp(IPUtils.getIpAddr());//ip 地址
 * log.setTime(t);// log.setCreateDate(new Date()); //3.保存日志信息
 * logd.insertObject(log);
 * 
 * 
 * 
 * 
 * }}
 */