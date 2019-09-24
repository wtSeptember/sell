package com.gzcstec.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 卖家信息表
 * Created by Administrator on 2017/11/3 0003.
 */
@Data
@Entity
public class SellerInfo {

    /**卖家id.*/
    @Id
    private String sellerId;

    /**卖家姓名.*/
    private String username;

    /**卖家登录密码.*/
    private String password;

    /**卖家微信openid.*/
    private String openid;

}
