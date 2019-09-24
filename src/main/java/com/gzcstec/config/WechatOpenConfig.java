package com.gzcstec.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 微信开放平台配置类
 * Created by Administrator on 2017/11/5 0005.
 */
@Component
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxOpenService() {
        WxMpService wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage() {
        WxMpInMemoryConfigStorage wxOpenConfigStorage = new WxMpInMemoryConfigStorage();
        wxOpenConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());
        wxOpenConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());
        return wxOpenConfigStorage;
    }
}
