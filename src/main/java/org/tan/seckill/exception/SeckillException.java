package org.tan.seckill.exception;

/**
 * 秒杀异常
 * 这里之所以继承RuntimeException，是为了方便事务回滚。而自定义异常的好处在于：一方面可以使代码语义化，另一方面使得我们编码更加方便。
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
