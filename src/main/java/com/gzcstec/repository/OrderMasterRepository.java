package com.gzcstec.repository;

import com.gzcstec.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/14 0014.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

    /**
     * 根据买家openid获取买家订单
     * @param openid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String openid , Pageable pageable);

}
