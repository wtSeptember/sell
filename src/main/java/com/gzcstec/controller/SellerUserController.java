package com.gzcstec.controller;

import com.gzcstec.config.ProjectUrlConfig;
import com.gzcstec.constant.CookieConst;
import com.gzcstec.constant.RedisConst;
import com.gzcstec.dataobject.SellerInfo;
import com.gzcstec.enums.CodeEnums;
import com.gzcstec.enums.ExceptionCodeEnums;
import com.gzcstec.service.SellerInfoService;
import com.gzcstec.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家信息
 * Created by Administrator on 2017/11/5 0005.
 */
@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("login")
    public ModelAndView login(@RequestParam("openid") String openid ,
                              HttpServletResponse response,
                              Map<String,Object> map) {
        //1.查找用户信息
        SellerInfo sellerInfo = sellerInfoService.findByOpenid(openid);
        if(null == sellerInfo) {
            log.error("【卖家登录】卖家信息没有找到，openid={}" , openid);
            map.put("msg" , ExceptionCodeEnums.LOGIN_FAIL.getMsg());
            map.put("url" , "/sell/seller/order/list");
            return new ModelAndView("/common/error" , map);
        }

        String token = UUID.randomUUID().toString();
        Integer expire = RedisConst.EXPIRE;

        //2.存放redis
        stringRedisTemplate.opsForValue()
                .set(String.format(RedisConst.PREFIX , token)
                        , openid , expire , TimeUnit.SECONDS);

        //3.存放cookie
        CookieUtils.set(response , CookieConst.TOKEN , token , expire);

        return new ModelAndView("redirect:"+ projectUrlConfig.getSell() + "/sell/seller/order/list");
    }

    @GetMapping("logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String,Object> map) {
        //1.查找cookie
        Cookie cookie = CookieUtils.get(request , CookieConst.TOKEN);
        if(null != cookie) {
            //2.清楚redis
            stringRedisTemplate.opsForValue().getOperations()
                    .delete(String.format(RedisConst.PREFIX , cookie.getValue()));

            //3.清楚cookie
            CookieUtils.set(response , CookieConst.TOKEN , null , 0);
        }

        map.put("msg" , ExceptionCodeEnums.LOGOUT_SUCCESS.getMsg());
        map.put("url" , "/sell/seller/order/list");
        return new ModelAndView("/common/success" , map);

    }
}
