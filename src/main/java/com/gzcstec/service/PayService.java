package com.gzcstec.service;

import com.gzcstec.dto.OrderDTO;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * Created by Administrator on 2017/10/19 0019.
 */
public interface PayService {
    /**
     * 下单
     * @param orderDTO
     * @return
     */
    PayResponse create(OrderDTO orderDTO);

    /**
     * 处理异步通知
     * @param notifyData
     */
    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
