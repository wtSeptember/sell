package com.gzcstec.service;

import com.gzcstec.dataobject.ProductInfo;
import com.gzcstec.dataobject.SellerInfo;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
public interface SellerInfoService {

    SellerInfo findByOpenid(String openid);

    SellerInfo save(SellerInfo sellerInfo);
}
