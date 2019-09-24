package com.gzcstec.dataobject.mapper;

import com.gzcstec.dataobject.ProductInfo;
import org.apache.ibatis.annotations.Insert;

/**
 * 卖家商品mapper
 * Created by Administrator on 2017/11/10 0010.
 */
public interface SellerProductMapper {

    @Insert("insert into product_info(product_id,product_name,product_price,product_stock,category_type) " +
            "values(#{productId,jdbcType=VARCHAR},#{productName,jdbcType=VARCHAR},#{productPrice,jdbcType=DECIMAL},#{productStock,jdbcType=INTEGER},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductInfo productInfo);

    int updateByObject(ProductInfo productInfo);
}
