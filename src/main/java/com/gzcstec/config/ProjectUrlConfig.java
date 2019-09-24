package com.gzcstec.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 工程路径管理
 * Created by Administrator on 2017/11/5 0005.
 */
@Component
@Data
@ConfigurationProperties(prefix = "projectUrlConfig")
public class ProjectUrlConfig {

    /**微信认证url.*/
    private String mpAuthorizeUrl;

    /**微信网页授权登录url.*/
    private String openAuthorizeUrl;

    /**点餐系统.*/
    private String sell;

}
