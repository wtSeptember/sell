package com.gzcstec.service.impl;

import com.gzcstec.dataobject.OrderDetail;
import com.gzcstec.dto.OrderDTO;
import com.gzcstec.enums.OrderStatusEnums;
import com.gzcstec.enums.PayStatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/15 0015.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    final static String BUYER_OPENID = "110110110";

    final static String ORDER_ID = "1508001584066370209";

    @Test
    public void create() throws Exception {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("123456789012");
        orderDTO.setBuyerAddress("广州从师科技有限公司");
        orderDTO.setBuyerName("范先生");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123458");
        o1.setProductQuantity(2);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(5);


        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}" , result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1508001584066370209");
        log.info("【订单详情】result={}" , orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() throws Exception {
        Pageable pageable = new PageRequest(0 , 5);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID , pageable);
        log.info("【用户订单】result={}" , orderDTOPage);
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnums.CANCEL.getCode() , result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnums.FINISH.getCode() , result.getOrderStatus());
    }

    @Test
    public void pay() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.pay(orderDTO);
        Assert.assertEquals(PayStatusEnums.FINISH.getCode(), result.getPayStatus());
    }

    @Test
    public void list() throws Exception {
        PageRequest pageRequest = new PageRequest(0 , 2);
        Page<OrderDTO> orderDTOS = orderService.findList(pageRequest);
        Assert.assertTrue("卖家获取订单列表" , orderDTOS.getContent().size() > 0);
    }

}