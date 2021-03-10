package com.project.common.excetion;

import com.project.common.enums.BaseErrorInfoEnum;
import com.project.common.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /***
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BadException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, BadException e) {
        logger.error("发生业务异常！请求是：{" + req.getRequestURL() + "}", e.getErrorMsg());
        return Result.error(e.getErrorCode(), e.getErrorMsg());
    }

    /***
     * 处理空指针的异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！请求是：{" + req.getRequestURL() + "}", e.getMessage());
        return Result.error(BaseErrorInfoEnum.BODY_NOT_MATCH);
    }

    /***
     * 处理运行时异常(子类异常可能会被框架包装成运行时异常）)
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("发生运行时异常！请求是：{" + req.getRequestURL() + "}", e.getMessage());
        if (e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")) {
            return Result.error(BaseErrorInfoEnum.ACCESS_ERROR);
        }
        return Result.error(e.getMessage());
    }

    /***
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        logger.error("未知异常！请求是：{" + req.getRequestURL() + "}", e.getMessage());
        return Result.error(BaseErrorInfoEnum.INTERNAL_SERVER_ERROR);
    }
}
