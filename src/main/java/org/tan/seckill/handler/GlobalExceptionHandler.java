package org.tan.seckill.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tan.seckill.vo.GlobalResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

//	@ExceptionHandler(value = Exception.class)
//	public Object errorHandler(HttpServletRequest reqest, HttpServletResponse response, Exception e) throws Exception {
//
//		e.printStackTrace();
//
//		if (isAjax(reqest)) {
//			return JsonResult.errorException(e.getMessage());
//		} else {
//			ModelAndView mav = new ModelAndView();
//			mav.addObject("exception", e);
//			mav.addObject("url", reqest.getRequestURL());
//			mav.setViewName(IMOOC_ERROR_VIEW);
//			return mav;
//		}
//	}

    /**
     *
     * @Title: IMoocExceptionHandler.java
     * @Package com.imooc.exception
     * @Description: 判断是否是ajax请求 Copyright: Copyright (c) 2017
     *               Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author leechenxiang
     * @date 2017年12月3日 下午1:40:39
     * @version V1.0
     */
//	public static boolean isAjax(HttpServletRequest httpRequest) {
//		return (httpRequest.getHeader("X-Requested-With") != null
//				&& "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
//	}

}