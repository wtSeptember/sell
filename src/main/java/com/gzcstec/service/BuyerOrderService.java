package com.gzcstec.service;

import com.gzcstec.dto.OrderDTO;
import org.springframework.stereotype.Service;

/**
 * 买家订单抽离的业务逻辑累
 * Created by Administrator on 2017/10/18 0018.
 */
public interface BuyerOrderService {

    //查找订单
    OrderDTO findOne(String openid , String orderId);

    //取消订单
    OrderDTO cancel(String openid , String orderId);
}
