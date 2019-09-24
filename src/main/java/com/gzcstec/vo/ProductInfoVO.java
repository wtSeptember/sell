package com.gzcstec.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 返回给前端的商品信息
 * Created by Administrator on 2017/10/13 0013.
 */
@Data
public class ProductInfoVO implements Serializable{

    private static final long serialVersionUID = -6463033438238617190L;
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
