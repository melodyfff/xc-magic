package com.xinchen.base.core.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/10 17:26
 */
@Data
@AllArgsConstructor
public class Response {
    private int status;
    private Object result;
    private boolean error;
    private String message;
}
