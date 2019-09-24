package com.gzcstec.repository;

import com.gzcstec.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14 0014.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单id获取订单详情
     * @param orderId
     * @return
     */
    public List<OrderDetail> findByOrderId(String orderId);

}
