package org.tan.seckill.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tan.seckill.core.dto.GlobalResult;
import org.tan.seckill.core.dto.SeckillResult;
import org.tan.seckill.core.enums.SeckillStateEnum;
import org.tan.seckill.core.exception.RepeatKillException;
import org.tan.seckill.core.exception.SeckillClosedException;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局的的异常拦截器（拦截所有的控制器）
 * 带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
@Order(-1)
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public GlobalResult exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return GlobalResult.error(e.getMessage());
    }

    /**
     * 重复秒杀异常
     */
    @ExceptionHandler(RepeatKillException.class)
    @ResponseBody
    public GlobalResult notFount(RepeatKillException e) {
        log.info("重复秒杀异常:", e);
        return GlobalResult.ok(SeckillResult.error(SeckillStateEnum.REPEAT_KILL));
    }

    /**
     * 秒杀结束异常
     */
    @ExceptionHandler(SeckillClosedException.class)
    @ResponseBody
    public GlobalResult notFount(SeckillClosedException e) {
        log.info("秒杀结束异常:", e);
        return GlobalResult.ok(SeckillResult.error(SeckillStateEnum.END));
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public GlobalResult notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return GlobalResult.ok(SeckillResult.error(SeckillStateEnum.INNER_ERROR));
    }
}
