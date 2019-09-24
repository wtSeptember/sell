package com.gzcstec.service.impl;

import com.gzcstec.dataobject.SellerInfo;
import com.gzcstec.service.SellerInfoService;
import com.gzcstec.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoServiceImplTest {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo sellerInfo = sellerInfoService.findByOpenid("oDPSusyTtH7n6reA30cS3TR_JX3M");
        Assert.assertTrue("卖家信息为空" , sellerInfo != null);
    }

    @Test
    public void save() throws Exception {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenid("oDPSusyTtH7n6reA30cS3TR_JX3M");
        sellerInfo.setPassword("abc");
        sellerInfo.setUsername("yien");
        sellerInfo.setSellerId(KeyUtils.gen());

        SellerInfo result = sellerInfoService.save(sellerInfo);
        Assert.assertTrue("openid 不相等" , result.getOpenid().equals(sellerInfo.getOpenid()));

    }

}