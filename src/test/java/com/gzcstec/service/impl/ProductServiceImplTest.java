package com.gzcstec.service.impl;

import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.enums.ProductStatusEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() throws Exception {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertNotEquals(0 , productInfos.size());
    }

    @Test
    public void findByPage() throws Exception {
        PageRequest pageRequest = new PageRequest(0 , 2);
        Page<ProductInfo> productInfos = productService.findByPage(pageRequest);
        Assert.assertNotNull(productInfos);
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("123450");
        productInfo.setProductName("八期烤鱼");
        productInfo.setProductDescription("超级好吃");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setCategoryType(3);
        productInfo.setProductStatus(ProductStatusEnums.UP.getCode());

        ProductInfo result = productService.save(productInfo);

        Assert.assertNotNull(result);
    }

}