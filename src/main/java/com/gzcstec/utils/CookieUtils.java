package com.gzcstec.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie帮助类
 * Created by Administrator on 2017/11/6 0006.
 */
public class CookieUtils {

    /**
     * 增加cookie
     * @param response
     * @param name
     * @param value
     * @param maxAge
     */
    public static void set(HttpServletResponse response, String name , String value , Integer maxAge) {
        Cookie cookie = new Cookie(name , value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 获取cookie
     * @param request
     * @param name
     * @return
     */
    public static Cookie get(HttpServletRequest request , String name) {
        Cookie cookie = getCookieMap(request).get(name);
        if(null != cookie) {
            return cookie;
        }else {
            return null;
        }
    }

    /**
     * 将cookie转成map
     * @param request
     * @return
     */
    private static Map<String , Cookie> getCookieMap(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Map<String , Cookie> cookieMap = new HashMap<>();
        if(null != cookies) {
            for(Cookie cookie : cookies) {
                cookieMap.put(cookie.getName() , cookie);
            }
        }
        return cookieMap;
    }
}
