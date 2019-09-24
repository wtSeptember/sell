package com.gzcstec.dto;

import lombok.Data;

/**
 * 购物车
 * Created by Administrator on 2017/10/15 0015.
 */
@Data
public class CartDTO {

    /**商品id.*/
    private String productId;

    /**商品数量.*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
