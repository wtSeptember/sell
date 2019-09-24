package com.gzcstec.form;

import com.gzcstec.enums.ProductStatusEnums;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 买家添加商品前台数据
 * Created by Administrator on 2017/11/2 0002.
 */
@Data
public class ProductForm {
    /**商品id*/
    private String productId;

    /**商品名称*/
    @NotEmpty(message = "商品名称不能为空")
    private String productName;

    /**商品单价*/
    private BigDecimal productPrice;

    /**商品库存*/
    private Integer productStock;

    /**商品描述*/
    private String productDescription;

    /**商品小图*/
    private String productIcon;

    /**商品类目*/
    private Integer categoryType;

}
