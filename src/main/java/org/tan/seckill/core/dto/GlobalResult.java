package org.tan.seckill.core.dto;

import lombok.Data;

/**
 * 通用数据封装类
 */
@Data
public class GlobalResult<T> {
    //是否成功
    private boolean success;
    //错误提示信息
    private String error;
    //成功时的数据
    private T data;

    public GlobalResult(T data) {
        this.success = true;
        this.data = data;
    }

    public GlobalResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static GlobalResult ok(Object data) {
        return new GlobalResult<>(data);
    }

    /**
     * 失败
     * @param error
     * @return
     */
    public static GlobalResult error(String error) {
        return new GlobalResult<>(false, error);
    }
}
