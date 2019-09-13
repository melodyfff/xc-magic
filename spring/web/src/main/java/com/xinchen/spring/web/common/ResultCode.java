package com.xinchen.spring.web.common;

/**
 * 返回结果状态码枚举类
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/14 1:10
 */
public enum  ResultCode {
    /***/
    SUCCESS(0,"SUCCESS"),
    FAIL(-1,"FAIL");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
