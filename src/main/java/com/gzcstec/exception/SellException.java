package com.gzcstec.exception;

import com.gzcstec.enums.ExceptionCodeEnums;
import lombok.Getter;

/**
 * Created by Administrator on 2017/10/15 0015.
 */
@Getter
public class SellException extends RuntimeException {
    /**错误状态码.*/
    private Integer code;

    public SellException(ExceptionCodeEnums exceptionCodeEnums) {
        super(exceptionCodeEnums.getMsg());
        this.code = exceptionCodeEnums.getCode();
    }

    public SellException(ExceptionCodeEnums exceptionCodeEnums , String msg) {
        super(msg);
        this.code = exceptionCodeEnums.getCode();
    }
}
