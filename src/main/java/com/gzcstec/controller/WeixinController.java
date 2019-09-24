package com.gzcstec.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * 微信请求
 * Created by Administrator on 2017/10/18 0018.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入回调函数");
        log.info("code={}" , code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxa6344813f106bc7a&secret=0561d7c09b9cc71b0083e9133e9a903d&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url , String.class);
        log.info("result={}" , result);
    }
}
