package com.gzcstec.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gzcstec.dataobject.OrderDetail;
import com.gzcstec.enums.OrderStatusEnums;
import com.gzcstec.enums.PayStatusEnums;
import com.gzcstec.enums.ProductStatusEnums;
import com.gzcstec.utils.EnumsUtils;
import com.gzcstec.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/15 0015.
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /**订单id*/
    private String orderId;

    /**买家姓名*/
    private String buyerName;

    /**买家手机号码*/
    private String buyerPhone;

    /**买家送货地址*/
    private String buyerAddress;

    /**买家微信openid*/
    private String buyerOpenid;

    /**订单总额*/
    private BigDecimal orderAmount;

    /**订单状态*/
    private Integer orderStatus;

    /**支付状态*/
    private Integer payStatus;

    /**创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnum() {
        return EnumsUtils.getEnumsByCode(orderStatus , OrderStatusEnums.class);
    }

    @JsonIgnore
    public PayStatusEnums getPayStatusEnum() {
        return EnumsUtils.getEnumsByCode(payStatus , PayStatusEnums.class);
    }
}
