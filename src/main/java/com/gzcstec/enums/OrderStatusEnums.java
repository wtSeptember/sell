package com.gzcstec.enums;

import lombok.Getter;

/**
 * 订单状态
 * Created by Administrator on 2017/10/14 0014.
 */
@Getter
public enum OrderStatusEnums implements CodeEnums<Integer>{
    NEW(0 , "新订单"),
    FINISH(1 , "完成订单"),
    CANCEL(2 , "取消订单")
    ;

    private Integer code;

    private String msg;

    OrderStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
