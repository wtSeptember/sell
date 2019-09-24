package com.gzcstec.repository;

import com.gzcstec.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/10/14 0014.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("123456");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(2);
        orderDetail.setProductIcon("http://xxxxx.jpg");
        OrderDetail result = orderDetailRepository.save(orderDetail);

        Assert.assertEquals("123456" , orderDetail.getDetailId());
    }

    @Test
    public void findByOrderIdTest(){
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("123456");

        Assert.assertNotNull(orderDetailList);
    }

}