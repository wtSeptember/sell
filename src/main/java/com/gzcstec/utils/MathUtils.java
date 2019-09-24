package com.gzcstec.utils;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class MathUtils {

    private static final Double MONEY_RANGE = 0.001;

    private MathUtils(){}

    public static Boolean isEqual(Double num1 , Double num2) {
        return Math.abs(num1 - num2) < MONEY_RANGE;
    }
}
