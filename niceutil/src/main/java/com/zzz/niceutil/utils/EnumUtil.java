package com.zzz.niceutil.utils;

import java.lang.reflect.Method;

/**
 * @ClassName:EnumUtil
 * @Description:
 * @Author: 张卫刚
 * @Date: 2022/5/30 13:19
 */
public class EnumUtil {

    public static String getValue(Class clazz, Object code) {
        if (code == null) {
            return null;
        }
        Object[] enumConstants = clazz.getEnumConstants();
        try {
            for (Object object : enumConstants) {
                Method getCode = clazz.getMethod("getCode");
                Method getName = clazz.getMethod("getName");
                if (getCode.invoke(object).equals(code)) {
                    return getName.invoke(object).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCode(Class clazz, Object name) {
        if (name == null) {
            return null;
        }
        Object[] enumConstants = clazz.getEnumConstants();
        try {
            for (Object object : enumConstants) {
                Method getCode = clazz.getMethod("getCode");
                Method getName = clazz.getMethod("getName");
                if (getName.invoke(object).equals(name)) {
                    return getCode.invoke(object).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <E> E getEnumByCode(Class<E> clazz, Object code) {
        if (code == null) {
            return null;
        }
        E[] enumConstants = clazz.getEnumConstants();
        try {
            for (E object : enumConstants) {
                Method getCode = clazz.getMethod("getCode");
                if (getCode.invoke(object).equals(code)) {
                    return object;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
