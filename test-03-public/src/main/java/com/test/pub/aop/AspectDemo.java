//package com.test.pub.aop;
//
//import com.google.gson.Gson;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//
//
//@Aspect//这个注解的作用是:将一个类定义为一个切面类
//@Component//这个注解的作用：把切面类加入到IOC容器中
//@Order(1)//这个注解的作用是:标记切面类的处理优先级,i值越小,优先级别越高.PS:可以注解类,也能注解到方法上
//@Slf4j
//public class AspectDemo {
//
//    private Gson gson = new Gson();
//
//    //申明一个切点 里面是excution表达式
//    @Pointcut("execution(* com.test.pub..*.*(..))")
//    private void webLog() {
//    }
//
//    //请求method前打印内容
//    @Before(value = "webLog()")//这个注解的作用是:在切点前执行方法,内容为指定的切点
//    public void methodBefore(JoinPoint joinPoint) {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        //打印请求内容
//        log.info("========================请求内容======================");
//        log.info("请求地址:" + request.getRequestURI().toString());
//        log.info("请求方式" + request.getMethod());
//        log.info("请求类方法" + joinPoint.getSignature());
//        log.info("请求类方法参数" + Arrays.toString(joinPoint.getArgs()));
//        log.info("========================请求内容======================");
//
//    }
//
//    //在方法执行完结后打印返回内容
//    //这个注解的作用是:在切入点,return后执行,如果想对某些方法的返回参数进行处理,可以在这操作
//    @AfterReturning(returning = "o", pointcut = "webLog()")
//    public void methodAfterReturing(Object o) {
//        log.info("--------------返回内容----------------");
//        log.info("Response内容:" + gson.toJson(o));
//        log.info("--------------返回内容----------------");
//
//    }
//
//}
//
//
