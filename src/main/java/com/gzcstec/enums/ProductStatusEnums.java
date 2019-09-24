package com.gzcstec.enums;

import lombok.Getter;

/**
 * 商品状态
 * Created by Administrator on 2017/10/13 0013.
 */
@Getter
public enum ProductStatusEnums {
    UP(1 , "在架"),
    DOWN(0 , "下架")
    ;

    private Integer code;

    private String message;

    ProductStatusEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
