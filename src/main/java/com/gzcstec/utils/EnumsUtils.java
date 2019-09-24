package com.gzcstec.utils;

import com.gzcstec.enums.CodeEnums;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class EnumsUtils {

    private EnumsUtils(){}

    public static <T extends CodeEnums>T getEnumsByCode(Integer code , Class<T> enums) {
        for(T e : enums.getEnumConstants()) {
            if( e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
