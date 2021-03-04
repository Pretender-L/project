package com.project.common.aop;

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
import java.util.Map;

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

        StringBuilder sb = new StringBuilder();
        sb.append("\r\n");
        sb.append(request.getMethod()).append("-->").append(request.getRequestURL());
        sb.append("\r\n<<<----------REQUEST  HEADER---------->>>");
        //令牌
        sb.append("\r\ntoken       :").append(request.getHeader("AUTHORIZATION"));
        //Content-Type代表发送端（客户端|服务器）发送的实体数据的数据类型。比如：Content-Type：text/html;代表发送端发送的数据格式是html。
        sb.append("\r\ntype        :").append(request.getContentType());
        //字符编码
        sb.append("\r\nencoding    :").append(request.getCharacterEncoding());
        //Accept代表发送端（客户端）希望接受的数据类型;比如：Accept：text/xml;代表客户端希望接受的数据类型是xml类型
        sb.append("\r\nAccept      :").append(request.getHeader("Accept"));
        //网页来源
        sb.append("\r\nReferer     :").append(request.getHeader("Referer"));
        //User-Agent是叫做用户代理，一个特殊字符串头，是一种向访问网站提供你所使用的浏览器类型及版本、操作系统及版本、浏览器内核、等信息的标识
        sb.append("\r\nAgent       :").append(request.getHeader("User-Agent"));
        sb.append("\r\n<<<----------REQUEST  PARAMS---------->>>");
        //请求参数map
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.isEmpty()) {
            sb.append("\r\nParams      :null");
        } else {
            for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
                for (String s : stringEntry.getValue()) {
                    sb.append("\r\n").append(stringEntry.getKey()).append(" = ").append(s);
                }
            }
        }
        logger.info(sb.toString());
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
            logger.info("\r\n" + stopWatch.prettyPrint());
        }
    }
}
