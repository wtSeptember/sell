package com.gzcstec.service.impl;

import com.gzcstec.dto.OrderDTO;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.service.OrderService;
import com.gzcstec.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2017/10/19 0019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    private static final String orderId = "1509440283285154336";

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderService.findOne(orderId);
        payService.create(orderDTO);
    }

    @Test
    public void refund() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1509449897001983544");
        payService.refund(orderDTO);
    }

}