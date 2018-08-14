package org.tan.seckill.vo;

import lombok.Data;

/**
 * 通用数据封装类
 */
@Data
public class GlobalResult<T> {
    private boolean success;
    private String error;
    private T data;

    public GlobalResult(T data) {
        this.success = true;
        this.data = data;
    }

    public GlobalResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public static GlobalResult ok(Object data) {
        return new GlobalResult<>(data);
    }

    public static GlobalResult error(String error) {
        return new GlobalResult<>(false,error);
    }
}
