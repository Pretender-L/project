package com.project.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private StopWatch stopWatch;

    /***
     * 切入点
     * @within:切入使用该注解的类中所有方法
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void logPrinter() {
    }

    /**
     * 前置通知
     */
    @Before("logPrinter()")
    private void beforeLog() {
        //获得请求对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //程序执行时长计时器
        stopWatch = new StopWatch(request.getRequestURI());
        stopWatch.start();
    }

    /**
     * 返回通知
     */
    @AfterReturning("logPrinter()")
    private void afterLog() {
        if (stopWatch != null) {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
            logger.info(stopWatch.prettyPrint());
        }
    }
}
