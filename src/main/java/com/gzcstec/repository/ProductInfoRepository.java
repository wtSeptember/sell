package com.gzcstec.repository;

import com.gzcstec.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo , String> {

    /**
     * 根据商品状态查询商品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
