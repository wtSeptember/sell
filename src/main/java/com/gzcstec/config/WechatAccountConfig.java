package com.gzcstec.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/18 0018.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /**微信appid.*/
    private String mpAppId;

    /**微信appSecret.*/
    private String mpAppSecret;

    /**微信开放平台appid.*/
    private String openAppId;

    /**微信开放平台appSecret.*/
    private String openAppSecret;

    /**商户id.*/
    private String mchId;

    /**商户key.*/
    private String mchKey;

    /**证书存放路径.*/
    private String keyPath;

    /**异步通知回调.*/
    private String notify_url;

    /**模板id.*/
    private Map<String,String> templateIds;
}
