package com.gzcstec.aspect;

import com.gzcstec.constant.CookieConst;
import com.gzcstec.constant.RedisConst;
import com.gzcstec.exception.SellerAuthorizeException;
import com.gzcstec.utils.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.RequestHandledEvent;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录权限检验切面
 * Created by Administrator on 2017/11/6 0006.
 */
@Aspect
@Slf4j
@Component
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Pointcut("execution(public * com.gzcstec.controller.SellerUserController.*(..))"+
    @Pointcut("execution(public * com.gzcstec.controller.Seller*.*(..))"+
    " && !execution(public * com.gzcstec.controller.SellerUserController.*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void doVerify() {
        //1.获取request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //2.获取cookies
        Cookie cookie = CookieUtils.get(request , CookieConst.TOKEN);
        if(null == cookie) {
            log.warn("【登录校验】cookie没有找到token");
            throw new SellerAuthorizeException();
        }

        //3.获取redis
        String openid = stringRedisTemplate.opsForValue().get(String.format(RedisConst.PREFIX , cookie.getValue()));
        if(StringUtils.isEmpty(openid)) {
            log.warn("【登录校验】redis没有找到token");
            throw new SellerAuthorizeException();
        }
    }

}
