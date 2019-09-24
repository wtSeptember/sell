package com.gzcstec.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gzcstec.dataobject.ProductInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前端的商品详情（包含类目）
 * Created by Administrator on 2017/10/13 0013.
 */
@Data
public class ProductVO implements Serializable{

    private static final long serialVersionUID = -4471285242600830239L;
    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfos;

}
