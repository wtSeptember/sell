package com.gzcstec.service.impl;

import com.gzcstec.dto.OrderDTO;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.exception.SellException;
import com.gzcstec.service.BuyerOrderService;
import com.gzcstec.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@Service
@Slf4j
public class BuyOrderServiceImpl implements BuyerOrderService{

    @Autowired
    OrderService orderService;

    @Override
    public OrderDTO findOne(String openid, String orderId) {
        return checkOwner(openid , orderId);
    }

    @Override
    public OrderDTO cancel(String openid, String orderId) {

        OrderDTO orderDTO = checkOwner(openid , orderId);
        if(orderDTO == null) {
            log.error("【取消订单】订单不存在，openid={}，orderId={}" , openid , orderId);
            throw new SellException(ExceptionCodeEnums.ORDER_NOT_FOUND);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOwner(String openid , String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);

        if(orderDTO == null) {
            return null;
        }

        if(!openid.equals(orderDTO.getBuyerOpenid())) {
            log.error("【查找订单】openid不一致，openid={}，orderId={}" , openid , orderId);
            throw new SellException(ExceptionCodeEnums.NOT_CURRENT_USER);
        }

        return orderDTO;
    }
}
