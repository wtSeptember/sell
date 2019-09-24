package com.gzcstec.repository;

import com.gzcstec.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/13 0013.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("1234567");
        productInfo.setProductName("肠粉");
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http://xxxxx");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setCategoryType(2);

        ProductInfo result = productInfoRepository.save(productInfo);

        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductStatus() throws Exception {

    }

}