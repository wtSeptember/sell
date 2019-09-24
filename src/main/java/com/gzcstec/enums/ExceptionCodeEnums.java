package com.gzcstec.enums;

import lombok.Getter;

/**
 * 异常状态码
 * Created by Administrator on 2017/10/15 0015.
 */
@Getter
public enum ExceptionCodeEnums {
    PARAM_ERROR(1 , "参数不正确"),

    PRODUCT_NOT_FOUND(10 , "未找到商品"),

    PRODUCT_STOCK_ERROR(11 , "商品库存不足"),

    ORDER_NOT_FOUND(12 , "未找到订单"),

    ORDER_DETAIL_NOT_FOUND(13 , "未找到订单详情"),

    ORDER_STATUS_ERROR(14 , "订单状态不正确"),

    ORDER_STATUS_UPDATE_FAIL(15 , "订单状态更新失败"),

    ORDER_PAY_NOT_FOUND(16 , "支付状态不正确"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    ORDER_PAY_STATUS_UPDATE_FAIL(18, "订单支付状态更新失败"),

    Order_CART_EMPTY(19 , "购物车为空"),

    NOT_CURRENT_USER(20 , "不是当前用户"),

    WECHAT_MP_ERROR(21 , "微信网页授权失败"),

    PAY_MONEY_NOT_EQUAL(22 , "支付金额不一致"),

    CANCEL_ORDER_SUCCESS(23 , "取消订单成功"),

    FINISH_ORDER_SUCCESS(24 , "完结订单成功"),

    PRODUCT_OFF_SALE_FAIL(25 , "商品下架失败"),

    PRODUCT_ON_SALE_FAIL(26 , "商品下架失败"),

    PRODUCT_OFF_SALE_SUCCESS(27 , "商品下架成功"),

    PRODUCT_ON_SALE_SUCCESS(28 , "商品下架成功"),

    PRODUCT_UPDATE_SUCCESS(29 , "商品更新成功"),

    CATEGORY_NOT_FOUND(30 , "商品类目没有找到"),

    CATEGORY_UPDATE_SUCCESS(31 , "商品类目更新成功"),

    CATEGORY_UPDATE_FAIL(32 , "商品类目更新失败"),

    LOGIN_FAIL(33 , "登录失败"),

    LOGIN_SUCCESS(34 , "登录成功"),

    LOGOUT_SUCCESS(35 , "登出成功"),

    ;

    private Integer code;

    private String msg;

    ExceptionCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
