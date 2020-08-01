package com.taikang.httprelated;

/**
 * 1
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/8/1 13:11
 * 4
 */
public enum HttpCode {
     /** 200请求成功 */
    OK(200,"请求成功"),
    INTERNAL_SERVER_ERROR(500,"服务器异常");

    private final Integer value;
    private final String message;

    private HttpCode(Integer value,String message) {
        this.value = value;
        this.message=message;
    }

    /**
     * Return the integer value of this status code.
     */
    public Integer value() {
        return this.value;
    }
    public String toString() {
        return this.value.toString();
    }
}
