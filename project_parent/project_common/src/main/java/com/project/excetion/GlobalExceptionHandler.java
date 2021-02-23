package com.project.excetion;

import com.project.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import com.project.excetion.service.impl.BaseErrorInfoEnumImpl;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /***
     * 处理自定义的业务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadException.class)
    @ResponseBody
    public Result bizExceptionHandler(HttpServletRequest req, BadException e) {
        logger.error("发生业务异常！请求是：{" + req.getRequestURL() + "}", e.getErrorMsg());
        return Result.error(e.getErrorCode(), e.getErrorMsg());
    }

    /***
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, NullPointerException e) {
        logger.error("发生空指针异常！请求是：{" + req.getRequestURL() + "}", e);
        return Result.error(BaseErrorInfoEnumImpl.BODY_NOT_MATCH);
    }

    /***
     * 处理运行时异常(子类异常可能会被框架包装成运行时异常）)
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("发生运行时异常！请求是：{" + req.getRequestURL() + "}", e);
        if (e.getClass().getName().equals("org.springframework.security.access.AccessDeniedException")){
            return Result.error(BaseErrorInfoEnumImpl.ACCESSERROR);
        }
        return Result.error(e.getMessage());
    }

    /***
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest req, Exception e) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        logger.error("未知异常！请求是：{" + req.getRequestURL() + "}", e);
        return Result.error(BaseErrorInfoEnumImpl.INTERNAL_SERVER_ERROR);
    }

}
