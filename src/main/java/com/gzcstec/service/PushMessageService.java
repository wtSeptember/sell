package com.gzcstec.service;

import com.gzcstec.dto.OrderDTO;

/**
 * 消息推送
 * Created by Administrator on 2017/11/6 0006.
 */
public interface PushMessageService {

    /**
     * 订单状态改变通知
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
