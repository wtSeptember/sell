package com.gzcstec.service;

import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品
 * Created by Administrator on 2017/10/13 0013.
 */
public interface ProductService {

    public ProductInfo findOne(String id);

    /**
     * 查询在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findByPage(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**减少库存.*/
    void decreaseStock(List<CartDTO> cartDTOList);

    /**增加库存.*/
    void increaseStock(List<CartDTO> cartDTOList);

    /**商品下架.*/
    ProductInfo offSafe(ProductInfo productInfo);

    /**商品上架.*/
    ProductInfo onSafe(ProductInfo productInfo);
}
