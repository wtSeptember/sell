package com.gzcstec.service.impl;

import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.dataobject.SellerInfo;
import com.gzcstec.repository.SellerInfoRepository;
import com.gzcstec.service.SellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@Component
public class SellerInfoServiceImpl implements SellerInfoService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }

    @Override
    public SellerInfo save(SellerInfo sellerInfo) {
        return sellerInfoRepository.save(sellerInfo);
    }
}
