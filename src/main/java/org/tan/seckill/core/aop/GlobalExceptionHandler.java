package org.tan.seckill.core.aop;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tan.seckill.core.dto.GlobalResult;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理类
 * <p>
 * 使用全局异常捕获器，一方面可以捕获到整个项目中的Exception及其子类（包含RuntimeException等），
 * 另一方面可以对异常进行统一处理并返回统一的数据格式，为前端提供友好的数据交互。
 */
//处理通知
@ControllerAdvice
public class GlobalExceptionHandler {


    public static final String IMOOC_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalResult exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return GlobalResult.error(e.getMessage());
    }

}