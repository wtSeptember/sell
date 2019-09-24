package com.gzcstec.utils;

import java.util.Random;

/**
 * 生成唯一主键
 * Created by Administrator on 2017/10/15 0015.
 */
public class KeyUtils {

    public static synchronized String gen() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
